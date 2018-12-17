---
layout: sdk.html.hbs
title: setAutoRefresh
description: Set the autorefresh flag
---

## Signature

```csharp
public void setAutoRefresh(string index, bool autoRefresh);
public void setAutoRefresh(string index, bool autoRefresh, QueryOptions options);
```

## Arguments

| Arguments     | Type          | Description                                             | Required |
| ------------- | ------------- | ------------------------------------------------------- | -------- |
| `index`       | string   | Index name                                              | yes      |
| `autoRefresh` | bool       | autoRefresh flag                                        | yes      |
| `options`     | Kuzzleio::QueryOptions | A `Kuzzleio::QueryOptions` containing query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

