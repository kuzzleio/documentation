function sleep (ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

const consoleLog = console.log;
const outputs = [];

console.log = (...args) => {
  outputs.push(...args);
};

[snippet-code]

(async () => {
  for (let i = 10; i > 0 && outputs.length <= 0; --i) {
    await sleep(200);
  }
})();

console.log = consoleLog;
console.log(...outputs);

kuzzle.disconnect();
