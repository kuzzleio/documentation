
const TestManager = require('./lib/testManager');

if (process.argv.indexOf('-L') > -1) {
  const 
    language = process.argv[process.argv.indexOf('-L') + 1],
    testManager = new TestManager(language);

  testManager.process(language);
  
} else {
  console.log('You have to define a language with -L args');
  process.exit(1);
}
