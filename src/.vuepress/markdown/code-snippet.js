const { fs, path } = require('@vuepress/shared-utils');

module.exports = function snippet(md, options = {}) {
  const root = options.root || process.cwd();
  function parser(state, startLine, endLine, silent) {
    const CH = '<'.charCodeAt(0);
    const pos = state.bMarks[startLine] + state.tShift[startLine];
    const max = state.eMarks[startLine];

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
    const sourcePath = state.src.slice(start, end).trim();
    let rawPath = sourcePath;

    // Extract raw path (absolute)
    if (/^@/.exec(sourcePath)) {
      rawPath = sourcePath.replace(/^@/, root);
    }

    // Extract raw path (relative)
    if (/^\./.exec(sourcePath)) {
      rawPath = sourcePath.replace(
        /^\./,
        path.dirname(path.normalize(`${root}/src/${state.env.relativePath}`))
      );
    }

    // Extract snippet id (if present)
    let snippetId = rawPath.match(/:\d+/);
    if (snippetId && snippetId[0]) {
      snippetId = snippetId[0];
      rawPath = rawPath.replace(snippetId, '');
    }

    // Generate snippet-extraction RegExp
    const SNIPPET_EXTRACT = generateSnippetRegex(snippetId);

    // Extract filename
    const filename = rawPath.split(/[{:\s]/).shift();
    let content = fs.existsSync(filename)
      ? fs.readFileSync(filename).toString()
      : 'Not found: ' + filename;

    // Extract snippet from file content
    const match = SNIPPET_EXTRACT.exec(content);
    if (match && match[3]) {
      content = match[3];
    }

    // Delete snippet extraction tags (if any)
    content = content.replace(/\/\*( *)snippet:start(:\d+)?( *)\*\/\n/g, '');
    content = content.replace(/\/\*( *)snippet:end( *)\*\/\n/g, '');

    // Extract meta (line highlight)
    const meta = rawPath.replace(filename, '');

    state.line = startLine + 1;

    // Create token
    const token = state.push('fence', 'code', 0);
    token.info = filename.split('.').pop() + meta;
    token.content = content;
    token.markup = '```';
    token.map = [startLine, startLine + 1];

    return true;
  }

  md.block.ruler.at('snippet', parser);
};

function generateSnippetRegex(snippetId) {
  if (!snippetId) {
    snippetId = '';
  }

  return new RegExp(
    `\\/\*( *)snippet:start${snippetId}( *)\\*\\/\\n?((.|\\n)*?)\\/\\*( *)snippet:end( *)\\*\\/`,
    'gm'
  );
}
