---
layout: full.html
algolia: true
title: Create
description: Create
order: 200
---

# create(name, [options])

Create a new empty data index.

[code-example=create]

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
| acknowledged | boolean | indicates whether the index was successfully created in the Elastic cluster
| shards_acknowledged | boolean | indicates whether the requisite number of shard copies were started for each shard in the index before timing out
