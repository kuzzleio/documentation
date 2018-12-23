---
layout: sdk.html.hbs
title: send
description: Sends a query to the Kuzzle API
---

# send

Sends a query to the [Kuzzle API]({{ site_base_path }}api/1).

## Arguments

```cpp
virtual kuzzleio::kuzzle_response* send(
  const std::string& query, 
  kuzzleio::query_options *options, 
  const std::string& request_id) = 0;
```

| Argument  | Type             | Description
| --------- | ---------------- | ------------------------
| `query` | <pre>const std::string&</pre> | API request
| `options` | <pre>kuzzleio::query_options\*</pre>  | Additional query options
| `request_id` | <pre>const std::string&</pre> | Optional request identifier

### request

Parameters required by the Kuzzle API can be set in the [kuzzle_request](https://github.com/kuzzleio/sdk-c/blob/master/include/internal/kuzzle_structs.h#L195) struct.
The following properties are the most commonly encountered:

| Property     | Type         | Description 
| ------------ | ------------ | ------------------------------------------------------------------ |
| `controller` | <pre>const char\*</pre> | Controller name                                          |
| `action`     | <pre>const char\*</pre> | Action name                                              |
| `body`       | <pre>const char\*</pre> | Query body, in JSON format                               |
| `index`      | <pre>const char\*</pre> | Data index name                                          |
| `collection` | <pre>const char\*</pre> | Data collection name                                     |
| `id`         | <pre>const char\*</pre> | Unique identifier                                        |
| `volatiles`  | <pre>const char\*</pre> | Additional, non-meaningful information (JSON format)     |

### options

A pointer to a [query_option](https://github.com/kuzzleio/sdk-c/blob/master/include/internal/kuzzle_structs.h#L129) containing additional query options
The following properties are the most common:

| Property     | Type<br/>(default)    | Description        |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

### request_id

User-defined request identifier. Kuzzle does not guarantee that responses are sent back in the same order than queries are made: use that field to link responses to their query of origin.

## Return

A [kuzzle_response](https://github.com/kuzzleio/sdk-c/blob/master/include/internal/kuzzle_structs.h#L152) containing the Kuzzle API response. See the [API Documentation]({{ site_base_path }}api/1/kuzzle-response).
The following properties are the most common:

| Property     | Type   | Description                         |
| ------------ | ------ | ----------------------------------- |
| `request_id` | <pre>const char \*</pre> | Request unique ID                 |
| `result`     | <pre>const char \*</pre> | JSON string representing Kuzzle API result  |
| `error`      | <pre>const char \*</pre> | Error message                       |
| `status`     | <pre>int</pre>    | Request status (eg: 200, 403, etc.) |
