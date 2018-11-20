---
layout: full.html.hbs
algolia: true
title: Volatile Data
description: broadcast extra information to subscribers
order: 400
---


# Volatile Data

All queries accept a `volatile` object parameter (see the [query syntax]({{site_base_path}}api/1/query-syntax) documentation).

The content of this object is not meant to be used directly: it has no impact on the query itself.

Still, volatile data are not completely ignored by Kuzzle, and they have a few uses.


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
