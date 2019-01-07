---
layout: sdk.html.hbs
title: updateMapping
description: Updates the collection mapping
---

# updateMapping

Updates the collection mapping.  
Mapping allows you to exploit the full capabilities of our
persistent data storage layer, [ElasticSearch](https://www.elastic.co/products/elasticsearch) (check here the [mapping capabilities of ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/mapping.html)).

## Signature

```java
public void updateMapping(String index, String collection, String mapping) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.NotFoundException;
public void updateMapping(String index, String collection, String mapping, io.kuzzle.sdk.QueryOptions options) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.NotFoundException;
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``collection`` | String | Collection name    | yes  |
| ``mapping`` | String | Collection data mapping in JSON format  | yes  |
| `options` | io.kuzzle.sdk.QueryOptions | The query options | no       |

### **mapping**

An string containing the JSON representation of the collection data mapping.  

The mapping must have a root field `properties` containing the mapping definition:
```json
{
  "properties": {
    "field1": { "type": "text" },
    "field2": {
      "properties": {
        "nestedField": { "type": "keyword"}
      }
    }
  }
}
```

You can see the full list of Elasticsearch mapping types [here](https://www.elastic.co/guide/en/elasticsearch/reference/5.4/mapping.html).

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/error-handling).

## Usage

[snippet=update-mapping]
