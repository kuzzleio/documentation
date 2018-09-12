const
  {
    renderSnippetTemplate,
    renderMarkdownTemplate,
    renderSnippetConfigTemplate,
    explodeSdkPath,
    extractFromFile,
    injectInFile
  } = require('./utils'),
  _ = require('lodash'),
  path = require('path');

const longDescriptionRegexp = {
  start: '\# create\n',
  end: '\#\# Signature\n'
};

const shortDescriptionRegexp = {
  start: 'description:',
  end: '\norder'
};

const argsTableRegexp = {
  start: '\#\# Arguments\n',
  end: '\n\#\#\#'
};

const argsDescriptionRegexp = {
  start: '\#\#\#',
  end: '\n\#\# R',
  includeStart: true
};

const hookAfterRegexp = {
  start: 'after:',
  end: '\ntemplate'
};

const hookBeforeRegexp = {
  start: 'before:',
  end: '\n  after'
};

const snippetTemplateRegexp = {
  start: 'template:',
  end: '\nexpected'
};

const snippetExpectedRegexp = {
  start: 'expected:',
  end: '\n'
};

function injectTemplates(sdkInfos, src, dest) {
  const
    srcIndexFile = `${src}/index.md`,
    srcTestConfigFile =`${src}/snippets/${sdkInfos.action}.test.yml`,
    destIndexFile = `${dest}/index.md`,
    destTestConfigFile =`${dest}/snippets/${sdkInfos.action}.test.yml`,
    longDescription = extractFromFile(srcIndexFile, longDescriptionRegexp),
    shortDescription = extractFromFile(srcIndexFile, shortDescriptionRegexp),
    argsTable = extractFromFile(srcIndexFile, argsTableRegexp),
    argsDescription = extractFromFile(srcIndexFile, argsDescriptionRegexp),
    hookAfter = extractFromFile(srcTestConfigFile, hookAfterRegexp),
    hookBefore = extractFromFile(srcTestConfigFile, hookBeforeRegexp),
    snippetTemplate = extractFromFile(srcTestConfigFile, snippetTemplateRegexp),
    snippetExpected = extractFromFile(srcTestConfigFile, snippetExpectedRegexp);

  injectInFile(destIndexFile, longDescriptionRegexp, longDescription);
  injectInFile(destIndexFile, shortDescriptionRegexp, shortDescription);
  injectInFile(destIndexFile, argsTableRegexp, argsTable);
  injectInFile(destIndexFile, argsDescriptionRegexp, argsDescription);
  injectInFile(destTestConfigFile, hookAfterRegexp, hookAfter);
  injectInFile(destTestConfigFile, hookBeforeRegexp, hookBefore);
  injectInFile(destTestConfigFile, { start: shortDescriptionRegexp.start, end: '\nhooks' }, shortDescription);
  injectInFile(destTestConfigFile, snippetTemplateRegexp, snippetTemplate);
  injectInFile(destTestConfigFile, snippetExpectedRegexp, snippetExpected);
}

async function copyCommand (src, dest) {
  if (! src) {
    // eslint-disable-next-line no-console
    console.error('You must provide a source path for the action');
    process.exit(1);
  }

  if (! dest) {
    // eslint-disable-next-line no-console
    console.error('You must provide a destination path for the new action');
    process.exit(1);
  }

  try {
    const sdkInfos = explodeSdkPath(dest);

    await renderMarkdownTemplate(sdkInfos, dest);
    await renderSnippetTemplate(sdkInfos, dest);
    await renderSnippetConfigTemplate(sdkInfos, dest)

    injectTemplates(sdkInfos, src, dest);
  } catch(error) {
    console.error(error);
    process.exit(1);
  }
}

module.exports = copyCommand;
