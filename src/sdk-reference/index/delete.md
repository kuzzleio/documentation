---
layout: full.html
algolia: true
title: Delete
description: Delete
order: 200
---

# delete(name, [options])

Deletes an entire data index from Kuzzle.

[code-example=delete]

## Options

Query options.

| Option | Type | Description | Default
|--------|------|-------------|---------
| queuable | boolean | Make this request queuable or not  | true

---

## Callback Response

Returns an object with the index creation status.

| Name | Type | Description
|------|------|-------------
| acknowledged | boolean | indicates whether the index was successfully deleted in the Elastic cluster
