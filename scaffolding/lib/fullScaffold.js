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
    renderMarkdownTemplates,
    renderSnippetTemplates
  } = require('./common.js');

function commandFullScaffold (options) {
  if (! options.controller) {
    // eslint-disable-next-line no-console    
    console.error('You must provide a controller name');
    process.exit(1);
  }

  if (! options.action) {
    // eslint-disable-next-line no-console
    console.error('You must provide an action name');
    process.exit(1);
  }

  const variables = {
    controller: options.controller,
    action: options.action
  };

  renderMarkdownTemplates(variables);

  renderSnippetTemplates('usage-snippets', options.action, variables);
}

module.exports = commandFullScaffold;
