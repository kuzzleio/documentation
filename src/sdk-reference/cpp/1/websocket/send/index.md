---
layout: sdk.html.hbs
title: send
description: Base method to send API query to Kuzzle
---

# send

Base method used to send queries to Kuzzle, following the [API Documentation]({{ site_base_path }}api/1).

## Signature

```cpp
kuzzle_response* send(const std::string& query, query_options *options, const std::string& request_id);
```

## Arguments

| Argument  | Type             | Description
| --------- | ---------------- | ------------------------
| `query` | <pre>std::string&</pre> | API request
| `options` | <pre>kuzzleio::query_options*</pre>  | Additional query options
| `request_id` | <pre>std::string&</pre> | the request_id of the request if you want to set one

### **request**

Properties required for the Kuzzle API can be set in the [kuzzle_request](https://github.com/kuzzleio/sdk-go/blob/1.x/internal/wrappers/headers/kuzzlesdk.h#L51) struct.
The following properties are the most common.

| Property     | Type         | Description                                                        | Required |
| ------------ | ------------ | ------------------------------------------------------------------ | -------- |
| `controller` | const char\* | Controller name                                                    | yes      |
| `action`     | const char\* | Action name                                                        | yes      |
| `body`       | const char\* | JSON query body string for this action                             | no       |
| `index`      | const char\* | Index name for this action                                         | no       |
| `collection` | const char\* | Collection name for this action                                    | no       |
| `id`         | const char\* | id for this action                                                 | no       |
| `volatiles`  | const char\* | JSON string representing additional information to send to Kuzzle | no       |

### **options**

A pointer to a [query_option](https://github.com/kuzzleio/sdk-go/blob/1.x/internal/wrappers/headers/kuzzlesdk.h#L169) containing additional query options
The following properties are the most common.

| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | boolean(true) | Make this request queuable or not |

### **request_id**

A string representing the request Id.

## Return

A [kuzzle_response](https://github.com/kuzzleio/sdk-go/blob/1.x/internal/wrappers/headers/kuzzlesdk.h#L445) containing the Kuzzle API response. See the [API Documentation]({{ site_base_path }}api/1/kuzzle-response).
The following properties are the most common.

| Property     | Type   | Description                         |
| ------------ | ------ | ----------------------------------- |
| `request_id` | char\* | Request unique id                   |
| `result`     | char\* | Raw JSON result                     |
| `error`      | char\* | Error message                       |
| `status`     | <pre>int</pre>    | Request status (eg: 200, 403, etc.) |
