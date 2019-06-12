---
code: true
type: page
title: send
description: Sends a query to the Kuzzle API
---

# send

Sends a query to the [Kuzzle API](/core/1/api).

## Signature

```cpp
virtual kuzzleio::kuzzle_response* send(
  const std::string& query,
  kuzzleio::query_options *options,
  const std::string& request_id) = 0;
```

## Arguments

| Argument     | Type                                 | Description                 |
| ------------ | ------------------------------------ | --------------------------- |
| `query`      | <pre>const std::string&</pre>        | API request                 |
| `options`    | <pre>kuzzleio::query_options\*</pre> | Additional query options    |
| `request_id` | <pre>const std::string&</pre>        | Optional request identifier |

### request

Properties required for the Kuzzle API can be set in the kuzzle_request struct.
The following properties are the most common.

| Property     | Type                    | Description                                                       | Required |
| ------------ | ----------------------- | ----------------------------------------------------------------- | -------- |
| `controller` | <pre>const char\*</pre> | Controller name                                                   | yes      |
| `action`     | <pre>const char\*</pre> | Action name                                                       | yes      |
| `body`       | <pre>const char\*</pre> | JSON string representing body for this action                     | no       |
| `index`      | <pre>const char\*</pre> | Index name for this action                                        | no       |
| `collection` | <pre>const char\*</pre> | Collection name for this action                                   | no       |
| `id`         | <pre>const char\*</pre> | ID for this action                                                | no       |
| `volatiles`  | <pre>const char\*</pre> | JSON string representing additional information to send to Kuzzle | no       |

### options

Additionnal query options.
The following properties are the most common:

| Property   | Type<br/>(default)                       | Description                                                                                                                                                                                                           |
| ---------- | ---------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`)             | If true, queues the request during downtime, until connected to Kuzzle again                                                                                                                                          |
| `refresh`  | <pre>const std::string&</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s)                                                                                                                                    |
| `from`     | <pre>int</pre><br/>(`0`)                 | Offset of the first document to fetch                                                                                                                                                                                 |
| `size`     | <pre>int</pre><br/>(`10`)                | Maximum number of documents to retrieve per page                                                                                                                                                                      |
| `scroll`   | <pre>const std::string&</pre><br/>(`""`) | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) |

### request_id

User-defined request identifier.  
Kuzzle does not guarantee that responses are sent back in the same order than queries are made: use that field to link responses to their query of origin.

## Return

A `kuzzle_response` containing the Kuzzle API response. See the [API Documentation](/core/1/api/essentials/kuzzle-response).
The following properties are the most common:

| Property     | Type                     | Description                                |
| ------------ | ------------------------ | ------------------------------------------ |
| `request_id` | <pre>const char \*</pre> | Request unique ID                          |
| `result`     | <pre>const char \*</pre> | JSON string representing Kuzzle API result |
| `error`      | <pre>const char \*</pre> | Error message                              |
| `status`     | <pre>int</pre>           | Request status (eg: 200, 403, etc.)        |
