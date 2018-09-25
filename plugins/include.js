const
  path = require('path');

const INCLUDE_REGEX = /<p>\[include=(.*?)\]<\/p>/;

module.exports = function plugin () {
  const include = (file, files) => {
    const content = files[file].contents.toString();

    return Buffer.from(content.replace(INCLUDE_REGEX, (match, name) => {
      if (name.endsWith('.md')) {
        name = name.replace(/\.md$/, '');
      }

      const fn = path.join(`${path.dirname(file)}/${name}.html`);
      return files[fn].contents.toString();
    }));
  };

  return function main (files, metalsmith, done) {
    for (const file of Object.keys(files)) {
      if (file.endsWith('.html')) {
        files[file].contents = include(file, files);
      }
    }

    setImmediate(done);
  };
};
