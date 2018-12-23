---
layout: sdk.html.hbs
title: Introduction
description: Kuzzle user representation
order: 0
---

# Introduction

The `User` class is the SDK representation of a Kuzzle [user]({{ site_base_path }}guide/1/essentials/user-authentication/#creating-users-default).

Instances of the `User` class are returned by methods such as [auth:getCurrentUser]({{ site_base_path }}sdk-reference/cpp/1/auth/get-current-user).

## Public class definition

```cpp
namespace kuzzleio {
  class User {
    public:
      User(const kuzzle_user* u);
      User() = default;
      User& operator=(const kuzzle_user*);

      const std::string& id() const;
      const std::string& content() const;
      const std::vector<std::string>& profile_ids() const;
  };
}
```
