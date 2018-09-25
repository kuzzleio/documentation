const manageArgs = (args, options) => {
  if (args.indexOf('--dev') > -1) {
    options.dev.enabled = true;
  }
  
  if (args.indexOf('--open-browser') > -1) {
    options.dev.openBrowser = true;
  }
  
  if (args.indexOf('--port') > -1) {
    options.dev.port = parseInt(args[args.indexOf('--port') + 1]);
  }
  
  if (args.indexOf('--watch') > -1) {
    options.dev.watch = true;
  }
  
  if (args.indexOf('--build-compress') > -1) {
    options.build.compress = true;
  }
  
  if (args.indexOf('--build-path') > -1) {
    options.build.path = args[args.indexOf('--build-path') + 1];
  }
  
  if (args.indexOf('--build-host') > -1) {
    options.build.host = args[args.indexOf('--build-host') + 1];
  }
  
  if (args.indexOf('--algolia-private-key') > -1) {
    options.algolia.privateKey = args[args.indexOf('--algolia-private-key') + 1];
  }
  
  return options;
};

module.exports = manageArgs;