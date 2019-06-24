---
code: true
type: page
title: Introduction
description: Kuzzle right representation
order: 0
---

# UserRight

The `UserRight` class is the SDK representation of a single [user's right](/core/1/guides/essentials/user-authentication/#creating-users).

Instances of the `UserRight` class are returned by the followings methods:

- [auth:getMyRights](/sdk/cpp/1/controllers/auth/).

## Public class definition

```cpp
namespace kuzzleio {
  class UserRight {
    public:
      // Getters
      const std::string& controller() const;
      const std::string& action() const;
      const std::string& index() const;
      const std::string& collection() const;
      const std::string& value() const;
  };
}
```
