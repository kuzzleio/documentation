---
layout: sdk.html.hbs
title: Introduction
description: Kuzzle User representation
order: 0
---

# Introduction

The `User` class is the SDK representation of a Kuzzle [user](https://docs-v2.kuzzle.io/guide/1/essentials/user-authentication/#creating-users-default).

Instances of the `User` class are returned by methods such as [auth:getCurrentUser]({{ site_base_path }}sdk-reference/cpp/1/auth/get-current-user).

## Public class definition

```cpp
namespace kuzzleio {
  class User {
    public:
      std::string const& id() const;
      std::string const& content() const;
      std::vector<std::string> const& profile_ids() const;

      User(const kuzzle_user* u);
      User() = default;
      User& operator=(const kuzzle_user*);
  };
}
```
