const
  path = require('path'),
  globby = require('globby').sync,
  readYaml = require('read-yaml');

const SECTION_REGEX = /\[section=([a-zA-Z0-9-]+)\]/g;

const sdkVersions = readYaml.sync(path.join(__dirname, '../test/sdk-versions.yml'));

module.exports = function plugin () {
  const process = function process (file, files) {
    const content = files[file].contents.toString();

    let has_section = false;
    let sections = [];

    const out = content.replace(SECTION_REGEX, (match, name) => {
      let replacement = '';

      for (const fn of globby(path.join(__dirname, `../src/${path.dirname(file)}/${name}*`))) {
        has_section = true;
        sections.push(name);

        const lng = fn.replace(/^.*\.([^.]+)\.[^.]+$/, '$1');
        const key = `${path.dirname(file)}/${path.basename(fn).replace(/\.[^.]+$/, '')}.html`;

        replacement += `<div id="${name}" class="section ${sdkVersions[lng].fullname}">
              ${files[key].contents.toString()}
          </div>`;
      }

      return replacement;
    });

    return {
      has_section,
      sections,
      contents: Buffer.from(out)
    };
  };

  return (files, metalsmith, done) => {
    for (const file of Object.keys(files)) {
      if (file.endsWith('index.html')) {
        const processed = process(file, files);

        files[file].contents = processed.contents;
        files[file].has_section = processed.has_section;
        files[file].sections = processed.sections.join(',');
      }
    }

    setImmediate(done);
  };
};

