## Signature

``` cpp
public bool exists(std::string index, query_options *options)
```

## Usage

[code-example=exists]

## Arguments

### index

A `std::string` representing the index name.

### options

A pointer to a `query_options` containing query options.


| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

---

## Return

Returns a boolean that indicate whether the index exists or not.

## Exceptions

Throw a KuzzleException