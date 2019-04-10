# Documentation

## Usage

> install dependencies

`npm install`

> index documentation content to algolia

`node index.js --algolia-private-key <key>`

> index documentation content to algolia matching version configured in config/versions.json

`node index.js --build-path /v/edge --algolia-private-key <key>`

> build the documentation in production mode matching version configured in config/versions.json

`node index.js --build-host http://docs.kuzzle.io --build-path /v/edge --build-compress`

> bind a webserver on 3000 with livereload and watch enabled

`npm run dev`

---

## File organization

Here is an overview of the files structure:

- `src/`: documentation entry point
- `src/<section>/` (for instance: `src/guide/`, entry point of the Guide documentation section)
- `src/<section>/<subsection>/` (for instance: `src/guide/1/essentials/`)
- `src/<section>/<subsection>/<article>.md` (for instance: `src/guide/1/essentials/installing-kuzzle.md`)

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

## Configuration files

### Metalsmith general configuration

Metalsmith itself is configured using the `config/metalsmith.json` file.

By default all directories under `src/` are consumed by metalsmith. The `exclude` configuration allows to skip some directories. This is especially useful when preparing future major versions.

Example: preventing the JS SDK version 6 to be included in the documentation

```json
{
  "exclude": [
    "**/sdk-reference/js/6"
  ]
}
```

### API versions description

See `config/versions.json`

## Writing tests

To test snippets, you have to create a YAML configuration file, named after the snippet file to be tested.

Example: a YAML file named `create.test.yml` used to test the `create.js` snippet file:

```yaml
name: Create document
description: Create a document in collection
hooks:
  before:
  after:
template: default
expect: document created successfully
```

Templates are located in `test/templates` and you have to put the `[snippet-code]` tag to automatically inject snippet in the template when tests are launched.

Example of a default template for the Javascript SDK:

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

You can test the snippets locally by using the script `run-snippet-tests.sh`.
This script looks recursively for snippets to test, using the path provided as an argument.
You must at least have a sdk language and version in the provided path: `src/sdk-reference/<language>/<version>[/path/to/snippets]`

First, you have to run a Kuzzle stack with the following script: `bash .ci/start_kuzzle.sh`

Then you can run snippets for any language:

```bash
# Execute all snippets under the repertory 'src/sdk-reference/js/6'
bash run-snippet-tests.sh -n -s js -v 6 -p src/sdk-reference/js/6
# Execute all snippets for the controller index in SDK CPP 1
bash run-snippet-tests.sh -n -s cpp -v 1 -p src/sdk-reference/cpp/1/index
```

If you want to avoid downloading the SDK each time you run a snippet, you can use the following variable:

```bash
export DEV_MODE=true
# The following command will download the cpp SDK only if it does not already exist
bash run-snippet-tests.sh -n -p src/sdk-reference/cpp/1/index
```

## Scaffolding tool

### Create a new controller action documentation

You can use the scaffolding tool to initialize a new SDK controller action documentation, from its Kuzzle API counterpart.

This tool takes the path of your new action as an argument and creates the following files:

  - `<language>/<version>/<controller>/<action>/index.md`: controller action documentation
  - `<language>/<version>/<controller>/<action>/snippets/<action>.test.yml`: configuration file for the snippet
  - `<language>/<version>/<controller>/<action>/snippets/<action>.<language>`: snippet file

Example:

```bash
# Create the files documenting the action 'list' of the controller 'document' for the SDK JS 6
./scaffolding/scaffold generate src/sdk-reference/js/6/collection/list
```

### Copy an existing action from another SDK

You can also copy an action from another SDK to save time.
This command extracts information from an existing action in another SDK and generates the correct files for another SDK.

Example:
```bash
# Copy information from SDK JS 6 to SDK CPP 1
./scaffolding/scaffold copy src/sdk-reference/js/6/collection/list src/sdk-reference/cpp/1/collection/list
```
