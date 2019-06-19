---
code: true
type: page
title: Introduction
description: Kuzzle user representation
order: 0
---

# User

The `User` class is the SDK representation of a Kuzzle [user](/core/1/guides/essentials/user-authentication/#creating-users).

Instances of the `User` class are returned by the following methods:

- [auth:getCurrentUser](/sdk/cpp/1/controllers/auth/)

## Public class definition

```cpp
namespace kuzzleio {
  class User {
    public:
      // Getters
      const std::string& id() const;
      const std::string& content() const;
      const std::vector<std::string>& profile_ids() const;
  };
}
```
