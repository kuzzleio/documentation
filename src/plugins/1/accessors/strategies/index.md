---
layout: full.html.hbs
algolia: true
title: strategies
---

# `strategies`

Dynamically adds or removes [authentication strategies]({{ site_base_path }}plugins/1/essentials/strategies).

---

## add

{{{since "1.2.0"}}}

Adds a new authentication strategy. 

Users can be authenticated using that new strategy as soon as this method resolves.

If the strategy to be added already exists, the old one will be removed first, unless it has been registered by another plugin.

In a cluster environment, the new strategy is automatically added to all server nodes.

### Arguments

`add(name, properties)`

* `name`: {string} name of the new authentication strategy
* `properties`: {object} strategy properties (see [managing credentials](essentials/strategies/#managing-credentials-default))

**Note:** strategy classes must previously be declared in the [authenticators]({{ site_base_path }}plugins/1/essentials/strategies/#registering-authentication-strategies-default) property

### Return

The `add` function returns a promise.

The promise is rejected if:

* the properties for that strategy are invalid or incomplete
* the properties does not expose a known `authenticator` value
* a strategy of the same name has already been registered by another plugin

### Example

```js
context.accessors.strategies.add('someStrategy', {
  config: {
    authenticator: 'StrategyConstructorName',
    authenticateOptions: {
      scope: []
    }
  },
  methods: {
    create: 'create',
    delete: 'delete',
    exists: 'exists',
    update: 'update',
    validate: 'validate',
    verify: 'verify'
  }
});
```

---

## remove

{{{since "1.2.0"}}}

Removes an authentication strategy, preventing new authentications from using it.

In a cluster environment, the new strategy is automatically removed from all server nodes.

<aside class="alert alert-warning">
Authentication tokens previously created using that strategy ARE NOT invalidated after using this method.
</aside>

### Arguments

`remove(name)`

* `name`: {sting} authentication strategy name to remove

### Return

The `remove` function returns a promise, resolved once the strategy has been successfully removed.

This promise is rejected if the strategy to remove does not exist, or if it is owned by another plugin.

### Example

```js
context.accessors.strategies.remove('someStrategy');
```
