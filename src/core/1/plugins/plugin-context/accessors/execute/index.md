---
code: true
type: page
title: execute
---

# execute



Executes a Kuzzle's [API action](/core/1/api/).

---

## Arguments

```js
execute(request, [callback]);
```

<br/>

| Arguments  | Type                                                           | Description                                    |
| ---------- | -------------------------------------------------------------- | ---------------------------------------------- |
| `request`  | [`Request`](/core/1/plugins/constructors/request) | The API query to execute                       |
| `callback` | <pre>function</pre>                                            | Callback to call with the API execution result |

---

## Return

The `execute` function resolves to an updated Request object, with its [response part](/core/1/plugins/plugin-context/constructors/request) set.

How the response is returned depends whether a callback argument is provided:

- if it is: the `execute` function returns nothing, and the callback is called once the API call is finished, with the following arguments: `callback(error, request)`
- otherwise: the `execute` function returns a promise, resolving to the updated request, or rejected with a KuzzleError object

---

## Example

```js
let request = new context.constructors.Request({
  controller: 'document',
  action: 'get',
  index: 'nyc-open-data',
  collection: 'yellow-taxi',
  _id: 'AWaCRnfbiSV6vMG7iV_K'
});

try {
  // "request" is the updated Request object
  // The API response is accessed through "request.response"
  request = await context.accessors.execute(request);
} catch (error) {
  // "error" is a KuzzleError object
}
```
