---
layout: sdk.html.hbs
title: adminExists
description: Checks that an administrator account exists.
---

# adminExists

Checks that an administrator account exists.

## Signature

```csharp
public bool adminExists();

public bool adminExists(query_options options);

```

## Arguments

| Arguments | Type          | Description       |
| --------- | ------------- | ------------------|
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)   | Description  |
| ---------- | ------- | -------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A bool indicating whether an admin user exists or not.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=admin-exists]
