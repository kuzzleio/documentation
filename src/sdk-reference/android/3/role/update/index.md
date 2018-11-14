---
layout: sdk.html.hbs
algolia: true
title: update
description: Role:update
---
  

# update
[snippet=update-1]
Updates the role object in Kuzzle.

<aside class="warning">
  <p>
    Unlike a regular document update, this method will replace the whole role definition under the indexes node with the <code>updateContent</code> parameter.<br>
    In other words, you always need to provide the complete role definition in the <code>updateContent</code> object.
  </p>
  <p>
    This method has the same effect as calling <a href="{{ site_base_path }}sdk-reference/role/set-content"><code>setContent</code></a> followed by the <a href="{{ site_base_path }}sdk-reference/role/save"><code>save</code></a> method.
  </p>
</aside>

To get more information about Kuzzle permissions, please refer to our [permissions guide]({{ site_base_path }}guide/essentials/security/#user-permissions).

---

## update(content, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``content`` | JSON Object | New role content |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Optional callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Return Value

Returns the `Role` object to allow chaining.

---

## Callback Response

Returns the updated version of this object.
