---
layout: sdk.html.hbs
algolia: true
title: deleteRole
description: Security:deleteRole
---

  

# deleteRole
Delete the provided role.

<aside class="notice">
There is a small delay between the time a role is deleted and it being reflected in the search layer (usually a couple of seconds).
That means that a role that was just deleted may still be returned by the <code>searchRoles</code> function at first.
</aside>


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `Security` object to allow chaining.

---

## Callback Response

Returns the id of the rold that has been deleted.

## Usage

[snippet=delete-role-1]
> Callback response

```json
"deleted role identifier"
```