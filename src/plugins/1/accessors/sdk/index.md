---
layout: full.html.hbs
title: sdk
---

# sdk

{{{since "1.6.0"}}}

Accessor to the embedded SDK. 

The embedded SDK is a custom version of our [Javascript SDK]({{ site_base_path }}sdk-reference/js/6) that uses a custom protocol plugged directly into Kuzzle core.  

All the documented controllers can be used, except the `realtime` one.  
Also, the low-level [query]({{ site_base_path }}sdk-reference/js/6/kuzzle/query/) method is available for use.

### Request context

By default, when using the embedded SDK, requests made to Kuzzle API don't have the same context as the original request received by the plugin.  

Typically, the `request.context.user` property is not correctly set and thus [Kuzzle metadata]({{ site_base_path }}guide/1/essentials/document-metadata) will not be properly set when creating or updating documents.

To avoid this, we also expose an `as()` method that take an user in parameter and allow to execute the following request as the provided user.  
This only allows to have metadata properly set. Users rights and permissions won't be checked before executing the request.

When the complete original context is needed to execute your request, plugin developers can use the [accessors.execute]({{ site_base_path }}plugins/1/accessors/execute) method.

---

## controllers

The following controllers are available in the embedded SDK:
  - [auth controller]({{ site_base_path }}sdk-reference/js/6/auth)
  - [bulk controller]({{ site_base_path }}sdk-reference/js/6/bulk)
  - [collection controller]({{ site_base_path }}sdk-reference/js/6/collection)
  - [document controller]({{ site_base_path }}sdk-reference/js/6/document)
  - [index controller]({{ site_base_path }}sdk-reference/js/6/index)
  - [memoryStorage (ms) controller]({{ site_base_path }}sdk-reference/js/6/ms)
  - [security controller]({{ site_base_path }}sdk-reference/js/6/security)
  - [server controller]({{ site_base_path }}sdk-reference/js/6/server)

### Example

```js
async myAwesomePipe (request) {
  await this.context.accessors.sdk.document.create(
    'nyc-open-data',
    'yellow-taxi',
    { licence: 'B' }
  );

  return request;
}
```

**Notes:**

* The created document will have the `author` metadata property set to `null`.

---

## query

Accessor to the [query method]({{ site_base_path }}sdk-reference/js/6/kuzzle/query).  
This can be useful to call plugins custom controller action.

### Example

```js
async myAwesomePipe (request) {
  await this.context.accessors.sdk.query({
    controller: 'custom-plugin/derbyController',
    action: 'play',
    body: {
      type: 'roller',
      playerIds: [21, 42, 84]
    }
  });

  return request;
}
```

---

## as

Execute the following query as the original request user.

### Arguments

```js
as(user)
```

<br/>

| Arguments | Type | Description |
|-----------|------|-------------|
| `user` | <pre>User</pre> | Valid User object |

### Example

```js
async myAwesomePipe (request) {
  await this.context.accessors.sdk.as(request.context.user).document.create(
    'nyc-open-data',
    'yellow-taxi',
    { licence: 'B' }
  );

  return request;
}
```

**Notes:**

* The created document will have the `author` metadata property set to the impersonated user ID.

### Return

Returns the embedded SDK contextualized with the provided user.
