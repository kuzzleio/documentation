---
layout: sdk.html.hbs
title: send
description: Base method to send API query to Kuzzle
---

# send

Base method used to send queries to Kuzzle, following the [API Documentation]({{ site_base_path }}api/1).

## Signature

```csharp
public override SWIGTYPE_p_kuzzle_response send(
    string arg0, 
    SWIGTYPE_p_query_options arg1, 
    string arg2);

```

## Arguments

| Argument  | Type             | Description
| --------- | ---------------- | ------------------------
| `query` | <pre>string</pre> | API request
| `options` | <pre>Kuzzleio::QueryOptions\*</pre>  | Additional query options
| `request_id` | <pre>string</pre> | the request_id of the request if you want to set one

### request

Properties required for the Kuzzle API can be set in the [kuzzle_request]({{ site_base_path }}sdk-reference/csharp/1/kuzzle-request) struct.
The following properties are the most common.

| Property     | Type         | Description                                                        | Required |
| ------------ | ------------ | ------------------------------------------------------------------ | -------- |
| `controller` | <pre>string</pre> | Controller name                                         | yes      |
| `action`     | <pre>string</pre> | Action name                                             | yes      |
| `body`       | <pre>string</pre> | JSON string representing body for this action           | no       |
| `index`      | <pre>string</pre> | Index name for this action                              | no       |
| `collection` | <pre>string</pre> | Collection name for this action                         | no       |
| `id`         | <pre>string</pre> | ID for this action                                      | no       |
| `volatiles`  | <pre>string</pre> | JSON string representing additional information to send to Kuzzle | no       |

### options

Additionnal query options.  
The following properties are the most common:

| Property     | Type<br/>(default)    | Description        |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>string</pre><br/>(`""`)| If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |
| `from` | <pre>int</pre><br/>(`0`) | Offset of the first document to fetch |
| `size` | <pre>int</pre><br/>(`10`) | Maximum number of documents to retrieve per page  |
| `scroll` | <pre>string</pre><br/>(`""`) | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) |

### request_id

User-defined request identifier.  
Kuzzle does not guarantee that responses are sent back in the same order than queries are made: use that field to link responses to their query of origin.

## Return

A [kuzzle_response]({{ site_base_path }}sdk-reference/csharp/1/kuzzle-response) containing the Kuzzle API response. See the [API Documentation]({{ site_base_path }}api/1/essentials/kuzzle-response).
The following properties are the most common.

| Property     | Type   | Description                         |
| ------------ | ------ | ----------------------------------- |
| `request_id` | <pre>string</pre>| Request unique id                   |
| `result`     | <pre>string</pre> | Raw JSON result                     |
| `error`      | <pre>string</pre> | Error message                       |
| `status`     | <pre>int</pre>    | Request status (eg: 200, 403, etc.) |
