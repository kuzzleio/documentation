---
layout: sdk.html.hbs
title: Getters
description: Getters for SearchResult class
order: 100
---

# SearchResult class getters

## aggregations

Returns a JSON string representing the search [aggregations](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/search-aggregations.html).  

### Signature

```cpp
const std::string& aggregations() const;
```

## hits

Returns a JSON string representing an array of JSON objects containing the matching documents.  
Each object has the following properties:

| Property | Type | Description |
| -------- | ---- | ----------- |
| _id | <pre>string</pre> | Document ID |
| _score | <pre>number</pre> | [Relevance score](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html) |
| _source | <pre>object</pre> | Document content |

### Signature

```cpp
const std::string& hits() const;
```

## total

Returns the total number of found documents. 
Can be greater than the number of documents retrieved by the current `SearchResult` instance.

### Signature

```cpp
unsigned total() const;
```

## fetched

Returns the number retrieved items so far.  

### Signature

```cpp
unsigned fetched() const;
```

## scroll_id

Returns the identifier to the next page of result.

### Signature

```cpp
const std::string& scroll_id() const;
```
