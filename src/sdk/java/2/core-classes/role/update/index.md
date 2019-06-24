---
code: true
type: page
title: update
description: Role:update
---

# update

Updates the role object in Kuzzle.

:::warning
Unlike a regular document update, this method will replace the whole role definition under the indexes node with the `updateContent` parameter.  
In other words, you always need to provide the complete role definition in the `updateContent` object.

This method has the same effect as calling [setContent](/sdk/java/2/core-classes/role/set-content/) followed by the [save](/sdk/java/2/core-classes/role/save/) method.
:::

To get more information about Kuzzle permissions, please refer to our [permissions guide](/core/1/guides/essentials/security/#user-permissions).

---

## update(content, [options], [callback])

| Arguments  | Type        | Description                             |
| ---------- | ----------- | --------------------------------------- |
| `content`  | JSON Object | New role content                        |
| `options`  | JSON Object | Optional parameters                     |
| `callback` | function    | Optional callback handling the response |

---

## Options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

---

## Return Value

Returns the `Role` object to allow chaining.

---

## Callback Response

Returns the updated version of this object.

## Usage

<<< ./snippets/update-1.java
