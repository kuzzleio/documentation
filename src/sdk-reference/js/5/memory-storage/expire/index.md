---
layout: sdk.html.hbs
algolia: true
title: expire
description: MemoryStorage:expire
---

  

# expire
Sets a timeout (in seconds) on a key. After the timeout has expired, the key will automatically be deleted.

[[_Redis documentation_]](https://redis.io/commands/expire)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns a boolean specifying if the operation was successful or not.

## Usage

[snippet=expire-1]
> Callback response:

```json
true
```