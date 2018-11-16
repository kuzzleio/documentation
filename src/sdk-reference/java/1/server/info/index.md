---
layout: sdk.html.hbs
algolia: true
title: info
description: Returns information about Kuzzle server.
---

# info

{{{since "1.0.0"}}}

Returns information about Kuzzle: available API (base + extended), plugins, external services (Redis, Elasticsearch, ...), servers, etc.

## Arguments

```java
public java.lang.String info(
io.kuzzle.sdk.QueryOptions
);
public java.lang.String info();
```

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `options` | io.kuzzle.sdk.QueryOptions | An object containing query options. | no       |

### **Options**

Additional query options

| Property   | Type  | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Return server information as a `String`.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=info]
