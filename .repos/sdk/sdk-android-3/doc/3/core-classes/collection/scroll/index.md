---
code: true
type: page
title: scroll
description: Collection:scroll
---

# scroll

Returns a [SearchResult](/sdk/android/3/core-classes/search-result/) object containing the next page of the scroll session, and the `scrollId` to be used in the next `scroll` action.
A scroll session is always initiated by a `search` action and including the `scroll` argument; more information below.

:::info
There is a small delay between the time a document is created and its availability in our search layer (usually a couple of seconds). That means that a document that was just created might not be returned immediately by this function.
:::

:::info
To get more information about scroll sessions, please refer to the [API reference documentation](/core/1/api/controllers/document/search/).
:::

---

## scroll(scrollId, [options], callback)

| Arguments  | Type        | Description                                                                                                             |
| ---------- | ----------- | ----------------------------------------------------------------------------------------------------------------------- |
| `scrollId` | string      | The "scrollId" provided with the last scroll response or from the initial search request if it is the first scroll call |
| `options`  | JSON object | Optional parameters                                                                                                     |
| `callback` | function    | Callback handling the response                                                                                          |

---

## Options

| Option     | Type    | Description                                                                                                                       | Default     |
| ---------- | ------- | --------------------------------------------------------------------------------------------------------------------------------- | ----------- |
| `queuable` | boolean | Make this request queuable or not                                                                                                 | `true`      |
| `scroll`   | string  | Re-initializes the scroll session timeout to its value. If not defined, the scroll timeout is defaulted to a Kuzzle configuration | `undefined` |

---

## Callback Response

Returns an instantiated [SearchResult](/sdk/android/3/core-classes/search-result/) object.

---

## Usage

<<< ./snippets/scroll-1.java
