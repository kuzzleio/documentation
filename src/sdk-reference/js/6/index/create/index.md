---
layout: sdk.html.hbs
algolia: true
title: create
description: Create a new index
---

# create

Create a new `<index>` in Kuzzle.

## Arguments

```javascript
create (index, options = null)
```

<br/>

| Arguments | Type   | Description      |
| --------- | ------ | ----------------- |
| `index`   | <pre>string</pre> | Index name        |
| `options` | <pre>object</pre> | Query options |

### **options**

Additional query options

| Property     | Type    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`  |

## Resolve

Resolves to an `object` containing the index creation status

| Name                | Type    | Description                                                                                                       |
| ------------------- | ------- | ----------------------------------------------------------------------------------------------------------------- |
| `acknowledged`        | <pre>boolean</pre> | Indicates whether the index was successfully created in the Elastic cluster                                       |
| `shards_acknowledged` | <pre>boolean</pre> | Indicates whether the requisite number of shard copies were started for each shard in the index before timing out |

## Usage

[snippet=create]
