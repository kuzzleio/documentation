import { Logger } from '@vuepress/helper';
import { fs, path } from '@vuepress/utils';
import fixIndents from 'fix-indents';
import MarkdownIt from 'markdown-it';

export type SnippetOptions = {
  docsDir?: string;
  logger: Logger;
};

function generateSnippetRegex(snippetId: string) {
  // if no snippet id is specfied, exclude all snippets that have an ID
  if (!snippetId) {
    snippetId = '[^:^0-9]';
  }

  return new RegExp(
    `^.*snippet:start${snippetId}.*$\\n((.|\\n)*?)^.*(snippet:end |snippet:end${snippetId}).*$\\n`,
    'gm'
  );
}

const snippet = (md: MarkdownIt, options: SnippetOptions) => {
  const logger = options.logger;
  const cwd = process.cwd();
  let docsDir = options.docsDir || 'src';

  if (!path.isAbsolute(docsDir)) {
    docsDir = path.join(process.cwd(), docsDir)
  }

  md.block.ruler.before('paragraph', 'kuzdoc_snippet', (state, startLine, _endLine, silent) => {
    const CH = '<'.charCodeAt(0);
    const pos = state.bMarks[startLine] + state.tShift[startLine];
    const max = state.eMarks[startLine];
    /**
     * The path of the MD document that includes the snippet, relative
     * to the docsDir. Sometimes undefined \o/
     */
    const documentPath = state.env.filePathRelative;

    // if it's indented more than 3 spaces, it should be a code block
    if (state.sCount[startLine] - state.blkIndent >= 4) {
      return false;
    }

    // Look for snippet import tag
    for (let i = 0; i < 3; ++i) {
      const ch = state.src.charCodeAt(pos + i);
      if (ch !== CH || pos + i >= max) return false;
    }

    if (silent) {
      return true;
    }

    const start = pos + 3;
    const end = state.skipSpacesBack(max, pos);
    const snippetPath = state.src.slice(start, end).trim();
    let rawPath = snippetPath;

    // Extract raw path (absolute)
    if (/^@/.exec(snippetPath)) {
      rawPath = snippetPath.replace(/^@/, cwd);
    }


    // Extract raw path (relative)
    if (/^\./.exec(snippetPath)) {
      if (!documentPath) {
        return false;
      }
      rawPath = path.join(docsDir, path.dirname(documentPath), snippetPath)
    }

    // Extract snippet id (if present)
    let snippetIds = rawPath.match(/:\d+/);
    let snippetId = '';

    if (snippetIds && snippetIds[0]) {
      snippetId = snippetIds[0];
      rawPath = rawPath.replace(snippetId, '');
    }

    // Extract line highligts (if present)
    let highlights = rawPath.match(/{.*}/);
    let highlight = '';

    if (highlights && highlights[0]) {
      highlight = highlights[0];
      rawPath = rawPath.replace(highlight, '');
    }

    // Extract language highlighting (if present)
    let languages = rawPath.match(/\[([a-z]*)\]/);
    let language: string | null = null;

    if (languages && languages[0]) {
      rawPath = rawPath.replace(languages[0], '');
      if (languages[1]) {
        language = languages[1];
      }
    }

    // Generate snippet-extraction RegExp
    const SNIPPET_EXTRACT = generateSnippetRegex(snippetId);

    // Extract filename
    const filename = rawPath.split(/[\[{:\s]/).shift() as string;

    // By default, import the whole file contents
    const fileExists = fs.existsSync(filename);
    let content = fileExists
      ? fs.readFileSync(filename).toString()
      : 'Not found: ' + filename;

    if (documentPath && !fileExists) {
      logger.error(`Cannot find snippet at ${filename} (docsDir=${docsDir}, documentPath=${documentPath})`)
    }

    // Extract snippet from file content, if matches snippet:* tags
    const match = SNIPPET_EXTRACT.exec(content);
    if (match && match[1]) {
      content = match[1];
    }

    // Delete snippet extraction tags (if any)
    content = content.replace(/.*snippet:start(:\d+)?.*\n/g, '');
    content = content.replace(/.*snippet:end.*\n/g, '');

    // Fix indentation in code content
    content = fixIndents(content, {
      countSpaces: 2
    });

    // Extract meta (line highlight)
    const fileExtension = filename.split('.').pop();
    const meta = `${language ? language : fileExtension}${
      highlight ? highlight : ''
      }`;

    state.line = startLine + 1;

    // Create token
    const token = state.push('fence', 'code', 0);
    token.info = meta;
    token.content = content;
    token.markup = '```';
    token.map = [startLine, startLine + 1];

    return true;
  });
}

export default snippet;
