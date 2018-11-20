---
layout: full.html.hbs
algolia: true
title: Basic Queries
description: learn how to search data with elasticsearch
order: 400
---


# Basic Queries

Here we present the most common ways to perform search queries,
including the options that modify their behavior.
For more details about these options refer to the
[Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/query-dsl.html).



## The `ids` query

Returns the documents with a matching `id`.

```bash
curl -g -X POST "http://localhost:9200/example/blogpost/_search?pretty" -d '{
  "query": {
    "ids": {
      "values": ["2", "4"]
    }
  }
}'
```

Reply:

```json
{
  "took" : 2,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "failed" : 0
  },
  "hits" : {
    "total" : 2,
    "max_score" : 1.0,
    "hits" : [ {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "2",
      "_score" : 1.0,
      "_source" : {
        "author" : "John Doe",
        "title" : "I like dogs",
        "body" : "They are loyal",
        "tags" : [ "pet", "animal", "dog" ],
        "status" : "published",
        "publish_date" : "2016-08-01"
      }
    }, {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "4",
      "_score" : 1.0,
      "_source" : {
        "author" : "Jane Doe",
        "title" : "I hate cheese cake",
        "body" : "I prefer chocolat cake",
        "tags" : [ "food", "cake" ],
        "status" : "archived",
        "publish_date" : "1985-08-03"
      }
    } ]
  }
}
```


## The `match` query

The `match` query is the one you want to use to perform a full text search.
The query you use (here: "hate cake") is analyzed (lowercased, tokenized ...)
and then is applied against the analyzed version of the field (which is also lowercased, tokenized...).
As a result, the choice of the analyzer applied to a field is very important. To know more about analyzers,
we recommend you to read the
[Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/analysis-analyzers.html).

It returns a set of documents that includes a score.

```bash
curl -g -X POST "http://localhost:9200/example/blogpost/_search?pretty" -d '{
  "query": {
    "match": {
      "title":"hate cake"
    }
  }
}'
```

Reply:

```json
{
  "took" : 3,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "failed" : 0
  },
  "hits" : {
    "total" : 2,
    "max_score" : 1.2201192,
    "hits" : [ {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "4",
      "_score" : 1.2201192,
      "_source" : {
        "author" : "Jane Doe",
        "title" : "I hate cheese cake",
        "body" : "I prefer chocolat cake",
        "tags" : [ "food", "cake" ],
        "status" : "archived",
        "publish_date" : "1985-08-03"
      }
    }, {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "3",
      "_score" : 0.23384948,
      "_source" : {
        "author" : "John Smith",
        "title" : "I hate fish",
        "body" : "They do not bring the ball back",
        "tags" : [ "pet", "animal", "fish" ],
        "status" : "pending",
        "publish_date" : "2017-08-03"
      }
    } ]
  }
}
```

You can see that the second document does not have a title that contains the text "cake" but it is still considered a match.
This is because, by default, the `match` query operator applies an `or` operand to the provided search terms.
In order to return documents matching all tokens, you have to use the `and` operator:

```bash
curl -g -X POST "http://localhost:9200/example/blogpost/_search?pretty" -d '{
  "query": {
    "match": {
      "title": {
        "query": "hate cake",
        "operator": "and"
      }
    }
  }
}'
```

Reply:

```json
{
  "took" : 1,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "failed" : 0
  },
  "hits" : {
    "total" : 1,
    "max_score" : 1.2201192,
    "hits" : [ {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "4",
      "_score" : 1.2201192,
      "_source" : {
        "author" : "Jane Doe",
        "title" : "I hate cheese cake",
        "body" : "I prefer chocolat cake",
        "tags" : [ "food", "cake" ],
        "status" : "archived",
        "publish_date" : "1985-08-03"
      }
    } ]
  }
}
```


## The `range` query

The `range` query matches all documents where the value of the specified field is included within the given range.
In the following example, we want to match all the document where `published_date` is within the two specified dates:

```bash
curl -g -X POST "http://localhost:9200/example/blogpost/_search?pretty" -d '{
  "query": {
    "range": {
      "publish_date": {
        "gte": "2016-08-01",
        "lte": "2016-08-31",
        "format": "yyyy-MM-dd"
      }
    }
  }
}'
```

Reply:

```json
{
  "took" : 6,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "failed" : 0
  },
  "hits" : {
    "total" : 3,
    "max_score" : 1.0,
    "hits" : [ {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "1",
      "_score" : 1.0,
      "_source" : {
        "author" : "John Doe",
        "title" : "I love cats",
        "body" : "They are so cute",
        "tags" : [ "pet", "animal", "cat" ],
        "status" : "pending",
        "publish_date" : "2016-08-03"
      }
    }, {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "2",
      "_score" : 1.0,
      "_source" : {
        "author" : "John Doe",
        "title" : "I like dogs",
        "body" : "They are loyal",
        "tags" : [ "pet", "animal", "dog" ],
        "status" : "published",
        "publish_date" : "2016-08-01"
      }
    }, {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "5",
      "_score" : 1.0,
      "_source" : {
        "author" : "Will Smith",
        "title" : "I admire lions",
        "body" : "They are so regal",
        "tags" : [ "wild animal", "animal", "lion" ],
        "status" : "published",
        "publish_date" : "2016-08-02"
      }
    } ]
  }
}

```


## The `terms` query

Behaves exactly like `term`, but with multiple possible exact matches.

```bash
curl -g -X POST "http://localhost:9200/example/blogpost/_search?pretty" -d '{
  "query": {
    "terms": {
      "status": ["pending", "archived"]
    }
  }
}'
```

Reply:

```json
{
  "took" : 1,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "failed" : 0
  },
  "hits" : {
    "total" : 3,
    "max_score" : 0.7524203,
    "hits" : [ {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "4",
      "_score" : 0.7524203,
      "_source" : {
        "author" : "Jane Doe",
        "title" : "I hate cheese cake",
        "body" : "I prefer chocolat cake",
        "tags" : [ "food", "cake" ],
        "status" : "archived",
        "publish_date" : "1985-08-03"
      }
    }, {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "1",
      "_score" : 0.46769896,
      "_source" : {
        "author" : "John Doe",
        "title" : "I love cats",
        "body" : "They are so cute",
        "tags" : [ "pet", "animal", "cat" ],
        "status" : "pending",
        "publish_date" : "2016-08-03"
      }
    }, {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "3",
      "_score" : 0.46769896,
      "_source" : {
        "author" : "John Smith",
        "title" : "I hate fish",
        "body" : "They do not bring the ball back",
        "tags" : [ "pet", "animal", "fish" ],
        "status" : "pending",
        "publish_date" : "2017-08-03"
      }
    } ]
  }
}

```


## The `missing` query

The `missing` query has been removed in Elasticsearch 5.x. and they recommend that the `exists` query be used with a `must_not`
occurence of a `bool` compound query (and this will introduce you to the `bool` query :-) ).

```bash
#!/bin/bash

curl -g -X POST "http://localhost:9200/example/blogpost/_search?pretty" -d '{
  "query": {
    "bool": {
      "must_not": {
        "exists": {
          "field": "author"
        }
      }
    }
  }
}'
```

Reply:

```json
{
  "took" : 4,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "failed" : 0
  },
  "hits" : {
    "total" : 0,
    "max_score" : null,
    "hits" : [ ]
  }
}
```

---

## Sorting the Result

If you want to sort your result set in a different order than the `_score` default sort
or compound the `_score` sort with other fields, you can specify the sort order in the query:

```bash
curl -g -X POST "http://localhost:9200/example/blogpost/_search?pretty" -d '{
  "query": {
    "match_all": {}
  },
  "sort": [
    {"status": {"order": "asc"}}
  ]
}'
```

Reply:

```json
{
  "took" : 9,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "failed" : 0
  },
  "hits" : {
    "total" : 5,
    "max_score" : null,
    "hits" : [ {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "4",
      "_score" : null,
      "_source" : {
        "author" : "Jane Doe",
        "title" : "I hate cheese cake",
        "body" : "I prefer chocolat cake",
        "tags" : [ "food", "cake" ],
        "status" : "archived",
        "publish_date" : "1985-08-03"
      },
      "sort" : [ "archived" ]
    }, {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "1",
      "_score" : null,
      "_source" : {
        "author" : "John Doe",
        "title" : "I love cats",
        "body" : "They are so cute",
        "tags" : [ "pet", "animal", "cat" ],
        "status" : "pending",
        "publish_date" : "2016-08-03"
      },
      "sort" : [ "pending" ]
    }, {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "3",
      "_score" : null,
      "_source" : {
        "author" : "John Smith",
        "title" : "I hate fish",
        "body" : "They do not bring the ball back",
        "tags" : [ "pet", "animal", "fish" ],
        "status" : "pending",
        "publish_date" : "2017-08-03"
      },
      "sort" : [ "pending" ]
    }, {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "2",
      "_score" : null,
      "_source" : {
        "author" : "John Doe",
        "title" : "I like dogs",
        "body" : "They are loyal",
        "tags" : [ "pet", "animal", "dog" ],
        "status" : "published",
        "publish_date" : "2016-08-01"
      },
      "sort" : [ "published" ]
    }, {
      "_index" : "example",
      "_type" : "blogpost",
      "_id" : "5",
      "_score" : null,
      "_source" : {
        "author" : "Will Smith",
        "title" : "I admire lions",
        "body" : "They are so regal",
        "tags" : [ "wild animal", "animal", "lion" ],
        "status" : "published",
        "publish_date" : "2016-08-02"
      },
      "sort" : [ "published" ]
    } ]
  }
}

```

If the `_score` is not used in the sort, it is not calculated and nullified in the reply.
