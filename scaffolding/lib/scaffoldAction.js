const
  { renderTemplate } = require('./utils'),
  _ = require('lodash'),
  path = require('path');

const documentationPath = path.join(
  path
    .dirname(require.main.filename)
    .split(path.sep)
    .slice(0, -1)
    .join(path.sep),
  'src',
  'sdk-reference'
);

const templatesPath = path.join(
  path.dirname(require.main.filename),
  'templates'
);

async function renderMarkdownTemplate({ controller, action, language, version, actionPath }) {
  const
    actionTemplate = path.join(templatesPath, `action.${language}.md`),
    destinationFile = path.join(actionPath, 'index.md'),
    locals = { controller, action, language, version, _ };

  return renderTemplate(actionTemplate, destinationFile, locals);
};

async function renderSnippetTemplate({ controller, action, language, version, actionPath }) {
  const
    snippetTemplate = path.join(templatesPath, 'usage-snippets',`usage.${language}`),
    destinationFile = path.join(actionPath, 'snippets', `${_.kebabCase(action)}.${language}`),
    locals = { controller, action, language, version, _ };

  return renderTemplate(snippetTemplate, destinationFile, locals);
};

async function renderSnippetConfigTemplate({ controller, action, language, version, actionPath }) {
  const
    snippetTemplate = path.join(templatesPath, 'usage-snippets','usage.test.yml'),
    destinationFile = path.join(actionPath, 'snippets', `${_.kebabCase(action)}.test.yml`),
    locals = { controller, action, language, version, _ };

  return renderTemplate(snippetTemplate, destinationFile, locals);
};

async function scaffoldAction (actionPath) {
  if (! actionPath) {
    // eslint-disable-next-line no-console
    console.error('You must provide a path for the action');
    process.exit(1);
  }

  const [
    language,
    version,
    controller,
    action
  ] = (actionPath.split('sdk-reference/')[1] || '').split('/');

  if (! language) {
    // eslint-disable-next-line no-console
    console.error('You must provide a language for the action');
    process.exit(1);
  }

  if (! version) {
    // eslint-disable-next-line no-console
    console.error('You must provide a version for the action');
    process.exit(1);
  }

  if (! controller) {
    // eslint-disable-next-line no-console
    console.error('You must provide a controller for the action');
    process.exit(1);
  }

  if (! action) {
    // eslint-disable-next-line no-console
    console.error('You must provide a name for the action');
    process.exit(1);
  }

  const variables = {
    controller,
    action,
    language,
    version,
    actionPath
  };

  await renderMarkdownTemplate(variables);
  await renderSnippetTemplate(variables);
  await renderSnippetConfigTemplate(variables)
}

module.exports = scaffoldAction;
