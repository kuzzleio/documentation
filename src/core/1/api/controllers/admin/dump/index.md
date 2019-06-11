---
type: page

code: true
title: dump
---

# dump

<SinceBadge version="1.4.0" />

Asynchronously create a snapshot of Kuzzle's state.
Depending on the configuration of Kuzzle, it may include the following:

- a coredump of Kuzzle
- the current Kuzzle configuration
- server logs
- Node.js binary & properties
- a list of OS properties
- plugins configuration
- usage statistics of the dumped instance

(See [configuration](/core/1/guides/essentials/configuration/))

The generated directory can be used to feed a complete report to the support team.
This report is the same as the one generated during a crash.

**Note:** in a Cluster environment, the dump action will be propagated across all nodes.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/admin/_dump
Method: POST
```

### Other protocols

```json
{
  "controller": "admin",
  "action": "dump"
}
```

---

## Response

Return an acknowledgement.

```js
{
  "requestId": "d16d5e8c-464a-4589-938f-fd84f46080b9",
  "status": 200,
  "error": null,
  "controller": "admin",
  "action": "dump",
  "collection": null,
  "index": null,
  "volatile": null,
  "result": {
    "acknowledge": true
  }
}
```
