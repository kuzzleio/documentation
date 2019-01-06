---
layout: sdk.html.hbs
title: Introduction
description: Kuzzle right representation
order: 0
---

# UserRight

The `UserRight` class is the SDK representation of a single [user's right]({{ site_base_path }}guide/1/essentials/user-authentication/## Public class definition

```csharp
namespace kuzzleio {
  class UserRight {
    public:
      // Getters
      string controller();
      string action();
      string index();
      string collection();
      string value();
  };
}
```
