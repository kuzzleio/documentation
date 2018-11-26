---
layout: sdk.html.hbs
algolia: true
title: constructor
description: SearchResult:constructor
order: 1
algolia: true
---
  

# Constructor

This object can only be instantiated internally by this SDK, and is an easy-to-use representation of a paginated result from a [search]({{ site_base_path }}sdk-reference/collection/search) or a [scroll]({{ site_base_path }}sdk-reference/collection/scroll) request.

---

## Properties

| Property name | Type | Description | get/set |
|--------------|--------|-----------------------------------|---------|
| ``aggregations`` | object | The result of an aggregation produced by a search request | get |
| ``collection`` | Collection | The data collection associated to this document | get |
| ``documents`` | Document[] | An array of instantiated Document objects | get |
| ``fetched`` | number | The number of fetched documents so far | get/set |
| ``options`` | object | The arguments of the search/scroll request | get |
| ``filters`` | object | The filters of the search request | get |
| ``total`` | integer | The total number of results that can be fetched | get |

---
