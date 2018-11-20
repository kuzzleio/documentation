---
layout: sdk.html.hbs
algolia: true
title: scroll
description: Collection:scroll
---

  

# scroll
Returns a [SearchResult]({{ site_base_path }}sdk-reference/search-result/) object containing the next page of the scroll session, and the `scrollId` to be used in the next `scroll` action.  
A scroll session is always initiated by a `search` action and including the `scroll` argument; more information below.

<aside class="notice">
There is a small delay between the time a document is created and its availability in our search layer (usually a couple of seconds). That means that a document that was just created might not be returned by this function at first.
</aside>

<aside class="notice">
  To get more information about scroll sessions, please refer to the <a href="{{ site_base_path }}api-documentation/controller-document/search">API reference documentation</a>.
</aside>


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an instantiated [SearchResult]({{ site_base_path }}sdk-reference/search-result) object.

---
## Usage

[snippet=scroll-1]
