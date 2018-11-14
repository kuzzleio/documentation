---
layout: sdk.html.hbs
algolia: true
title: sort
description: MemoryStorage:sort
---
  

# sort
[snippet=sort-1]
> Callback response:
Sorts and returns elements contained in a list, a set of unique values or a sorted set.
By default, sorting is numeric and elements are compared by their value interpreted as double precision floating point number.

<aside class="left notice">
While Kuzzle's API supports the "store" option for this command, Kuzzle SDK methods do not. To sort and store in the same process, use the <a href="{{ site_base_path }}sdk-reference/kuzzle/query">query method</a>
</aside>

[[_Redis documentation_]](https://redis.io/commands/sort)

### sort(key, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `alpha` | boolean | Perform an alphanumerical sort instead of a numeric one | `false` |
| `by` | string | Instead of sorting the values stored at `key`, use them to complete the provided key pattern, and return the sorted list of values stored in those keys. | `null` |
| `direction` | string | Sort in ascendant (`ASC`) or descendant (`DESC`)  order | `ASC` |
| `get` | array | Sort the values stored at `key` but, instead of returning these directly, return the values contained in external keys, using the provided array of patterns completed by the sorted values | `null` |
| `limit` | array | Limit the result set to a range of matching elements (similar to _SELECT LIMIT offset, count_ in SQL).<br/>Format: `[<offset(int)>, <count(int)>]` | `null` |
| `queuable` | boolean | Make this request queuable or not  | `true` |

### Callback Response

Returns an array of sorted values.
