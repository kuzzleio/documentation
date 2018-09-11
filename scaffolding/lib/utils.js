const
  fs = require('fs'),
  ejs = require('ejs'),
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

function renderTemplate(source, destination, locals) {
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

module.exports = {
  renderTemplate
};
