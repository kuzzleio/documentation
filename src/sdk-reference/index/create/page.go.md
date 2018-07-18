`create(std::string index, kuzzleio::query_options options) : nullptr`

[section=description]

## Usage

[code-example=create]

## Arguments

### index

A `std::string` representing the index name.

### options

A pointer to a `kuzzleio::query_options` containing query options.

[code-example=options]

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

---

## Return

Returns nothing.

## Exceptions

Throw a KuzzleException
