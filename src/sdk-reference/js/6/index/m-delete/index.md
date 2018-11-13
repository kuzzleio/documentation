---
layout: sdk.html.hbs
algolia: true
title: mDelete
description: Deletes multiple indexes
---

# mDelete

Deletes multiple indexes at once.

## Arguments

```javascript
mDelete (indexes, [options]);
```

<br/>

| Arguments | Type   | Description                                  |
| --------- | ------ | -------------------------------------------- |
| `indexes` | <pre>string[]</pre>  | List of index names to delete |
| `options` | <pre>object</pre> | Query options          |

### options

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |

## Resolves

Resolves to an `array<string containing the list of indexes names successfully deleted.

## Usage

[snippet=mDelete]
