# Kuzzle Documentation

Our documentation is a statically generated website. The content is stored in a bunch of Markdown files built by [VuePress](https://vuepress.vuejs.org/).

## Ok, but... Where is the actual content?

This is a meta repository, which means content is not here. The actual documentation is stored in the repositories of the different pieces of the Kuzzle ecosystem, e.g. the [Kuzzle Core](https://github.com/kuzzleio/kuzzle/tree/master/doc/2), the [Javascript SDK](https://github.com/kuzzleio/sdk-javascript/tree/master/doc/7), the [GOLANG SDK](https://github.com/kuzzleio/sdk-go/tree/master/.doc/2) and so on. And this is a good thing because documentation should live along with the code that it documents. So, what is this repository for?

This repository contains the following elements:

- the VuePress logic and plugins that convert a bunch of `.md` files into a multi-rooted single-page-application with server-side-rendering an all those cool features we love;
- the logic that gathers all the different documentations and organizes them in a set of different instances of VuePress;
- the CI configuration that allows GitHub Actions to build and deploy all those different VuePress instances on our hosting space.

We want to stress that each section of the documentation (Core, SDK JS, SDK GO, plugins, etc...) is built against a separate and independent instance of VuePress. All these pieces will be glued together by being hosted in the same S3 bucket but in different sibling directories.

### If you want to edit the content, this is not the right place

This repository is essentially used to version the framework files and deploy the whole docs when necessary. In order to edit the content of the docs, please refer to the repositories that contain them.

## I am working on the framework code and I want to see a preview

If you are working on the code of the documentation framework, this is the right place. And you want a preview of your work before pushing it to GitHub. For most operations of this kind, we use a CLI tool called [kuzdoc](https://github.com/kuzzleio/kuzdoc).

If you haven't already done it, install Kuzdoc globally

```sh
npm install -g kuzdoc
```

:warning: You are not meant to run a local copy of the _whole_ documentation. It is huge and it will take ages to build.

```sh
npm ci
```

Then run

```sh
kuzdoc install --repo=sdk-javascript-6,kuzzle-2
```

This will only clone the `sdk-javascript-6` and `kuzzle-2` repos, choose the ones that you want to work on locally, trying to avoid having too many.

> Note. If you don't remember exactly the name of the repo you want to install, you can omit the `--repo` option and just let kuzdoc show the list of the repo names.

Then you just need to run:

```sh
npm run doc-dev
```

And follow the on-screen instructions. This will start a development server with hot-reload.

## I am writing the docs for a repo and I want to see a preview

If you are writing a new piece of the documentation for let's say, the Javascript SDK v7, or simply editing it, you are likely willing to see how it looks. Here is how you do it with [kuzdoc](https://github.com/kuzzleio/kuzdoc).

If you haven't already done it, install Kuzdoc globally

```sh
npm install -g kuzdoc
```

If you haven't already done it, clone this repo somewhere

```sh
git clone git@github.com:kuzzleio/documentation.git
```

From your local copy of this repository, install the local repo you're working on (SDK JS v7)

```sh
cd documentation
kuzdoc install --repo=sdk-javascript-7 --localPath=../sdk-javascript
```

Passing the `--localPath` option to the `kuzdoc install` command will symlink your local repo into the framework meta-repo instead of cloning it from GitHub.

> Note. If you don't remember exactly the name of the repo you want to install, you can omit the `--repo` option and just let kuzdoc show the list of the repo names.

Once your local repo installed in the framework meta-repo, simply launch

```sh
kuzdoc dev --repo=sdk-javascript-7
```

## I want to see a local preview of a static build of the whole documentation site

You might want to see how the whole docs look before merging stuff to `master`, so [kuzdoc](https://github.com/kuzzleio/kuzdoc) has the right command for you.

If you haven't already done it, install Kuzdoc and `http-server` globally

```sh
npm install -g kuzdoc http-server
```

If you haven't already done it, clone this repo somewhere

```sh
git clone git@github.com:kuzzleio/documentation.git
```

From your local copy of this repository, install all the repos.

```sh
kuzdoc install --repo=__ALL__
```

This wil clone all the repos inside the framework meta-repo.

> Note. If you want to put local repositories inside your preview, you can install them before installing all the repos. `kuzdoc install` will not overwrite repositories that are already installed.

Once all your repos are installed, just launch

```sh
kuzdoc local-deploy
```

The whole docs will be built (this may take a long time) and deployed to `/tmp/kuzzle-docs/`. You then may run 

```sh
http-server /tmp/kuzzle-docs/
```

## I want to add a new repo

Repos that are built in the docs are listed in [`.repos/repositories.yml`](https://github.com/kuzzleio/documentation/blob/master/.repos/repositories.yml). You can either edit it manually or use the kuzdoc wizard

```sh
kuzdoc add-repo
```

... and just answer the questions.

## Content organization

VuePress generates the documentation based on how the files are organized in the filesystem. For example, the URL of each page is direclty infered by its filesystem path relative to `src/`. 

### The frontmatter
The left sidebar generation is based on the filesystem location of the files and their [frontmatter](https://v1.vuepress.vuejs.org/guide/frontmatter.html#front-matter) contents.

A page is defined by a directory (e.g. `src/core/1/api/controllers/admin/dump/`) containing an `index.md` file. This file must have a frontmatter with the following form:

```
---
type: [root|branch|page]
code: [true|false]
title: <Name used in the section list>
order: <Integer> (optional)
description: <(optional) Text appearing under the section name in the section list>
nosidebar: <Boolean> (optional)
---
```

:warning: **No other fields are allowed in the frontmatter**

#### `type` (required)

Defines how this page behaves in the generation of the sidebar. It is also used by other components (like Algolia indexation). Can be the following values:

- `root` - The page is the root of the generation of an entire sidebar (e.g. `src/code/1/api/`);
- `branch` - The page is a branch of the sidebar and generally has no content but has children (e.g. `src/code/1/api/api-reference`, `src/code/1/api/controllers/admin/`);
- `page` - The page is a "leaf" in the sidebar tree: it has no children and has content. It is indexed to Algolia.

#### `code` (required)

A Boolean field defining whether the name of the page must be displayed in monospace typeface in the menu because it indicates the name of a function, a controller or a piece of code in general (e.g. `src/code/1/api/controllers/admin/`).

#### `title` (required)

A String field holding the text to display in the sidebar.

#### `description` (optional)

A String field holding a detailed description of the page. Currently used nowhere (I guess).

#### `order` (optional)

An Integer field indicating how to sort this page in the sidebar. If absent, the page is sorted alphabetically based on the `title` field.

#### `nosidebar` (optional, default: true)

A Boolean field indicating whether the left sidebar should be displayed for the page or not.

#### Frontmatter Linter

The build toolchain runs a linter on the frontmatters. If some frontmatters are invalid, the linter makes the build fail and shows the errors to standard output and dumps them to `frontmatter-errors.js`. Some errors can be automatically fixed: at the end of its report, the linter shows the command to execute to launch the auto-fixer:

```sh
$(npm bin)/frontmatter-fix -e frontmatter-errors.js
```

You can learn more about the linter by looking at its [official repository](https://github.com/xbill82/vuepress-frontmatter-lint).

### The sections
Many other dynamic menus are generated from the [`src/.vuepress/sections.json`](https://github.com/kuzzleio/documentation/blob/master/src/.vuepress/sections.json) file, which consists in a hashmap of sections of this form

```json
{
  "/sdk/go/3/": {
    "kuzzleMajor": 2,
    "section": "sdk",
    "subsection": "go",
    "name": "Golang",
    "version": 3,
    "icon": "/logos/go.svg",
    "released": true
  }
}
```

The shape of each section is defined in a specific [JSON Schema file](https://github.com/kuzzleio/documentation/blob/master/src/.vuepress/sections.schema.json).

#### Path
The path to the section in the docs is the key of the element, which prevents from defining the same section twice.

#### `name` (required)
The name of the section, mainly use to build the widgets (like the SDK selector, for example).

#### `kuzzleMajor` (required)
Defines the major version of Kuzzle this section belongs to (relates to the major version selector).

#### `section` (required)
The ID of the section, used when building lists of sections. If the current section is the child of a parent section, this field indicates the name of the parent section. I am very sorry for this horrible naming, I promise I will rename this to `parent` and make it optional soon.

#### `subsection` (optional)
If the section is the child of another section, this field contains the name of the current section. OMG I'm so sorry I'll fix it I promise.

#### `released` (required)
Defines if the section is released or not (the section appears in the menus and indexes if `true`).

#### `version` (optional)
If the documented product in the current section has a version, here it goes (like the v7 of the JS SDK v7).

#### `icon` (optional)
If the documented product has an icon, this field contains the path.

#### `closedSource` (optional)
A boolean indicating whether the documented product is part of Kuzzle Enterprise or not.

#### `deprecated` (optional)
Defines if the section shows a header banner saying it is deprecated.

#### `deprecatedBannerComponent` (optional)
Contains the name of the name of the banner component indicating the section is deprecated.

#### I want to add a new section
You can manually edit `sections.json` or use the kuzdoc wizard

```sh
kuzdoc add-section
```

... and answer the questions.

## Custom containers

### alert/info boxes

You can create alert/info boxes in your markdown with the following syntax:

```markdown
::: info
lorem ipsum
:::
```

Supported containers are : `info`, `success`, `warning`

### Tabs

It is possible to add tabs directly in the markdown with this syntax:

```markdown
:::: tabs
::: tab Java

<<< ./snippets/check-token.java

:::
::: tab Kotlin

<<< ./snippets/check-token.kt

:::
::::
```

Library used: https://superbiger.github.io/vuepress-plugin-tabs/#documents

### IconTable

You can create a list with icons inside a Markdown file with the `IconTable` CSS class, like in the following example.

```html
<div class="IconTable">
  <div class="IconTable-item">
    <div class="IconTable-item-icon">
      <img src="./feature-data-storage.svg" />
    </div>
    <div class="IconTable-item-text">
      Data storage and access
    </div>
  </div>
  <div class="IconTable-item">
    <div class="IconTable-item-icon">
      <img src="./feature-acl.svg" />
    </div>
    <div class="IconTable-item-text">
      Advanced permission system
    </div>
  </div>
</div>
```

The path of the icon file is relative to the current Markdown file.

:warning: `div.IconTable-item` elements are `display: inline-block`, which means you cannot add any whitespace in the markdown between them: that's why the closing and opening tag are on the same line `</div><div class="IconTable-item">`. To add a newline for better readability, you can do

```html
</div><!--
--><div class="IconTable-item">
```

### GuidesLinks

The GuidesLinks component allows to display previous and next links:

```
<GuidesLinks :prev="{text: 'what is kuzzle', url: '/foo'}" :next="{text: 'Store and Access Data', url: '/bar'}"/>
```

### Custom badge

```
<CustomBadge type="tip | warning | error" text="your text here"/>
```

## Code snippet import

You can [import code snippets from file](https://v1.vuepress.vuejs.org/guide/markdown.html#import-code-snippets), as supported by VuePress, with the following syntax in your Markdown:

```markdown
<<< @/filepath
```

We extended this feature by making it support relative paths. For example, if you have the following files:

```
- /core/1/guides/getting-started/first/steps/
  |
  +- snippets/
  |  |
  |  +- create.js
  |
  +- index.md
```

In your `index.md` file, you can import the `create.js` snippet by writing

```
<<< ./snippets/create.js
```

### Partial code snippet extraction

You can also use special tags to import specific parts of your snippet file. For instance, say your snippet file is like the following:

```javascript
// load the Kuzzle SDK module
import { Kuzzle, WebSocket } from 'kuzzle-sdk';

// instantiate a Kuzzle client
const kuzzle = new Kuzzle(new WebSocket('kuzzle'));

// add a listener to detect any connection problems
kuzzle.on('networkError', error => {
  console.error(`Network Error: ${error}`);
});

const run = async () => {
  try {
    // Connect to Kuzzle server
    await kuzzle.connect();

    // Create an index
    await kuzzle.index.create('nyc-open-data');

    // Create a collection
    await kuzzle.collection.create('nyc-open-data', 'yellow-taxi');
    console.log('nyc-open-data/yellow-taxi ready!');
  } catch (error) {
    console.error(error.message);
  } finally {
    kuzzle.disconnect();
  }
};

run();
```

But you only want to import the code of the `run` function. You can use the special `snippet:start` and `snippet:end` comments for this purpose:

```javascript
// load the Kuzzle SDK module
import { Kuzzle, WebSocket } from 'kuzzle-sdk';

// instantiate a Kuzzle client
const kuzzle = new Kuzzle(new WebSocket('kuzzle'));

// add a listener to detect any connection problems
kuzzle.on('networkError', error => {
  console.error(`Network Error: ${error}`);
});

/* snippet:start */
const run = async () => {
  try {
    // Connect to Kuzzle server
    await kuzzle.connect();

    // Create an index
    await kuzzle.index.create('nyc-open-data');

    // Create a collection
    await kuzzle.collection.create('nyc-open-data', 'yellow-taxi');
    console.log('nyc-open-data/yellow-taxi ready!');
  } catch (error) {
    console.error(error.message);
  } finally {
    kuzzle.disconnect();
  }
};
/* snippet:end */

run();
```

This way, only the code between the tags will be included in the MD file.

Snippet tags can also bear an ID, so that you can have many of them inside your snippet file, like

```javascript
// load the Kuzzle SDK module
import { Kuzzle, WebSocket } from 'kuzzle-sdk';

// instantiate a Kuzzle client
/* snippet:start:1 */
const kuzzle = new Kuzzle(new WebSocket('kuzzle'));
/* snippet:end */

// add a listener to detect any connection problems
kuzzle.on('networkError', error => {
  console.error(`Network Error: ${error}`);
});

/* snippet:start:2 */
const run = async () => {
  try {
    // Connect to Kuzzle server
    await kuzzle.connect();

    // Create an index
    await kuzzle.index.create('nyc-open-data');

    // Create a collection
    await kuzzle.collection.create('nyc-open-data', 'yellow-taxi');
    console.log('nyc-open-data/yellow-taxi ready!');
  } catch (error) {
    console.error(error.message);
  } finally {
    kuzzle.disconnect();
  }
};
/* snippet:end */

run();
```

This way, you can select which snippet you want to include by using the following syntax in you MD file

```
<<< ./snippets/create.js:1
```

The code above will include only

```javascript
const kuzzle = new Kuzzle(new WebSocket('kuzzle'));
```

### Forcing snippet language

In some cases, you might want to force the snippet language, e.g. when importing a partial script from a `.vue` file: in this case, the automatic syntax detection would try to highlight some Javascript code as a VueJS template, which is obviously not what you want.

To force the language highlight, you can use the following syntax

<<< ./snippets/create.vue:1[js]

## Testing code snippets

Because we want our documentation to be bullet-proof, we created a snippet testing tool.

To locally test your snippets, first run a Kuzzle stack:

```sh
bash .ci/start_kuzzle.sh
```

Then you can run snippets for any language:

```bash
# Execute all snippets under the repertory 'src/sdk/js/6'
bash run-snippet-tests.sh -n -s js -v 6 -p src/sdk/js/6
# Execute all snippets for the controller index in SDK CPP 1
bash run-snippet-tests.sh -n -s cpp -v 1 -p src/sdk/cpp/1/controllers/index
```

If you want to avoid downloading the SDK each time you run a snippet, you can use the following variable:

```bash
export DEV_MODE=true
# The following command will download the cpp SDK only if it does not already exist
bash run-snippet-tests.sh -n -p src/sdk/cpp/1/controllers/index
```

### Writing tests

To make a snippet testable, simply create a YML file called `<snippet-name>.test.yml` along with the snippet file, like the following:

```
- /core/1/guides/getting-started/first/steps/
  |
  +- snippets/
  |  |
  |  +- create.js
  |  +- create.test.yml
  |
  +- index.md
```

The `create.test.yml` file of this example would look as follows:

```yaml
name: Create document
description: Create a document in collection
hooks:
  before:
  after:
template: default
expect: document created successfully
```

### Snippet templates

Since code snippets often lack of support and cannot be executed as-is, we use templates to recreate the context of a snippet.

Templates are located in `test/templates`.

A template is a code file containing a special `[snippet-code]` tag that is parsed by the test runner: the runner replaces this tag with the actual code of the snippet. This way, the snipped is injected in its context and can be effectively tested.

Example of a default template for the Javascript SDK:

```js
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
    return [snippet - code];
  })
  .then(() => {
    kuzzle.disconnect();
  });
```

You can add your own template, just respect the naming rule : `template_name.tpl.ext`

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
./scaffolding/scaffold generate src/sdk/js/6/controllers/collection/list
```

### Copy an existing action from another SDK

You can also copy an action from another SDK to save time.
This command extracts information from an existing action in another SDK and generates the correct files for another SDK.

Example:

```bash
# Copy information from SDK JS 6 to SDK CPP 1
./scaffolding/scaffold copy src/sdk/js/6/controllers/collection/list src/sdk/cpp/1/controllers/collection/list
```

### Adding a new SDK

To add a new SDK you will need to create a ew folder inside `src/sdk` and to create a simlink of it's version:

```sh
cd src/sdk
mkdir my-sdk
ln -s ../../../.repos/my-sdk-1/doc/1
```

Then add it to `.repos/repositories.yml`
