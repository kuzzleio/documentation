---
layout: full.html.hbs
algolia: true
title: scrollSpecifications
---


# scrollSpecifications

{{{since "1.0.0"}}}

Moves a result set cursor forward, created by the [`searchSpecifications` request]({{ site_base_path }}api/1/controller-collection/search-specifications/) when the `scroll` argument is provided.

Results returned by a `scroll` request reflect the state of the index at the time of the initial search request, like a fixed snapshot. Subsequent changes to documents do not affect the scroll results.


## Arguments

* `collection`: data collection
* `index`: data index
* `scrollId`: cursor identifier, obtained with ({{ site_base_path }}api/1/controller-collection/search-specifications)

### Optional:

* `scroll`: reset the cursor TTL to the provided duration, using the [time to live](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/common-options.html#time-units) format.


## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/errors/#notfounderror)
