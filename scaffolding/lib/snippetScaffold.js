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
  {
    renderSnippetTemplates
  } = require('./common.js');

function commandSnippetScaffold (options) {
  if (! options.controller) {
    // eslint-disable-next-line no-console
    console.error('You must provide a controller name.');
    process.exit(1);
  }

  if (! options.action) {
    // eslint-disable-next-line no-console
    console.error('You must provide an action name.');
    process.exit(1);
  }

  if (! options.snippet) {
    // eslint-disable-next-line no-console
    console.error('You must provide a snippet name.');
    process.exit(1);
  }

  if (options.action === options.snippet) {
    // eslint-disable-next-line no-console
    console.error('You are overriding usage snippet.');
    process.exit(1);
  }

  const variables = {
    controller: options.controller,
    action: options.action,
    snippet: options.snippet
  };

  renderSnippetTemplates('blank-snippets', options.snippet, variables);
}

module.exports = commandSnippetScaffold;
