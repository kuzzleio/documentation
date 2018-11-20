---
layout: sdk.html.hbs
algolia: true
title: pexpire
description: MemoryStorage:pexpire
---

  

# pexpire
Sets a timeout (in milliseconds) on a key. After the timeout has expired, the key will automatically be deleted.

[[_Redis documentation_]](https://redis.io/commands/pexpire)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns a boolean specifying if the operation was successful or not.

## Usage

[snippet=pexpire-1]
> Callback response:

```json
true
```