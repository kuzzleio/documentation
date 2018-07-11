# Documentation-V2

## Usage

> install dependencies

`npm install`

> index documentation content to algolia

`node index.js --algolia-private-key <key>`

> index documentation content to algolia matching version configured in versions.config.json

`node index.js --build-path /v/edge --algolia-private-key <key>`

> build the documentation in production mode matching version configured in versions.config.json

`node index.js --build-host http://docs.kuzzle.io --build-path /v/edge --build-compress`

> bind a webserver on 3000 with livereload and watch enabled

`npm run dev`

---

## File organization

Here is an overview of the files structure:

* `src/`: documentation entry point
* `src/<section>/` (for instance: `src/guide/`, entry point of the Guide documentation section)
* `src/<section>/<subsection>/` (for instance: `src/guide/essentials/`)
* `src/<section>/<subsection>/<article>.md` (for instance: `src/guide/essentials/installing-kuzzle.md`)


Though there is no real limit to the directories depth, to keep the documentation homogeneous and readable, no additional subdirectories should be added.

## Documentation pages

Pages are listed as subdirectories in `src/`.  
For instance: `src/guide/`.

Each page directory must contain an `index.md` file, with the following headers:

```

---
layout: category-childrens.html
title: <Name used in the section list>
order: <(optional, integer)>
description: <(optional) Text appearing under the section name in the section list>
---
```

## Adding code example

It's possible to add code example (for each languages supported for SDKs) in markdown, before doing that, you have to create a directory `code-example` at the same level of the page you are editing. In this directory put all your code example files.

EX: `createDocument.js` / `createDocument.go` / `...`

Now in markdown just add `[code-example=createDocument]` where you want. When metalsmith will build, the code-example tag will be remplaced by the code in each code example files.

## Override markdown

Because each languages supported for SDKs can have specifications, It's possible to override markdown.
Like code-example, create a subfolder `sections` and put markdown files in it.

EX: `createDocument_js.md` / `createDocument_go.md` / `createDocument_default.md` / `...`

Please note that `_language` is important for build process in metalsmith.

Now you can add this tag in your markdown to allow metalsmith to override parts of markdown : `[section=createDocument]`

## Configuration file

`config.yml` at the root of the project :

```yaml
code_example:
  snippet_folder_name: code-example
  section_folder_name: sections
languages:
  js:
    fullname: javascript
    ext: js
    sdk_url: https://github.com/kuzzleio/sdk-javascript.git
    sdk_branch: master
  go:
    fullname: go
    ext: go
    sdk_url: https://github.com/kuzzleio/sdk-go.git
    sdk_branch: 1.x
```

## Writting tests

To write tests for code-example, you have to put an YAML file in front of snippets file with the same name of the snippet you want to test

```yaml
name: Create document
description: Create a document in collection
hooks:
  before:
  after:
template: default
expect: document created successfully
```

Templates are located in `test/templates` and you have to put the `[snippet-code]` tag to automatically inject snippet in the template when tests are lauched.

exemple of default template in JS :

```javascript
// load the Kuzzle SDK module
const Kuzzle = require('kuzzle-sdk').Kuzzle;

// instantiate a Kuzzle client
const kuzzle = new Kuzzle('websocket', { host: 'kuzzle', autoReconnect: false });

// add a listener to detect any connection problems
kuzzle.on('networkError', error => {
  console.error('Network Error:' + error);
})

kuzzle.connect()
  .then(() => {
    return [snippet-code]
  })
  .then(() => {
    kuzzle.disconnect();
  });
```

You can add your own template, just respect the naming rule : `template_name.tpl.ext`


## Testing code-example

Every time a pull request is made, a build is launch with Travis and a comment is added to the PR with the URLs of the tests reports (by language).

It's possible to play test locally by running at the root of the project `$ sh run_test.sh -l the_language_you_want` (js, go, ...). this will launch a kuzzle stack, and play all the tests for the language specified in an appropriate container and generate a report served locally to http://localhost:3001/reports .
