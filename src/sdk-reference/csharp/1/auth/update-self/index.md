---
layout: sdk.html.hbs
title: updateSelf
description: Updates the current user object in Kuzzle.
---

# updateSelf

Updates the current user object in Kuzzle.

## Signature

```csharp
public SWIGTYPE_p_User updateSelf(string content);
public SWIGTYPE_p_User updateSelf(string content, QueryOptions options);
```

### **Options**

Additional query options:

| Property     | Type    | Description  |
| ---------- | ------- | -------------- |
| `queuable` | <pre>bool (true)</pre> | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A [User]({{ site_base_path }}sdk-reference/csharp/1/user/) object.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=update-self]
