---
layout: sdk.html.hbs
algolia: true
title: deleteUser
description: Security:deleteUser
---

  

# deleteUser
Delete the provided user.

<aside class="notice">
There is a small delay between the time a user is deleted and it being reflected in the search layer (usually a couple of seconds).
That means that a user that has just been deleted may still be returned by the <code>searchUsers</code> function at first.
</aside>


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `Security` object to allow chaining.

---

## Callback Response

Return the id of the user that has been deleted.

## Usage

[snippet=delete-user-1]
> Callback response

```json
"deleted user identifier"
```