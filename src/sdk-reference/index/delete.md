---
layout: full.html
algolia: true
title: Delete
description: Delete
order: 300
---

# Delete

Deletes an entire data index from Kuzzle.

## Usage

> delete(name, [options])

[code-example=delete]

## Options

Query options.

| Option | Type | Description | Default
|--------|------|-------------|---------
| queuable | boolean | Make this request queuable or not  | true

---

## Response

Returns an object with the index deletion status.

| Name | Type | Description
|------|------|-------------
| acknowledged | boolean | indicates whether the index was successfully deleted in the Elastic cluster
