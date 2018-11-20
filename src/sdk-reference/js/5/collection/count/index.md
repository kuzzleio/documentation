---
layout: sdk.html.hbs
algolia: true
title: count
description: Collection:count
---

  

# count
Returns the number of documents matching the provided set of filters.

<aside class="notice">
There is a small delay between the time a document is created and its availability in our search layer (usually a couple of seconds). That means that a document that was just created might not be returned by this function at first.
</aside>


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns a count for the number of document matches as an ``integer``.

## Usage

[snippet=count-1]
> Callback response:

```json
12
```