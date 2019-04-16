---
layout: sdk.html.hbs
title: logout
description: Revokes the user's token & unsubscribe them from registered rooms.
---

# logout

Revokes the current authentication token.

If there were any, real-time subscriptions are cancelled.

If this action is successful, then the [jwt]({{ site_base_path }}sdk-reference/js/6/kuzzle/properties) property of this class instance is unset.

## Arguments

```javascript
logout ()
```

## Usage

[snippet=logout]
