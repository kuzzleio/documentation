---
code: true
type: page
title: create
description: Create a new collection
---

# create

Creates a new [collection](/core/1/guides/essentials/store-access-data/) in the provided `index`.
You can also provide an optional data mapping that allow you to exploit the full capabilities of our
persistent data storage layer, [ElasticSearch](https://www.elastic.co/products/elasticsearch) (check here the [mapping capabilities of ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/mapping.html)).

This method will only update the mapping if the collection already exists.

## Signature

```go
Create(index string, collection string, mapping json.RawMessage, options types.QueryOptions) error
```

## Arguments

| Arguments    | Type            | Description                            | Required |
| ------------ | --------------- | -------------------------------------- | -------- |
| `index`      | string          | Index name                             | yes      |
| `collection` | string          | Collection name                        | yes      |
| `mapping`    | json.RawMessage | Collection data mapping in JSON format | no       |
| `options`    | QueryOptions    | Query options                          | no       |

### **mapping**

An string containing the JSON representation of the collection data mapping.

The mapping must have a root field `properties` that contain the mapping definition:

```json
{
  "properties": {
    "field1": { "type": "text" },
    "field2": {
      "properties": {
        "nestedField": { "type": "keyword" }
      }
    }
  }
}
```

More informations about database mappings [here](/core/1/guides/essentials/database-mappings).

### **options**

Additional query options

| Property   | Type | Description                       | Default |
| ---------- | ---- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Return an error or `nil` if collection successfully created.

## Usage

<<< ./snippets/create.go
