---
layout: sdk.html.hbs
title: adminExists
description: Checks that an administrator account exists.
---

# adminExists

{{{since "1.0.0"}}}

Checks that an administrator account exists.

## Arguments

```cpp
bool adminExists(kuzzleio::query_options *options=nullptr);
```

| Arguments | Type          | Description                                             | Required |
| --------- | ------------- | ------------------------------------------------------- | -------- |
| `options` | kuzzleio::query_options* | A pointer to a `query_options` containing query options | no       |

### options

Additional query options

| Option     | Type   | Description                       | Default |
| ---------- | ------- | --------------------------------- | 
| `queuable` | bool | If true, queues the request during downtime, until connected to Kuzzle again | `true`  |

## Return

Returns a `boolean` set to `true` if an admin exists and `false` if it does not.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=admin-exists]
