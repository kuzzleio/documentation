``` javascript
delete(index, options = null) ⇒ object
```

## Usage

[code-example=delete]

## Arguments

### index

A `string` representing the index name.

### options

Query options.

| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |

---

## Response

Returns an object with the index deletion status.

| Name | Type | Description
|------|------|-------------
| acknowledged | boolean | indicates whether the index was successfully deleted in the Elastic cluster
