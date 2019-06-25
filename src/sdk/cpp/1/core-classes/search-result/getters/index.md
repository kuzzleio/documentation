---
code: true
type: page
title: Getters
description: Getters for SearchResult class
order: 100
---

# SearchResult class getters

## aggregations

Returns a JSON string representing the search [aggregations](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-aggregations.html).

### Arguments

```cpp
char const* aggregations() const;
```

## fetched

Returns the number retrieved items so far.

### Arguments

```cpp
unsigned fetched() const;
```

## hits

Returns a JSON string representing an array of JSON objects containing the matching documents.  
Each object has the following properties:

| Property | Type              | Description                                                                                         |
| -------- | ----------------- | --------------------------------------------------------------------------------------------------- |
| \_id     | <pre>string</pre> | Document ID                                                                                         |
| \_score  | <pre>number</pre> | [Relevance score](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html) |
| \_source | <pre>object</pre> | Document content                                                                                    |

### Arguments

```cpp
char const* hits() const;
```

## total

Returns the total number of found documents.
Can be greater than the number of documents retrieved by the current `SearchResult` instance.

### Arguments

```cpp
unsigned total() const;
```

## scroll_id

Returns the identifier to the next page of result.

### Arguments

```cpp
char const* scroll_id() const;
```

## Usage

<<< ./snippets/getters.cpp
