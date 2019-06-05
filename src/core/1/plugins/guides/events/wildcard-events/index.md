---
type: page
code: false
title: Wildcard Events
order: 250
---

# Wildcard Events

Kuzzle allows you to listen to multiple events with a single listener thanks to wildcards (`*`).

---

## Name wildcard

| Arguments | Type                                                           | Description                |
| --------- | -------------------------------------------------------------- | -------------------------- |
| `*` | `*` | Depends on the event type |

You can use wildcards to replace only the name of an event.

### Naming Template

The event name is built using the following template:

`<controller>:*`

- `controller`: controller name

#### Example

```javascript
this.pipes = 
  // listener function will trigger at each
  // events triggered in the document namespace
  'document:*': <function to call> 
};
```

---

## Templated events wildcard

| Arguments | Type                                                           | Description                |
| --------- | -------------------------------------------------------------- | -------------------------- |
| `request` | <pre><a href=/plugins/1/constructors/request>Request</a></pre> | The normalized API request |

Wildcards permit listenening templated events.

### Naming Template

The event name is built using the following template:

`<controller>:<template>*`

- `controller`: API controller name
- `template`: `before`, `after` or `error`

#### Example

```javascript
this.pipes = 
  // listener function will trigger after each
  // events triggered in the document namespace
  'document:after*': <function to call>
};
```