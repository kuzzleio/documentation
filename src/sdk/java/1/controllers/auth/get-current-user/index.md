---
code: true
type: page
title: getCurrentUser
description: Returns the profile object for the user linked to the `JSON Web Token`
---

# getCurrentUser

Returns the profile object for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Signature

```java
public User getCurrentUser();
```

## Return

A User object with setters and getters containing:

| Property     | Type     | Description                         |
| ------------ | -------- | ----------------------------------- |
| `id`         | String   | The user ID                         |
| `content`    | String   | The user content                    |
| `profileIds` | String[] | An array containing the profile ids |

## Usage

<<< ./snippets/get-current-user.java
