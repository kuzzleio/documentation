---
layout: sdk.html.hbs
algolia: true
title: publishMessage
description: Collection:publishMessage
---
  

# publishMessage
[snippet=publish-message-1]
Publish a real-time message.

---
## publishMessage(Document, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``Document`` | object | [Document]({{ site_base_path }}sdk-reference/document/) object |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Optional callback |

---

## publishMessage(content, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``content`` | JSON Object | Content of the document to publish |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Optional callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``volatile`` | JSON Object | Additional information passed to notifications to other users | ``null`` |
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Return Value

Returns the `Collection` object to allow chaining.

---

## Callback Response

Returns a raw Kuzzle response in JSON format.
