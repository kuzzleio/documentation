---
layout: sdk.html.hbs
title: info
description: Returns information about Kuzzle server.
---

# info

{{{since "1.0.0"}}}

Returns information about Kuzzle: available API (base + extended), plugins, external services (Redis, Elasticsearch, ...), servers, etc.

## Signature

```csharp
public string info();
public string info(QueryOptions options);
```

### **Options**

Additional query options

| Option     | Type   | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | If true, queues the request during downtime, until connected to Kuzzle again | `true`  |

## Return

Returns a JSON string representing the server information.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=info]
