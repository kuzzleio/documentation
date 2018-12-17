---
layout: sdk.html.hbs
title: create
description: Create a new document
---

# create

Creates a new document in the persistent data storage.

Throws an error if the document already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Signature

```csharp
public string create(string index, string collection, string id, string body);
public string create(string index, string collection, string id, string body, QueryOptions options);
```

### options

Additional query options

| Option   | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>string</pre><br/>(`""`)| If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the document creation result.

| Name | Type | Description
| --- | --- | ---
| _id | <pre>string</pre> | ID of the newly created document
| _version | <pre>int</pre> | Version of the document in the persistent data storage
| _source | <pre>string</pre> | A JSON string containing the created document
| result | <pre>string</pre> | Set to `created` in case of success

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=create]
