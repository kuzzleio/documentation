---
layout: sdk.html.hbs
title: Introduction
description: SearchResult class
order: 0
---

# SearchResult

The class is used to retrieve the subsequent paginated results of a search query.  
The following methods returns a `SearchResult`:
  - [document:search]({{ site_base_path }}sdk-reference/cpp/1/document/search)

## Public class definition

```cpp
namespace kuzzleio {
    class SearchResult {
        public:
            std::shared_ptr<SearchResult> next() const;

            // Getters
            char const* aggregations() const;
            char const* hits() const;
            char const* scroll_id() const;
            size_t total() const;
            size_t fetched() const;
    };
}
```
