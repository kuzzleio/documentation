---
layout: sdk.html.hbs
algolia: true
title: Logout
description: Revokes the user's token & unsubscribe them from registered rooms.
---


# Logout

Revokes the user's token & unsubscribe them from registered rooms.

## Signature

```go
func (a *Auth) Logout() error 
```

## Return

Return an error or `nil` if the credentials are successfully deleted

## Usage

[snippet=logout]
