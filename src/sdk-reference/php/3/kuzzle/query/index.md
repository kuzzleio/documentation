---
layout: sdk.html.hbs
algolia: true
title: query
description: Kuzzle:query
---

  

## query
Base method used to send queries to Kuzzle, following the [API Documentation]({{ site_base_path }}api-documentation).

<aside class="warning">
This is a low-level method, exposed to allow advanced SDK users to bypass high-level methods.<br/>
Refer to Kuzzle's API Reference <a href="{{ site_base_path }}api-documentation">here</a>
</aside>


## queryArgs

`queryArgs` is a JSON object allowing Kuzzle to route your query to the right API method:

| Option | Type | Description |  Required? |
|
## query

`query` is a JSON object containing arguments specific to the query, such as a `body` property, a JWT hash, a document `_id`, or generic query options (such as `from` or `size` for [search queries]({{ site_base_path }}api-documentation/controller-document/search/))

## Return Value

Returns the `Kuzzle` SDK object to allow chaining.

---

## Callback Response

Returns a `JSON object` containing the raw Kuzzle response.

## Usage

[snippet=query-1]
> Callback response:

```json
{ "error": null,
  "result": {
    "action": "action",
    "controller": "controller",
    "requestId": "bf87b930-7c02-11e5-ab10-dfa9e9fd2e07",
    "other properties": "depends of the query made"
  }
}
```