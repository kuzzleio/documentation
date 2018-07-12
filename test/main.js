const program = require('commander');
const TestManager = require('./lib/testManager');

program
  .version(require('../package.json').version)
  .option('-L, --language <language>', '[Mandatory] The language to test. Available languages are js, go')
  .option('-O, --only <test_path>', 'The path of a single test to execute, relative to src/sdk-reference')
  .parse(process.argv);

if (!program.language) {
  console.error('Missing --language argument.')
  program.help();
}

testManager = new TestManager(program.language);
testManager.process(program.only);