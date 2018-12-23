---
layout: sdk.html.hbs
title: Introduction
description: Kuzzle right representation
order: 0
---

# UserRight

The `UserRight` class is the SDK representation of a single [user's right]({{ site_base_path }}guide/1/essentials/user-authentication/#creating-users-default).

Instances of the `UserRight` class are returned by methods such as [auth:getMyRights]({{ site_base_path }}sdk-reference/cpp/1/auth/get-my-rights).

## Public class definition

```cpp
namespace kuzzleio {
  class UserRight {
    public:
      const std::string& controller() const;
      const std::string& action() const;
      const std::string& index() const;
      const std::string& collection() const;
      const std::string& value() const;
  };
}
```
