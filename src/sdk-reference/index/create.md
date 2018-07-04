---
layout: full.html
algolia: true
title: Create
description: Create
order: 200
---

# Create

Create a new empty data index.

## Usage

> create(name, [options])

[code-example=create]

## Options

Query options.

| Option | Type | Description | Default
|--------|------|-------------|---------
| queuable | boolean | Make this request queuable or not  | true

---

## Response

Returns an object with the index creation status.

| Name | Type | Description
|------|------|-------------
| acknowledged | boolean | indicates whether the index was successfully created in the Elastic cluster
| shards_acknowledged | boolean | indicates whether the requisite number of shard copies were started for each shard in the index before timing out
