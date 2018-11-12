---
layout: sdk.html.hbs
algolia: true
title: publish
description:
order: 200
---

# publish

Sends a real-time `<message>` to Kuzzle. The `<message>` will be dispatched to all clients with subscriptions matching the `<index>`, the `<collection>` and the `<message>` content.  

The `<index>` and `<collection>` are indicative and serve only to distinguish the rooms. They are not required to exist in the database

**Note:** real-time messages are not persisted in the database.

## Arguments

```javascript
publish (index, collection, message, [options])
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | string | Index name    |
| ``collection`` | string | Collection name    |
| ``message`` | object | Message to send    |
| ``options`` | object | Query options    |

### options

Additional query options

| Option     | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | boolean<br/>(`true`) | Make this request queuable or not |

## Resolve

A boolean indicating if the message was successfully published.

## Usage

[snippet=publish]
