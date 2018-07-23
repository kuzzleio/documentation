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
  program = require('commander');

program
  .command('full')
  .description('generate a full scaffold for a controller action')
  .option('-c, --controller <controller>', 'Controller name')
  .option('-a, --action <action>', 'Action name')
  .action(require('./lib/fullScaffold.js'));

program
  .command('snippet')
  .description('generate a scaffold for a snippet')
  .option('-c, --controller <controller>', 'Controller name')
  .option('-a, --action <action>', 'Action name')
  .option('-s, --snippet <snippet>', 'Snippet name')
  .action(require('./lib/snippetScaffold.js'));

program.parse(process.argv);

/*
  Workaround for a bug in commander: program.args.length is empty if a sub-command is executed with
  a flag as the last option before an argument.
  For instance: "kuzzle install -a plugin" will incorrectly trigger the help and exit

  This is still true with commander v2.2
 */
if (program.rawArgs.length <= 2) {
  program.help();
}
