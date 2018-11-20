---
layout: full.html.hbs
algolia: true
title: list
---


# list

{{{since "1.0.0"}}}

Returns the list of data collections associated to a provided data index.

The returned list is sorted in alphanumerical order.  


## Arguments


* `collection`: data collection
* `index`: data index

### Optional:

* `from` and `size`: response pagination
* `type`: filters the returned collections. Allowed values: `all`, `stored` and `realtime` (default : `all`).  


## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/errors/#notfounderror)
