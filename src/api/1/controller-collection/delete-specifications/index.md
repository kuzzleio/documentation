---
layout: full.html.hbs
algolia: true
title: deleteSpecifications
---


# deleteSpecifications

{{{since "1.0.0"}}}

Deletes validation specifications for a data collection.

The request succeeds even if no specification exist for that data collection.

***Note:***  an empty specification is implicitly applied to all collections. In a way, "no specification set" means "all documents are valid". 


## Arguments

* `collection`: data collection
* `index`: data index


## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
