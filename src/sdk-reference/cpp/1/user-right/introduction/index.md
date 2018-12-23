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
      UserRight() = default;
      UserRight(const user_right* r);

      const std::string& controller() const;
      const std::string& action() const;
      const std::string& index() const;
      const std::string& collection() const;
      const std::string& value() const;
  };
}
```

## Properties

| Property | Type | Description |
|--- |--- |--- |
| `action` | <pre>const std::string</pre> | Action on wich the rights are applied |
| `collection` | <pre>const std::string</pre> | Collection on wich the rights are applied |
| `controller` | <pre>const std::string</pre> | Controller on wich the rights are applied |
| `index` | <pre>const std::string</pre> | Index on wich the rights are applied |
| `value` | <pre>const std::string</pre> | Right status.<br/>Possible values: `allowed`, `denied`, `conditional` |
