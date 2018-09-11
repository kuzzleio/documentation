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

- `src/`: documentation entry point
- `src/<section>/` (for instance: `src/guide/`, entry point of the Guide documentation section)
- `src/<section>/<subsection>/` (for instance: `src/guide/essentials/`)
- `src/<section>/<subsection>/<article>.md` (for instance: `src/guide/essentials/installing-kuzzle.md`)

For the SDKs:

- `src/sdk-reference/<language>/<version>/<controller>/<action>`

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

To add alert box just put in your markdown :

```html
  <div class="alert alert-info">
    lorem ipsum
  </div>
```

supported classes are : `alert-infos`, `alert-success`, `alert-warning`

## Adding code example

It's possible to add code example (for each languages supported for SDKs) in markdown, before doing that, you have to create a directory `snippets` at the same level of the page you are editing. In this directory put all your code example files.

EX: `createDocument.js` / `createDocument.go` / `...`

Now in markdown just add `[snippet=createDocument]` where you want. When metalsmith will build, the snippet tag will be remplaced by the code in each code example files.

## Override markdown

Because each languages supported for SDKs can have specifications, It's possible to override markdown.
Like snippet, create a subfolder `sections` and put markdown files in it.

EX: `createDocument_js.md` / `createDocument_go.md` / `createDocument_default.md` / `...`

Please note that `_language` is important for build process in metalsmith.

Now you can add this tag in your markdown to allow metalsmith to override parts of markdown : `[section=createDocument]`

## Configuration file

`config.yml` at the root of the project :

```yaml
code_example:
  snippet_folder_name: snippet
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

## Writing tests

To write tests for snippet, you have to put an YAML file in front of snippets file with the same name of the snippet you want to test

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
const { Kuzzle } = require('kuzzle-sdk');

// instantiate a Kuzzle client
const kuzzle = new Kuzzle('websocket', {
  host: 'kuzzle',
  autoReconnect: false
});

kuzzle
  .connect()
  .then(() => {
    return [snippet-code];
  })
  .then(() => {
    kuzzle.disconnect();
  });
```

You can add your own template, just respect the naming rule : `template_name.tpl.ext`

## Testing the snippets locally

It's possible to play test locally by running at the root of the project

```bash
   bash run-snippet-tests.sh -l <language>
```

Where `<language>` specifies the language to test (currently available languages are `js`, `go`, `cpp` and `java`). This will launch a Kuzzle stack, and play all the tests for the language specified in an appropriate container and generate a report served locally to http://localhost:3001/reports .

There are more available options. Using `-n` will prevent the script to launch the Kuzzle stack:

```bash
   bash run-snippet-tests.sh -l <language> -n
```

This is handy if you launch many times the tests and keep the stack running on the background.

You can also specify a single test to be run using the `-f` option:

```bash
   bash run-snippet-tests.sh -l <language> -f <path>
```

Where `<path>` specifies the path to the `.yml` test description, relative to `$PWD/src/sdk-reference`.

The following example launches a single test in Javascript using the running Kuzzle stack:

```bash
   bash run-snippet-tests.sh -n -l js -f index/snippet/delete.yml
```

## Scaffolding tool

You can use the scaffolding tool to create the files needed when you want to document a new controller action.  

This tool take the path of your new action as an argument an create the following files:
  - `<language>/<version>/<controller>/<action>/index.md`: File describing your action with the right subsections according to the language
  - `<language>/<version>/<controller>/<action>/snippets/<action>.test.yml`: The configuration file explaining the snippet
  - `<language>/<version>/<controller>/<action>/snippets/<action>.<language>`: The snippet demonstrating the action

Example:
```bash
# Create the files to document the action 'list' of the controller 'document' for the SDK JS 6
./scaffolding/scaffold generate src/sdk-reference/js/6/collection/list
```
