---
layout: full.html.hbs
algolia: true
title: mDelete
---


# mDelete

{{{since "1.0.0"}}}

Deletes multiple indexes.


## Body properties

If no index is specified in the body, then all indexes that the current user is allowed to delete will be removed.

### Optional:

* `indexes`: an array of index names to delete


## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
