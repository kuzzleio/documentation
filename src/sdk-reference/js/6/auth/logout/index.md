---
layout: sdk.html.hbs
algolia: true
title: logout
description: Revokes the user's token & unsubscribe them from registered rooms.
order: 200
---

# logout

Revokes the user's authentication token.

If there were any, real-time subscriptions are cancelled.

## Signature

```javascript
logout ()
```

## Usage

[snippet=logout]
