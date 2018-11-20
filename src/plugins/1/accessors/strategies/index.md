---
layout: full.html.hbs
algolia: true
title: strategies
---


# strategies

Dynamically adds or removes [authentication strategies]({{ site_base_path }}plugins/1/essentials/strategies).


## remove

{{{since "1.2.0"}}}

Removes an authentication strategy, preventing new authentications from using it.

In a cluster environment, the new strategy is automatically removed from all server nodes.

<aside class="alert alert-warning">
Authentication tokens previously created using that strategy ARE NOT invalidated after using this method.
</aside>

### Arguments

```js
remove(name)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `name` | <pre>string</pre> | Name of the authentication strategy to remove |

### Return

The `remove` function returns a promise, resolved once the strategy has been successfully removed.

This promise is rejected if the strategy to remove does not exist, or if it is owned by another plugin.

### Example

```js
try {
  context.accessors.strategies.remove('someStrategy');
} catch(error) {
  // "error" is a KuzzleError object
}
```
