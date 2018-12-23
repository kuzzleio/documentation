---
layout: sdk.html.hbs
title: Introduction
description: SearchResult class
order: 0
---

# SearchResult

The class is used to retrieve the subsequent paginated results of a search query.  
The following method returns a `SearchResult` or a `SpecificationSearchResult` (which acts the same):
 - [document:search]({{ site_base_path }}sdk-reference/cpp/1/document/search)
 - [collection:searchSpecifications]({{ site_base_path }}sdk-reference/cpp/1/document/search)

## Public class definition

```cpp
namespace kuzzleio {
    class SearchResult {
        public:
            SearchResult* next();
    };

    class SpecificationSearchResult : public SearchResult {
        public:
            SpecificationSearchResult* next();
    };
}
```
