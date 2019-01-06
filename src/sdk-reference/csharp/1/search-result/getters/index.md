---
layout: sdk.html.hbs
title: Getters
description: Getters for SearchResult class
order: 100
---

## Signature

```csharp
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

```csharp
string hits() const;
```

### Signature

```csharp
string hits() const;
```

### Signature

```csharp
int total() const;
```

### Signature

```csharp
int total() const;
```

### Signature

```csharp
int total() const;
```

### Signature

```csharp
int total() const;
```

### Signature

```csharp
int fetched() const;
```

### Signature

```csharp
int fetched() const;
```

### Signature

```csharp
int fetched() const;
```

## scroll_id

Returns the identifier to the next page of result.

