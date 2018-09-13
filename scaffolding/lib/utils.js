const
  fs = require('fs'),
  ejs = require('ejs'),
  _ = require('lodash'),
  { exec } = require('child_process'),
  path = require('path');

function mkdirp (fullPath) {
  const parts = fullPath.split(path.sep);

  if (parts.length > 1) {
    mkdirp(parts.slice(0, parts.length - 1).join('/'));
  }

  if (! fs.existsSync(fullPath)) {
    fs.mkdirSync(fullPath);
  }
}

function get(store, key) {
  if (store && store[key]) {
    return store[key];
  }

  return '';
}

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

async function renderTemplate(source, destination, variables) {
  if (fs.existsSync(destination)) {
    throw new Error(`${destination} already exists.`)
  }

  const
    locals = Object.assign({}, { _ }, variables);

  mkdirp(path.dirname(destination));

  return new Promise((resolve, reject) => {
    ejs.renderFile(source, locals, {}, (error, output) => {
      if (error) {
        reject(error);
        return;
      }

      fs.writeFileSync(destination, output);
      resolve();
    });
  });
}

/* EXPORTED FUNCTIONS =====================================================   */

function showSignatures({ language, action, controller }) {
  console.log('Function signature:\n');
  const display = (error, stdout, stderr) => {
    if (error) {
      console.log(error.message);
    }
    console.log(stdout);
  };

  switch (language) {
    case 'js':
      exec(`cat find node_modules/kuzzle-sdk/src/controllers -name "*.js" | grep ${controller} | xargs cat | grep '${_.camelCase(action)} ('`, display);
      break;

    case 'cpp':
      exec(`cat test/bin/sdk-cpp/include/${controller}.hpp | grep ${_.camelCase(action)}`, display);
      break;

    case 'java':
      exec(`javap -classpath test/bin/sdk-java/kuzzlesdk-java.jar io.kuzzle.sdk.${_.upperFirst(_.camelCase(controller))} | grep ${_.camelCase(action)}`, display);
      break;

    case 'go':
      exec(`cat ~/go/src/github.com/kuzzleio/sdk-go/${controller}/${_.camelCase(action)}.go | grep ${_.upperFirst(_.camelCase(action))}`, display);
      exec(`cat ~/go/src/github.com/kuzzleio/sdk-go/${controller}/${_.snakeCase(action)}.go | grep ${_.upperFirst(_.camelCase(action))}`, display);
      break;
  }

  console.log('\n');
}

function renderMarkdownTemplate(variables, actionPath) {
  const
    actionTemplate = path.join(templatesPath, `action.${variables.language}.md`),
    destinationFile = path.join(actionPath, 'index.md');

  return renderTemplate(actionTemplate, destinationFile, variables);
};

function renderSnippetTemplate(variables, actionPath) {
  const
    snippetTemplate = path.join(templatesPath, 'usage-snippets',`usage.${variables.language}`),
    destinationFile = path.join(actionPath, 'snippets', `${_.kebabCase(variables.action)}.${variables.language}`);

  return renderTemplate(snippetTemplate, destinationFile, variables);
};

function renderSnippetConfigTemplate(variables, actionPath) {
  const
    snippetTemplate = path.join(templatesPath, 'usage-snippets','usage.test.yml'),
    destinationFile = path.join(actionPath, 'snippets', `${_.kebabCase(variables.action)}.test.yml`);

  return renderTemplate(snippetTemplate, destinationFile, variables);
};

function extractFromFile(file, regexpInfo) {
  const content = fs.readFileSync(file, 'utf8')
  let regexp;

  if (regexpInfo.includeStart) {
    regexp = new RegExp(`(${regexpInfo.start}[\\s\\S]*)${regexpInfo.end}`);
  } else {
    regexp = new RegExp(`${regexpInfo.start}([\\s\\S]*)${regexpInfo.end}`);
  }

  const result = content.match(regexp);

  if ((result || []).length === 0) {
    throw new Error(`No match found in ${file} for ${regexp}`)
  }

  return result[1];
}

function injectInFile(file, regexpInfo, injectedContent) {
  const
    content = fs.readFileSync(file, 'utf8'),
    regexp = new RegExp(`${regexpInfo.start}([\\s\\S]*)${regexpInfo.end}`),
    newContent = content.replace(regexp, `${regexpInfo.start}${injectedContent}${regexpInfo.end}`);

  fs.writeFileSync(file, newContent);
}

function explodeSdkPath(fullPath) {
  const [
    language,
    version,
    controller,
    action,
    rest
  ] = (fullPath.split('sdk-reference/')[1] || '').split('/');

  if (! language) {
    // eslint-disable-next-line no-console
    throw new Error('You must provide a language for the action');
  }

  if (! version) {
    // eslint-disable-next-line no-console
    throw new Error('You must provide a version for the action');
  }

  if (! controller) {
    // eslint-disable-next-line no-console
    throw new Error('You must provide a controller for the action');
  }

  if (! action) {
    // eslint-disable-next-line no-console
    throw new Error('You must provide a name for the action');
  }

  return { language, version, controller, action };
}

module.exports = {
  renderMarkdownTemplate,
  renderSnippetTemplate,
  renderSnippetConfigTemplate,
  explodeSdkPath,
  extractFromFile,
  injectInFile,
  showSignatures
};
