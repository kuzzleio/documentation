---
layout: full.html.hbs
algolia: true
title: Working with Persistent Data
order: 400
---


# Working with Persistent Data

Kuzzle relies on [Elasticsearch](https://www.elastic.co/) to store and fetch persistent data.

In Kuzzle, data is organized as follows:

* **Documents** are atomic units of data that are each defined in JSON format and contain a unique `_id`.
* **Collections** are a group of Documents, identified by a unique name.
* **Indexes** are a group of Collections, identified by a unique name.


## Creating an Index

We will start off by [**creating a new index**]({{ site_base_path }}api/1/controller-index/create/) which we will use to store a collection.

To create a new index, send a `POST` request to the following API endpoint and leave the request body empty:  `http://localhost:7512/<index name>/_create`.


Let's create an index named `myindex`:

```bash
 curl -X POST http://localhost:7512/myindex/_create
```

You should receive the following response:

```json
{
    "requestId": "<random unique request id>",
    "status": 200,
    "error": null,
    "controller": "index",
    "action": "create",
    "collection": null,
    "index": "myindex",
    "volatile": null,
    "result": {
        "acknowledged": true,
        "shards_acknowledged": true
    }
}
```

## Creating a Collection

Next, we will [**create a new collection**]({{ site_base_path }}api/1/controller-collection/create/), which we will use to store documents.

To create a collection, send a `PUT` request to the following API endpoint and leave the request body empty: `http://localhost:7512/<index name>/<colletion name>`.

Let's create the collection `mycollection` in the `myindex` index:

```bash
 curl -X PUT http://localhost:7512/myindex/mycollection
```

You should receive the following response:

```json
{
    "requestId": "<random unique request id>",
    "status": 200,
    "error": null,
    "controller": "collection",
    "action": "create",
    "collection": "mycollection",
    "index": "myindex",
    "volatile": null,
    "result": {
        "acknowledged": true
    }
}
```

**Note:**  we have just created a new collection without specifying any mappings. As a result, the database layer will automatically create a mapping that assigns a best guess data type to any new field it detects in input documents. Since these mappings cannot be changed once they are created, we strongly recommend that you [**create your own mappings**]({{ site_base_path }}guide/1/essentials/persisted/#mappings) as soon as the collection has been created. For the purpose of this tutorial, we will continue without defining our own mappings.


## Document CRUD

Kuzzle ships with a full data [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) API that can be used to manage documents.

### CREATE

We can [**create a new document**]({{ site_base_path }}api/1/controller-document/create) by sending a `POST` request to the following API endpoint and setting the document contents in the request body: `http://localhost:7512/<index name>/<collection name>/_create`.

Let's create a new document in the `mycollection` collection of the `myindex` index:

```bash
 curl -X POST -H "Content-Type: application/json" -d '{"message": "Hello World!"}' http://localhost:7512/myindex/mycollection/_create
```

You should receive the following response (with your own `_id` value):

```json
{
  "status": 200,
  "error": null,
  "requestId": "<random unique request id>",
  "controller": "document",
  "action": "create",
  "collection": "mycollection",
  "index": "myindex",
  "volatile": null,
  "headers": {},
  "result": {
    "_index": "myindex",
    "_type": "mycollection",
    "_id": "AVkDBl3YsT6qHI7MxLz0",
    "_version": 1,
    "result": "created",
    "_shards": {
      "total": 2,
      "successful": 1,
      "failed": 0
    },
    "created": true,
    "_source": {
      "message": "Hello World!",
      "_kuzzle_info": {
        "author": "-1",
        "createdAt": 1481814465050,
        "updatedAt": null,
        "updater": null,
        "active": true,
        "deletedAt": null
      }
    },
    "_meta": {
      "author": "-1",
      "createdAt": 1481814465050,
      "updatedAt": null,
      "updater": null,
      "active": true,
      "deletedAt": null
    }
  }
}
```

Note that the document contains the auto-generated id `AVkDBl3YsT6qHI7MxLz0`. Take some time to examine the content of the [response]({{ site_base_path }}guide/1/essentials/request-and-response-format/#status-codes) message as it contains useful information, like the name of the controller, the action performed in the request, and of course the object we just created in the `source` field.



### READ


We can [**read a document**]({{ site_base_path }}api/1/controller-document/get)  by sending a `GET` request to `http://localhost:7512/<index name>/<collection name>/<document id>`.

Let's read the document we just created in the `mycollection` collection of the `myindex` index:

```bash
 curl http://localhost:7512/myindex/mycollection/AVkDBl3YsT6qHI7MxLz0
```

You should receive the following response (with your own `_id` value):

```json
{
  "requestId": "<random unique request id>",
  "status": 200,
  "error": null,
  "controller": "document",
  "action": "get",
  "collection": "mycollection",
  "index": "myindex",
  "volatile": null,
  "result": {
    "_index": "myindex",
    "_type": "mycollection",
    "_id": "AVkDBl3YsT6qHI7MxLz0",
    "_version": 1,
    "found": true,
    "_source": {
      "message": "Hello World!",
      "_kuzzle_info": {
        ...
      }
    },
    "_meta": {
      ...
    }
  }
}
```

### UPDATE

We can [**update a document**]({{ site_base_path }}api/1/controller-document/update) by sending a `PUT` request to the following API endpoint and setting the document's updated contents in the request body: `http://localhost:7512/<index name>/<collection name>/<document id>/_update`.

Let's update the document we just created, with id `AVkDBl3YsT6qHI7MxLz0`, in the `mycollection` collection of the `myindex` index:


```bash
 curl -X PUT -H "Content-Type: application/json" -d '{"message": "in a bottle","an_englishman":"in New York"}' http://localhost:7512/myindex/mycollection/AVkDBl3YsT6qHI7MxLz0/_update
```

You should receive the following response (with your own `_id` value):

```json
{
  "status": 200,
  "error": null,
  "requestId": "<random unique request id>",
  "controller": "document",
  "action": "update",
  "collection": "mycollection",
  "index": "myindex",
  "volatile": null,
  "headers": {},
  "result": {
    "_index": "myindex",
    "_type": "mycollection",
    "_id": "AVkDBl3YsT6qHI7MxLz0",
    "_version": 2,
    "result": "updated",
    "_shards": {
      "total": 2,
      "successful": 1,
      "failed": 0
    }
  }
}
```

### DELETE


We can [**delete a document**]({{ site_base_path }}api/1/controller-document/delete)  by sending a `DELETE` request to the following API endpoint with no request body: `http://localhost:7512/<index name>/<collection name>/<document id>`.

Let's delete the document we just created in the `mycollection` collection of the `myindex` index:

```bash
 curl -X DELETE http://localhost:7512/myindex/mycollection/AVkDBl3YsT6qHI7MxLz0
```

You should receive the following response (with your own `_id` value):

```json
{
  "requestId": "<random unique request id>",
  "status": 200,
  "error": null,
  "controller": "document",
  "action": "delete",
  "collection": "mycollection",
  "index": "myindex",
  "volatile": null,
  "result": {
    "_index": "myindex",
    "_type": "mycollection",
    "_id": "AVkDBl3YsT6qHI7MxLPOSTz0",
    "_version": 3,
    "result": "updated",
    "_shards": {
      "total": 2,
      "successful": 1,
      "failed": 0
    }
  }
}
```



## Mappings

Kuzzle uses Elasticsearch as a persistent document store, which uses mappings to assign a type to a document field. These mappings are attached to a `collection` (aka `type` in Elasticsearch terminology) and are automatically inferred from input documents if no mappings are preconfigured.

If you want to control how Kuzzle interprets your documents, we recommend that you configure your own mappings using our mappings creation endpoint.

Create a mapping by sending a `PUT` request to the `http://localhost:7512/<index name>/<collection name>/_mapping` and setting the mapping in the request body.

Use [this](https://www.elastic.co/guide/1/en/elasticsearch/reference/5.x/mapping.html) syntax when definng a mapping. For example, if we want to create a mapping that will define a field `birthday` as a `date` type, we would send the following JSON in the body:

```json
{
  "properties": {
    "birthday": {
      "type": "date"
    }
  }
}
```

Let's try it:

```bash
 curl -X PUT -H "Content-Type: application/json" -d '{"properties":{"birthday":{"type": "date"}}}' http://localhost:7512/myindex/mycollection/_mapping
```

You should receive the following response:

```json
{
  "requestId": "<random unique request id>",
  "status": 200,
  "error": null,
  "controller": "collection",
  "action": "updateMapping",
  "collection": "mycollection",
  "index": "myindex",
  "volatile": null,
  "result": {
    "acknowledged": true
  }
}
```

Here we have now defined the type `date` for the field labeled `birthday` in our `mycollection` collection. Defining types in this way can be especially useful when dealing with specific types (`date`, `geo_shape`, etc.), full-text search, or complex data structures.

Please note that the mappings of a collection cannot be updated once they are created, this is true whether you create the rules yourself using our API or whether Elasticsearch generates the rules automatically based on the input documents it processes. Because of this, you should almost always define mappings when you first create your collections and before creating any documents.

---

## What Now?


* Read our [Elasticsearch Cookbook]({{ site_base_path }}elasticsearch-cookbook) to learn more about how querying works in Kuzzle
* Use [document metadata]({{ site_base_path }}guide/1/essentials/document-metadata) to find or recover documents
* Keep track of data changes using [Real-time Notifications]({{ site_base_path }}guide/1/essentials/real-time)
