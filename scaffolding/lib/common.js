/*
 * Kuzzle, a backend software, self-hostable and ready to use
 * to power modern apps
 *
 * Copyright 2015-2018 Kuzzle
 * mailto: support AT kuzzle.io
 * website: http://kuzzle.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

const
  fs = require('fs'),
  _ = require('lodash'),
  ejs = require('ejs'),
  path = require('path');

const documentationPath = path.join(
  path
    .dirname(require.main.filename)
    .split(path.sep)
    .slice(0, -1)
    .join(path.sep),
  'src',
  'sdk-reference'
);

const templatesPath = path.join(
  path.dirname(require.main.filename),
  'templates'
);

const renderMarkdownTemplates = (variables) => {
  const templates = fs.readdirSync(templatesPath)
    .filter(entry => entry.slice(-3, entry.length) === '.md');

  const locals = Object.assign({}, { _ }, variables);

  for (const template of templates) {
    const [filename, language] = template.replace('.md', '').split('.');
    const extension = language ? `.${language}.md` : '.md';

    const destinationFile = path
      .join(
        documentationPath,
        _.kebabCase(variables.controller),
        _.kebabCase(variables.action),
        _.kebabCase(filename.replace('action', _.kebabCase(variables.action)))
      )
      .concat(extension);

    if (! fs.existsSync(path.dirname(destinationFile))) {
      fs.mkdirSync(path.dirname(destinationFile));
    }

    ejs.renderFile(path.join(templatesPath, template), locals, {}, (error, output) => {
      fs.writeFileSync(destinationFile, output);
    });
  }
}

const renderSnippetTemplates = (snippetTemplate, snippetName, variables) => {
  const snippetsPath = path.join(templatesPath, snippetTemplate);
  const templates = fs.readdirSync(snippetsPath);
  const destinationPath = path.join(
    documentationPath,
    _.kebabCase(variables.controller),
    _.kebabCase(variables.action),
    'snippets'
  );

  if (! fs.existsSync(destinationPath)) {
    fs.mkdirSync(destinationPath);
  }

  const locals = Object.assign({}, { _ }, variables);

  for (const template of templates) {
    const extension = template.split('.').slice(1).join('.');
    const destinationFile = path.join(
      destinationPath,
      `${snippetName}.${extension}`
    );

    ejs.renderFile(path.join(snippetsPath, template), locals, {}, (error, output) => {
      if (error) {
        console.error(error);
        return;
      }

      fs.writeFileSync(destinationFile, output);
    });
  }
}

module.exports = {
  renderMarkdownTemplates,
  renderSnippetTemplates
}
