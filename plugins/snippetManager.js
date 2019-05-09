const fs = require('fs'),
  path = require('path'),
  MarkdownIt = require('markdown-it'),
  color = require('colors/safe'),
  hljs = require('highlight.js'),
  indentLine = require('indent-string');

const SNIPPET_REGEX = /(\[snippet=)[a-zA-Z0-9-]+\]/g;
const SNIPPET_INOTHER_REGEX = /(\[\[snippet=)[a-zA-Z0-9-]+\]]/g;

const SNIPPET_EXTRACT = /\/\* *snippet:start *\*\/\n?((.|\n)*?)\/\* *snippet:end *\*\//;

const md = new MarkdownIt();

const countIndent = (string, pos) => {
  let count = 0;
  let idx = pos - 1;
  const char = string[idx];
  for (; string[idx] === ' ' || string[idx] === '\t'; idx -= 1) {
    count += 1;
  }
  return {
    char,
    count
  };
};

const getDirPath = filename =>
  path.join(
    __dirname,
    '../src/' +
      filename
        .split('/')
        .slice(0, -1)
        .join('/') +
      '/snippets'
  );

const getMarkdown = (code, lang, indent, wrapWithTag) => {
  if (wrapWithTag) {
    return md.render(code);
  }
  const codeIndented = indentLine(code, indent.count, { indent: indent.char });
  return hljs.highlight(lang, codeIndented, true).value;
};

const getSnippetContent = (dir, file) => {
  let content = fs.readFileSync(dir + '/' + file, 'utf8');

  const match = SNIPPET_EXTRACT.exec(content);
  if (match) {
    content = match[1];
  }

  if (content[content.length - 1] === '\n') {
    content = content.slice(0, -1);
  }

  return content;
};

const replaceSnippets = (filename, fileString, regex, wrapWithTag = true) => {
  const match = fileString.match(regex);
  if (match) {
    match.forEach(el => {
      let name = el.split('=')[1].slice(0, wrapWithTag ? -1 : -2),
        dirPath = getDirPath(filename),
        filenames = fs.readdirSync(dirPath),
        code = '';

      const indent = countIndent(fileString, fileString.indexOf(el));

      let languageName;
      filenames.forEach(file => {
        if (file.split('.')[0] === name && file.substr(-8) !== 'test.yml') {
          const fileContent = getSnippetContent(dirPath, file);
          const lng = file.split('.')[1];

          languageName = lng === 'js' ? 'javascript' : lng;
          code += wrapWithTag
            ? '``` ' + languageName + '\n' + fileContent + '\n```\n'
            : fileContent;
        }
      });

      const markdown = getMarkdown(code, languageName, indent, wrapWithTag);
      fileString = fileString.replace(
        indent.char.repeat(indent.count) + el,
        markdown
      );
    });
  }
  return fileString;
};

module.exports = {
  process(filename, data) {
    let self = this,
      fileString = data.contents.toString(),
      match = false;

    let newFileString = replaceSnippets(
      filename,
      fileString,
      SNIPPET_INOTHER_REGEX,
      false
    );
    newFileString = replaceSnippets(filename, newFileString, SNIPPET_REGEX);
    if (newFileString !== fileString) {
      fileString = newFileString;
      match = true;
    }

    return {
      has_code_example: match,
      fileContent: new Buffer(fileString)
    };
  },

  report(args) {
    console.log(color.yellow('[TO-DO] =>'), args);
  }
};
