### index

A `String` representing the index name.

### options

A type.QueryOptions struct containing query options.

<pre>


opts := types.NewQueryOptions()
opts.setQueuable(false)
</pre>

| Property | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |
