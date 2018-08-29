### index

A `std::string` representing the index name.

### options

A pointer to a kuzzleio::query_options containing query options.

<pre>

kuzzleio::query_options options = {0};
options.queuable = false;
</pre>


| Option   | Type    | Description                       | Default |
| -------- | ------- | --------------------------------- | ------- |
| queuable | boolean | Make this request queuable or not | true    |
