---
layout: sdk.html.hbs
algolia: true
title: hscan
---

# hscan


Iterates incrementally over fields contained in a hash, using a cursor.

An iteration starts when the cursor is set to 0.  
To get the next page of results, simply re-send the request with the updated cursor position provided in the result set.  

The scan ends when the cursor returned by the server is 0.

[[_Redis documentation_]](https://redis.io/commands/hscan)

## Arguments

```js
hscan(key, [options])

```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `key` | <pre>string</pre> | Key |
| ``options`` | <pre>object</pre> | Optional query arguments |

### options

The `options` arguments can contain the following option properties:

| Property   | Type (default)   | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean (true)</pre> | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolve

## Usage

[snippet=hscan]
