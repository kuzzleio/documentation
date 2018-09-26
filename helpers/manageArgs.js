const manageArgs = (args, options) => {
  console.log(args);
  
  if (args.dev) {
    options.dev.enabled = true;
  }
  
  if (args.openBrowser) {
    options.dev.openBrowser = true;
  }
  
  if (args.port) {
    options.dev.port = args.port;
  }
  
  if (args.watch) {
    options.dev.watch = true;
  }
  
  if (args.buildCompress) {
    options.build.compress = true;
  }
  
  if (args.buildPath) {
    options.build.path = args.buildPath;
  }
  
  if (args.buildHost) {
    options.build.host = args.buildHost
  }
  
  if (args.algoliaPrivateKey) {
    options.algolia.privateKey = args.algoliaPrivateKey;
  }
  
  return options;
};

module.exports = manageArgs;