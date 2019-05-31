---
code: true
type: page
title: fetchDocument
description: Collection:fetchDocument
---

# fetchDocument

Retrieves a single stored document using its unique document ID, and returns it as a [Document](/sdk/android/3/controllers/document/) object.

---

## fetchDocument(documentId, [options], callback)

| Arguments    | Type        | Description                    |
| ------------ | ----------- | ------------------------------ |
| `documentId` | string      | Unique document identifier     |
| `options`    | JSON Object | Optional parameters            |
| `callback`   | function    | Callback handling the response |

---

## Options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

---

## Callback Response

Returns a [Document](/sdk/android/3/controllers/document/) object.

## Usage

<<< ./snippets/fetch-document-1.java
