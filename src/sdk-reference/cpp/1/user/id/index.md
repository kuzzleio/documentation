---
layout: sdk.html.hbs
title: id
description: Getter for the _id property
---

# id

Returns the user's unique identifier (or [kuid]({{ site_base_path }}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuid-default)).

## Arguments

```cpp
std::string const& id() const;
```

## Return

The `id` getter returns the user's kuid.
