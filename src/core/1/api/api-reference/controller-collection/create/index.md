---
code: true
type: page
title: create
---

# create



Creates a new [collection](/core/1/guide/guides/essentials/persisted/), in the provided `index`.

<SinceBadge version="1.3.0" />

You can also provide an optional body with a data mapping that allow you to exploit the full capabilities of our persistent data storage layer.

This method will only update the mapping if the collection already exists.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>
Method: PUT
Body:
```

```js
{
  "properties": {
    "field1": {
      "type": "integer"
    },
    "field2": {
      "type": "keyword"
    },
    "field3": {
        "type":   "date",
        "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"
    }
  }
}
```

### Other protocols

```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "collection",
  "action": "create",
  "body": {
    "properties": {
      "field1": {
        "type": "integer"
      },
      "field2": {
        "type": "keyword"
      },
      "field3": {
          "type":   "date",
          "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"
      }
    }
  }
}
```

---

## Arguments

- `collection`: data collection to create
- `index`: data index that will host the new data collection

---

## Body properties

### Optional:

- `properties`: object describing the data mapping to associate to the new collection, using [Elasticsearch mapping format](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/mapping.html).

---

## Response

Returns a confirmation that the collection is being created:

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "collection",
  "action": "create",
  "requestId": "<unique request identifier>",
  "result": {
    "acknowledged": true
  }
}
```

---

## Possible errors

- [Common errors](/core/1/api/essentials/errors/#common-errors)
- [PreconditionError](/core/1/api/essentials/errors/#preconditionerror)
