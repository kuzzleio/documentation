---
layout: sdk.html.hbs
algolia: true
title: create
description: Create an index
---

# create

Create a new index in Kuzzle.

## Arguments

```javascript
create (index, [options])
```

<br/>

| Arguments | Type   | Description      |
| --------- | ------ | ----------------- |
| `index`   | <pre>string</pre> | Index name        |
| `options` | <pre>object</pre> | Query options |

### **options**

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |

## Resolves

Resolves to an `object` containing the index creation status

| Name                | Type    | Description                                                                                                       |
| ------------------- | ------- | -------------------------------------------------------------- |
| `acknowledged`        | <pre>boolean</pre> | Indicates whether the index was successfully created in the Elastic cluster                                       |
| `shards_acknowledged` | <pre>boolean</pre> | Indicates whether the requisite number of shard copies were started for each shard in the index before timing out |

## Usage

[snippet=create]
