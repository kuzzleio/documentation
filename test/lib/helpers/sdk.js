const
  readYaml = require('read-yaml'),
  path = require('path'),
  { spawnSync } = require('child_process');

const SDK_VERSIONS_PATH = path.join(__dirname, '../../sdk-versions.yml');

async function execute(command, args, options = {}) {
  options.encoding = 'utf8';

  const { status, output, error } = spawnSync(command, args, options);

  if (error) {
    throw error;
  }

  if (status !== 0) {
    const message = output.join("\n");

    throw new Error(message);
  }

  return status;
}

function getVersionPath(language, version) {
  const sdkVersions = readYaml.sync(SDK_VERSIONS_PATH);
  const supportedLanguages = getSupportedLanguages(sdkVersions);

  if (! supportedLanguages.includes(language)) {
    throw new Error(`Unknown language ${language}. Supported languages: ${supportedLanguages.join(',')}`);
  }

  const sdkVersionType = sdkVersions[language][version];
  if (! sdkVersionType) {
    throw new Error(`Unknown version ${version} for ${language} SDK.`);
  }

  const versionPath = (() => {
    switch (sdkVersionType) {
      case 'latest':
        return 'latest';
      case 'stable':
        return `${version}-stable`;
      case 'unstable':
        return `${version}-dev`;
      default:
        throw new Error(`Unknown version type '${sdkVersionType}' for ${language}#${version}`);
    }
  })();

  return versionPath;
}

function getSupportedLanguages(sdkVersions = null) {
  if (! sdkVersions) {
    sdkVersions = readYaml.sync(SDK_VERSIONS_PATH);
  }

  return Object.keys(sdkVersions);
}

module.exports = {
  execute,
  getVersionPath,
  getSupportedLanguages
};
