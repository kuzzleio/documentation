---
layout: full.html.hbs
title: Real-time Notifications
order: 600
---

# Real-time Notifications

Kuzzle features highly customizable notifications thanks to its **real-time engine** which lets you configure live subscriptions to any dataset. These live subscriptions are a great way to **track** changes in specific subsets of data.

---

## Introduction

Imagine you are developing a collaborative TO-DO application like [this](https://github.com/kuzzleio/demo/tree/master/todolist) one. All the TO-DO items are persisted in Kuzzle (in a collection called `todos`) so, once clients start, they fetch every available TO-DO items via a simple document search.

But imagine that one of the users (let's call her Ann), adds a new TO-DO item. In order for other users (let's call them Tom and Matt) to display these new item, they need to perform a new document search on the corresponding data collection. They will not see the new items until they refresh (or restart) their application.

This cannot be called a "modern" application: it rather looks like an old-school, refresh-ish, one. Like the early '90s. Today, such a user-experience wouldn't be satisfying at all.

A more interesting user-experience would be that clients display the new TO-DO item _as soon as it is created_. How can we achieve that?

* By implementing a long-polling mechanism in the clients. Every, say, one second, the clients perform a document search and update their list of TO-DO items. Doesn't look like a great idea (performances would be rather bad, for example)
* By providing notifications to subscribed clients, allowing them to receive these new items automatically, as soon as they are saved in the system

The second solution is exactly what we are looking for and Kuzzle ships it natively. We can call it **pub/sub**, **notifications** or **live subscriptions** and it is often used to solve use-cases like this one, where things need to be kept _in sync_ between clients and the back-end server.

Getting back to our example, our collaborative TO-DO list clients only need to subscribe to the TO-DO data collection (right after the first document search), in order to be notified _in real-time_ about new TO-DO items. This way, once Ann creates her new item, Tom and Matt can see it immediately on their screen.

---

## Concepts

Real-time notifications are triggered by the [pub/sub mechanism](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern) embedded in Kuzzle and follow the [Observer/Observable pattern](https://en.wikipedia.org/wiki/Observer_pattern), in which Kuzzle is the Observable and the client is the Observer.

They are is possible only when the communication happens in a **persistent-connection**-oriented protocol (like Websockets or MQTT) and therefore not with HTTP.

Clients can subscribe to many types of notifications. Below are some examples:

1. **new documents** being created in a given collection (e.g. Ann creates a new TO-DO item);
2. **documents being deleted** from a given collection (e.g. Tom deletes a TO-DO item);
3. **changes happening** on any document within a collection (e.g. Matt checks an item as "done");
4. **changes happening on a given set of documents** (e.g. clients must play a sound every time an item containing the word "URGENT" is created).

The scope of possibilities is huge. Take a look at the [Notifications section]({{ site_base_path }}api/2/notifications) in the API Reference for more details.

---

## Examples

But, how does this work in Kuzzle? **How do we select the data that we want to subscribe to?**

Let's dive into the implementation of the Collaborative TO-DO list application.

<div class="alert alert-info">
All the following examples are written in Javascript, therefore using the Javascript Kuzzle SDK. If this is not your usual development language, take a look at the different flavors of the `subscribe` method in the <a href="{{ site_base_path }}sdk-reference/collection/subscribe">SDK Reference</a>).
</div>

---

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

This subscription is very handy and will notify Tom about the events 1, 2 and 3 of the list above (the `controller`, `action` and `result` will vary depending on the case). But what about the event number 4? How does Tom subscribe to items that only contain the word `URGENT` in their `label` field? Looks like a job for [Koncorde]({{ site_base_path }}koncorde/1/).

---

### Subscription with filters

Kuzzle ships with a powerful filtering tool named [Koncorde]({{ site_base_path }}koncorde/1/). It enables you to perform fine-grained selections on the documents you want to subscribe to.

In our case, we want to select all the documents that contain the `URGENT` word in the `label` field. The best pick for this case is the [regexp]({{ site_base_path }}koncorde/1/terms/regexp) filter.


```javascript
kuzzle
  .collection('todos', 'todo-list')
  .subscribe({
    regexp: {
      label: 'URGENT'
    }
  }, (error, notification) => {
    if (error) {
        throw new Error(error)
    }
    console.log('Something happened and we should do something URGENTLY.', notification)
  })
```

This way, Tom will be notified about urgent TO-DO items. Take a look at the [Koncorde Reference]({{ site_base_path }}koncorde/1/) for a comprehensive list of available filters.

There are a few things that deserve to be noticed here:

* Tom will be notified either if somebody creates, updates or deletes a document containing the word `URGENT`;
* Tom will be notified even if he performs the actions himself (e.g. he is notified right after having created a new TO-DO item).

The last point may seem a little bit inconvenient. What if Tom does not want to receive notifications when the event come from his own actions? Keep reading, the solution is right below.

---

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
