---
layout: full.html.hbs
algolia: true
title: zadd
---

# zadd

{{{since "1.0.0"}}}

Add elements to a sorted set. 

If the key does not exist, it is created, holding an empty sorted set. 

If the key already exists but does not hold a sorted set, an error is returned.

Scores are expressed as floating point numbers.

If a member to insert is already in the sorted set, its score is updated and the member is reinserted at the right position in the set.

[[_Redis documentation_]](https://redis.io/commands/zadd)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_zadd/<_id>
Method: POST  
Body:
```

```js
{
  "elements": [
    {"score": "<score1>", "member": "<value1>"},
    {"score": "<score2>", "member": "<value2>"},
    {"score": "<...>", "member": "<...>"}
  ]
}
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "zadd",
  "_id": "<key>",
  "body": {
    "elements": [
      {"score": "<score1>", "member": "<value1>"},
      {"score": "<score2>", "member": "<value2>"},
      {"score": "<...>", "member": "<...>"}
    ]
  }
}
```

---

## Arguments

* `_id`: sorted set identifier

---

## Body properties

* `elements`: an array of objects. Each object describes a sorted set member, using the following properties:
  * `member`: member value
  * `score`: member score

### Optional:

* `ch` (boolean): if true, instead of returning the number of added elements, return the number of changes performed
* `incr` (boolean): if true, instead of adding elements, increments the existing member with the provided `score`. Only one score-element pair can be specified if this option is set
* `nx` (boolean): if true, only add new elements, do not update existing ones
* `xx` (boolean): if true, never add new elements, update only existing ones

---

## Response

Return the number of added elements.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zadd",
  "collection": null,
  "index": null,
  "result": 6
}
```
