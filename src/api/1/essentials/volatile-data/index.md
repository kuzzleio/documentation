---
layout: full.html.hbs
algolia: true
title: Volatile Data
description: broadcast extra information to subscribers
order: 400
algolia: true
---

# Volatile Data

All queries accept a `volatile` object parameter (see the [query syntax]({{site_base_path}}api/1/query-syntax) documentation).

The content of this object is not meant to be used directly: it has no impact on the query itself.

Still, volatile data are not completely ignored by Kuzzle, and they have a few uses.

---

## Query Context

Volatile data can be used to provide additional context about a query; this allows extended logs, application metadata, and so on. Many use cases benefit from being able to pass context data, without any direct impact to queries themselves.

Moreover, plugins receive the complete query made by a user, including volatile data.

Lastly, if a query triggers [document notifications]({{ site_base_path }}api/1/notifications/#documents-changes-messages-default), then its volatile data are included in the notifications content. This allows real-time subscribers to get elements of context about changes made to documents, if needs be.

### Example:

The following [document:update]({{ site_base_path }}api/1/controller-document/update) query:

```javascript
{
  "index": "foo",
  "collection": "bar",
  "controller": "document",
  "action": "update",
  "_id": "documentUniqueId",
  "body": {
    "somefield": "now has a new value"
  },
  "volatile": {
    "reason": "applied suggestion made by soandso"
  }
}
```

Can trigger the following document notification, sent to real-time subscribers:

```javascript
{
  "index": "foo",
  "collection": "bar",
  "controller": "document",
  "type": "document",
  "action": "update",
  "state": "done",
  "scope": "in",
  "volatile": {
    "reason": "applied suggestion made by soandso"
  },
  "result": {
    "_id": "documentUniqueId",
    "_source": {
      "somefield": "now has a new value",
      "someOtherField": "was left unchanged"
    }
  }
}
```

---

## Real-time subscription context

There is one special case, where volatile data are stored by Kuzzle for a later use, instead of being completely ignored: whenever a user make a new real-time subscription.

Volatile data passed to a new subscription query are used two times by Kuzzle:

* if the new subscription triggers [user notifications]({{ site_base_path }}api/1/notifications/#user-events-default), its volatile data are included into those
* if that subscription is cancelled, whether because of a call to [realtime:unsubscribe]({{ site_base_path }}api/1/controller-realtime/unsubscribe), or after the user disconnects: the volatile data provided **at the time of the subscription** are once again copied into user notifications triggered by that event

This allows other real-time subscribers to get context information about a user joining or leaving the same subscription as them.

### Example: 

Here is a new subscription query, with volatile data provided:

```javascript
{
  "index": "foo",
  "collection": "bar",
  "controller": "realtime",
  "action": "subscribe",
  "body": {
    "equals": {
      "room": "global_chat"
    }
  },
  "volatile": { 
    "fullname": "Alan Smithee",
    "profession": "Film Director"
  }
}
```

If other users subscribed to the same filter, they will receive the following user notification:

```javascript
{
  "type": "user",
  "index": "foo",
  "collection": "bar",
  "controller": "realtime",
  "action": "subscribe",
  "user": "in",
  "volatile": {
    "fullname": "Alan Smithee",
    "profession": "Film Director"
  },
  "result": {
    "count": 42
  }
}
```

Now, if that user disconnects (either on purpose or unexpectedly), the following notification is sent to the remaining users sharing that filter:

```javascript
{
  "type": "user",
  "index": "foo",
  "collection": "bar",
  "user": "out",
  "volatile": {
    "fullname": "Alan Smithee",
    "profession": "Film Director"
  },
  "result": {
    "count": 41
  }
}
```
