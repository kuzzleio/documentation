``` cpp
public void delete_(std::string index, query_options *options)
```

## Usage

[code-example=delete]

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

Returns nothing.

## Exceptions

Throw a KuzzleException
