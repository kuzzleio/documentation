---
layout: sdk.html
algolia: true
title: replace
description: Replace a document
---


# replace

Replaces the content of an existing document.

## Arguments

```javascript
replace (index, collection, id, document, [options])
```

| Argument | Type | Description |
| | _id | <pre>string</pre> | ID of the newly created document
| _version | <pre>number</pre> | Version of the document in the persistent data storage
| result | <pre>string</pre> | Set to `updated` in case of success

## Usage

[snippet=replace]
