const metalsmithOptions = {
  dev: {
    enabled: false,
    port: 8080,
    openBrowser: false,
    watch: false
  },
  build: {
    compress: false,
    checkLinks: false,
    host: '',
    path: '/'
  },
  algolia: {
    projectId: '4RFBRWISJR',
    index: '',
    publicKey: '6febf1ebe906bd82bce58d5a20ac6c1b',
    privateKey: undefined,
    fnFileParser: undefined
  },
  github: {
    repository: '',
    branch: ''
  }
};

module.exports = metalsmithOptions;