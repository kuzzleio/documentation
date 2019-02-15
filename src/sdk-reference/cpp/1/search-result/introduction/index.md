---
layout: sdk.html.hbs
title: Introduction
description: SearchResult class
order: 0
---

# SearchResult

The class is used to retrieve the subsequent paginated results of a search query.  
The following methods returns a `SearchResult` or a `SpecificationSearchResult` (which acts the same):
 - [document:search]({{ site_base_path }}sdk-reference/cpp/1/document/search)
 - [collection:searchSpecifications]({{ site_base_path }}sdk-reference/cpp/1/collection/search-specifications)

## Public class definition

```cpp
namespace kuzzleio {
    class SearchResult {
        public:
            SearchResult* next() const;

            // Getters
            size_t total() const;
            size_t fetched() const;
            const std::string& aggregations() const;
            const std::string& hits() const;
            const std::string& scroll_id() const;
    };

    class SpecificationSearchResult : public SearchResult {
        public:
            SpecificationSearchResult* next() const;
    };
}
```
