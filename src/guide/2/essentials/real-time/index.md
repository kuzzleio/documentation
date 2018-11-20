---
layout: full.html.hbs
algolia: true
title: Real-time Notifications
order: 600
---


# Real-time Notifications

Kuzzle features highly customizable notifications thanks to its **real-time engine** which lets you configure live subscriptions to any dataset. These live subscriptions are a great way to **track** changes in specific subsets of data.


## Concepts

Real-time notifications are triggered by the [pub/sub mechanism](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern) embedded in Kuzzle and follow the [Observer/Observable pattern](https://en.wikipedia.org/wiki/Observer_pattern), in which Kuzzle is the Observable and the client is the Observer.

They are is possible only when the communication happens in a **persistent-connection**-oriented protocol (like Websockets or MQTT) and therefore not with HTTP.

Clients can subscribe to many types of notifications. Below are some examples:

1. **new documents** being created in a given collection (e.g. Ann creates a new TO-DO item);
2. **documents being deleted** from a given collection (e.g. Tom deletes a TO-DO item);
3. **changes happening** on any document within a collection (e.g. Matt checks an item as "done");
4. **changes happening on a given set of documents** (e.g. clients must play a sound every time an item containing the word "URGENT" is created).

The scope of possibilities is huge. Take a look at the [Notifications section]({{ site_base_path }}api/2/notifications) in the API Reference for more details.


### The basic subscription

Once our client has started and initialized with the set of TO-DO items it fetched from the persistence layer, we want it to subscribe to the changes happening on them.

```javascript
kuzzle
    .collection('todos', 'todo-list')
    .subscribe({}, (error, notification) => {
        if (error) {
            throw new Error(error)
        }
        console.log('Something happened and we should do something.', notification)
    })
```

This code isn't very useful at the moment, but it shows the capability to react to a notification coming from the server.

Here, we call the `subscribe` method on the `todos` collection with two arguments:

* The first argument represents the _filters_, and in this case there's none, which means that we are subscribing to _all documents changes_ in the collection. Filters enable more fine-grained selection on the data we want to subscribe to and are described in the next example.
* The second argument is the _callback_, i.e. a function that is called _every time a notification is received_.

Now, imagine this code is executed on Tom's client: when Ann creates the new TO-DO item, Tom receives a notification looking like the following:

```json
{
  "status": 200,
  "type": "document",
  "index": "todo-list",
  "collection": "todos",
  "controller": "document",
  "action": "create",
  "state": "done",
  "scope": "in",
  "volatile": {},
  "requestId": "<request unique identifier>",
  "result": {
    "_id": "<document unique identifier>",
    "_source": {
      "label": "The new item Ann just created!",
      "checked": "false"
    },
    "_meta": {
      "author": "ann",
      "createdAt": 1497866996975,
      "updatedAt": null,
      "updater": null,
      "active": true,
      "deletedAt": null
    }
  }
}
```

The Notification bears some useful information about what just happened:

* the `controller` and `action` attributes show *what* has happened;
* the `index` and `collection` attributes show *where* it happened;
* the `result` shows *the consequence* of what just happened (in this case, the newly created document).

We won't analyze the other attributes for the moment. Take a look at the [Notifications section of the API Reference]({{ site_base_path }}api/2/notifications) for a comprehensive list of available notification properties.

This subscription is very handy and will notify Tom about the events 1, 2 and 3 of the list above (the `controller`, `action` and `result` will vary depending on the case). But what about the event number 4? How does Tom subscribe to items that only contain the word `URGENT` in their `label` field? Looks like a job for [Koncorde]({{ site_base_path }}kuzzle-dsl/2/).


### Subscription with options

The `subscribe` method can be called with an extra argument, which is an object containing a set of options to be passed to the subscription Room.

We just introduced a new concept here, the Room. A Room is a class representing a single subscription and its constructor is called internally by the `subscribe` method.  
This object supports a wide range of options that can be passed directly to its [constructor]({{ site_base_path }}sdk-reference/room/), allowing to configure the kind of notifications we want to receive.

For now, let's concentrate on the question asked at the end of the previous chapter: how do we filter the notifications resulting of our own actions?  
The option we are looking for is `subscribeToSelf`, which is set to `true` by default.

```javascript
kuzzle
  .collection('todos')
  .subscribe(
    { // The Filters object
      regexp: {
        label: 'URGENT'
      }
    },
    { // The Options object
      subscribeToSelf: false
    },
    (error, notification) => { // The callback
      if (error) {
          throw new Error(error)
      }
      console.log('Something happened and we should do something URGENTLY.', notification)
  })
```

In the code right above, we added the extra "options" object as the second argument to avoid subscribing Tom to his own events.
