const fs = require('fs');

const path = process.argv[2];

if (! path || path === '') {
  console.log('You must provide a path for the code example');
  console.log(`> node ${process.argv[1]} <path> <example name>`);
  process.exit(1);
}

if (! fs.existsSync(path)) {
  console.log(`Path ${path} does not exists`);
  process.exit(1);
}

const exampleName = process.argv[3];

if (! exampleName || exampleName === '') {
  console.log('You must provide an example name for the code example');
  console.log(`> node ${process.argv[1]} <path> <example name>`);
  process.exit(1);
}




['js', 'go', 'cpp', 'java'].forEach(language => {
  fs.writeFile(`${path}/${exampleName}.${language}`, '//@todo', error => {
    if (error) {
      console.log(`Error for ${language} example: ${error.message}`);
    }
  });
})

const yamlContent = `name: ${exampleName}
description:
hooks:
  before:
  after:
template: default
expect: something
`;

fs.writeFileSync(`${path}/${exampleName}.yml`, yamlContent)
