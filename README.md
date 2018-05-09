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

> bind a webserver on 8080 with livereload and watch enabled

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
It's possible to add code example (for each languages supported for SDKs) in markdown, before doing that, you have to create a directory `code-example` in the section you are editing. In this directory put all your code example files.

EX: `createDocument.js` / `createDocument.go` / `...`

Now in markdown just add `[code-example=createDocument]` where you want. When metalsmith will build, the code-example tag will be remplaced by the code in each code example files.

## Override markdown
Because each languages supported for SDKs can have specifications, It's possible to override markdown.
Like code-example, create a subfolder `sections` and put markdown files in it.

EX: `createDocument_js.md` / `createDocument_go.md` / `createDocument_default.md` / `...`

Please note that `_language` is important for build process in metalsmith (so please, don't add another `_` in filename).

Now you can add this tag in your markdown to allow metalsmith to override parts of markdown : `[section=createDocument]`
