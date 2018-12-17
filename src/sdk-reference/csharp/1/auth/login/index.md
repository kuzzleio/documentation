---
layout: sdk.html.hbs
title: login
description: Authenticate a user
---

# login

Authenticates a user.


## Signature

```csharp
public string login(string strategy, string credentials);
public string login(string strategy, string credentials, int expiresIn);
```

## Arguments

| Arguments     | Type    | Description | Required
|---------------|---------|----------------------------------|----------
| `strategy`    | string  | the name of the strategy to use  | yes
| `credentials` | string  | the json credentials             | yes
| `expiresIn`   | int     | expiration time in milliseconds  | no

## Return

The **login** action returns an encrypted JSON Web Token, that must then be sent in the [requests headers]({{ site_base_path }}api/2/query-syntax/).

## Return

The **login** action returns an encrypted JSON Web Token, that must then be sent in the [requests headers]({{ site_base_path }}api/2/query-syntax/).

## Return

The **login** action returns an encrypted JSON Web Token, that must then be sent in the [requests headers]({{ site_base_path }}api/2/query-syntax/).

