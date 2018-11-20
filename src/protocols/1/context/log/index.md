---
layout: full.html.hbs
algolia: true
title: log
---


# log

The `context.log` object exposes functions used to send messages to Kuzzle's logging system.

Log levels are assigned to each exposed log function, corresponding to the log priority.  
The lower the log level, the higher its priority.

Levels above the threshold configued in the `logs` section of Kuzzle's [configuration file]({{ site_base_path }}guide/1/essentials/configuration) are ignored.


## warn

{{{since "1.0.0"}}}

### Priority

`1`

### Example

```js
context.log.warn('warning message');
```


## verbose

{{{since "1.0.0"}}}

### Priority

`3` (ignored by default)

### Example 

```js
context.log.verbose('verbose message');
```


## silly

{{{since "1.0.0"}}}

### Priority

`5` (lowest priority, usually ignored)

### Example

```js
context.log.silly('silly message');
```
