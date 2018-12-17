---
layout: sdk.html.hbs
title: replace
description: Replace a document
order: 200
---

# replace

Replaces the content of an existing document.

## Signature

```csharp
public string replace(string index, string collection, string id, string body);
public string replace(string index, string collection, string id, string body, QueryOptions options);
```

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>string</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the document update result.

| Name | Type | Description
| --- | --- | ---
| _id | <pre>string</pre> | The id of the newly created document
| _version | <pre>int</pre> | The version of the document in the persistent data storage
| result | <pre>string</pre> | set to `updated` in case of success

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=replace]
