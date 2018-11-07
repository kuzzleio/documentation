---
layout: sdk.html.hbs
algolia: true
title: validate
description:
order: 200
---

# validate

Validates data against existing validation rules. 

Note that if no validation specifications are set for the `<index>`/`<collection>`, the document will always be valid.

This request does **not** store or publish the document.

## Arguments

```java
boolean validate(String, String, String, io.kuzzle.sdk.QueryOptions)
boolean validate(String, String, String)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `body` | <pre>String</pre> | A JSON string containing the body of the document |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | The query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`)| Make this request queuable or not |

## Return

Returns a boolean value set to true if the document is valid and false otherwise.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=validate]
