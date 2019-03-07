---
layout: full.html.hbs
title: loadFixtures
---

# loadFixtures

{{{since "1.6.0"}}}

Load fixtures into the storage layer.

**Notes:**

* The fixtures can contain any number of index and collection configurations.
* Each collection contains an array of data to load, just like the [bulk:import API]({{ site_base_path }}api/1/controller-bulk/import/).
* If an index or collection does not exist, the load will fail.
* Fixtures are loaded sequentially, one index/collection pair at a time. If a failure occurs, Kuzzle immediately interrupts the sequence.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/admin/_loadFixtures[?refresh=wait_for]
Method: POST
Body:
```

```js
{
  "index": {
    "collection": [
      {"<command>": {}},
      {"field": "value", "field2": "value", "field...", "value"}
    ]
  }
}
```


### Other protocols


```js
{
  "controller": "admin",
  "action": "loadFixtures",
  "body": <fixtures>
}
```

## Arguments

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the fixtures are loaded

---

## Response

Returns a confirmation that the command is being executed.

```js
{
  "requestId": "d16d5e8c-464a-4589-938f-fd84f46080b9",
  "status": 200,
  "error": null,
  "controller": "admin",
  "action": "loadFixtures",
  "collection": null,
  "index": null,
  "result": { "acknowledge": true }
}
```
