const
  {
    explodeSdkPath,
    renderSnippetTemplate,
    renderMarkdownTemplate,
    renderSnippetConfigTemplate,
    showSignatures
  } = require('./utils'),
  _ = require('lodash'),
  path = require('path');

async function generateCommand (actionPath) {
  if (! actionPath) {
    // eslint-disable-next-line no-console
    console.error('You must provide a path for the action');
    process.exit(1);
  }

  try {
    const sdkInfos = explodeSdkPath(actionPath);

    await renderMarkdownTemplate(sdkInfos, actionPath);
    await renderSnippetTemplate(sdkInfos, actionPath);
    await renderSnippetConfigTemplate(sdkInfos, actionPath)

    showSignatures(sdkInfos);
  } catch (error) {
    console.error(error);
    process.exit(1);
  }
}

module.exports = generateCommand;
