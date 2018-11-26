---
layout: sdk.html.hbs
algolia: true
title: getAutoRefresh
description: Kuzzle:getAutoRefresh
algolia: true
---
  

# getAutoRefresh
The `autoRefresh` flag, when set to true, will make Kuzzle perform a
[`refresh`](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/docs-refresh.html) request
immediately after each write request, causing documents to be immediately visible in a search.

The `getAutoRefresh` function returns the current `autoRefresh` status for the given index.

<div class="alert alert-warning">
    <p>
        A refresh operation comes with some performance costs.
    </p>
    <p>
      While forcing the autoRefresh can be convenient on a development or test environmnent, we recommend that you avoid
      using it in production or at least carefully monitor its implications before using it.
    </p>
</div>

---

#### getAutoRefresh([index], [options], callback)

| Arguments | Type | Description
|-----------|------|------------
| `index` | string | Optional index to query. If no set, defaults to [Kuzzle.defaultIndex]({{ site_base_path }}sdk-reference/kuzzle/#properties)
| `options` | JSON object | Optional parameters
| `callback`| function | Callback handling the response

---

## Options

| Option | Type | Description | Default
|--------|------|-------------|---------
| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

Returns a boolean with the index `autoRefresh` status.

## Usage

[snippet=get-auto-refresh-1]
