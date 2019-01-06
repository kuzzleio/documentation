---
layout: sdk.html.hbs
title: Introduction
description: Kuzzle user representation
order: 0
---

# User

The `User` class is the SDK representation of a Kuzzle [user]({{ site_base_path }}guide/1/essentials/user-authentication/#creating-users-default).

Instances of the `User` class are returned by the following methods:
 - [auth:getCurrentUser]({{ site_base_path }}sdk-reference/csharp/1/auth/get-current-user)

## Public class definition

```csharp
namespace kuzzleio {
  class User {
    public:
      // Getters
      string id();
      string content();
      List<string>& profile_ids();
  };
}
```
