---
layout: full.html.hbs
title: Define database mapping
order: 400
---

# Database mappings for collections

With Elasticsearch, it is possible to define mappings for collections. These mappings allows you to configure the way Elasticsearch will handle these collections.

There are 3 root fields for mapping configuration:
 - [properties]({{ site_base_path}}guide/1/essentials/database-mappings/#properties-types-definition): collection types definition
 - [dynamic]({{ site_base_path}}guide/1/essentials/database-mappings/#dynamic-mapping-policy): dynamic mapping policy against new fields
 - [_meta]({{ site_base_path}}guide/1/essentials/database-mappings/#collection-metadata): collection metadata

The following API methods can be used to modify these mappings:
 - [collection:create]({{ site_base_path }}api/1/controller-collection/create/)
 - [collection:updateMapping]({{ site_base_path }}api/1/controller-collection/update-mapping/)

---

## Properties types definition

The definition of the types of fields that will be inserted in a collection allows Elasticsearch to optimize the indexing of your data for future searches.  

Especially when searching on fields with special types such as `date` or `geo_shape`.

<div class="alert alert-warning">
Once a type has been defined for a field, it is not possible to modify it later.
</div>

Refer to the Elasticsearch documentation for an exhaustive list of available types: https://www.elastic.co/guide/en/elasticsearch/reference/5.6/mapping-types.html

### Example

If I want the following document to be correctly indexed:
```javascript
{
  "category": "limousine",
  "distance": 120990,
  "position": {
    "lat": 27.730400,
    "lon": 85.328467
  },
  "driver": {
    "name": "liia mhe ry"
  }
}
```

The following mapping must first be defined:
```javascript
{
  "properties": {
    "category": { "type": "keyword" },
    "distance": { "type": "integer" },
    "position": { "type": "geo_point" },
    "driver": {
      "properties": {
        "name": { "type": "keyword" }
      }
    }
  }
}
```

This mapping is then passed in the body to the methods [collection:create]({{ site_base_path }}api/1/controller-collection/create/) or [collection:updateMapping]({{ site_base_path }}api/1/controller-collection/update-mapping/).

```bash
# First create a collection yellow-taxi in the nyc-open-data index
curl -X PUT -d '{"properties":{"category":{"type":"keyword"},"distance":{"type":"integer"},"position":{"type":"geo_point"},"driver":{"properties":{"name":{"type":"keyword"}}}}}' -H "Content-Type: application/json" "http://localhost:7512/nyc-open-data/yellow-taxi?pretty"

# Then create the desired document
curl -X POST -d '{"category":"limousine","distance":120990,"position":{"lat":27.7304,"lon":85.328467},"driver":{"name":"liia meh ry"}}' -H "Content-Type: application/json" "http://localhost:7512/nyc-open-data/yellow-taxi/_create?pretty"
```

<div class="alert alert-warning">
Because of the way Elasticsearch manages collections, mappings are shared between indexes.
<br/>
This means that if I have an index <code>nyc-open-data</code>, two collections <code>yellow-taxi</code> and <code>green-taxi</code> and a field <code>name</code> with type <code>keyword</code> in the collection <code>yellow-taxi</code>, I couldn't have a field <code>name</code> with a different type in the collection <code>green-taxi</code>.
</div>

---

## Dynamic mapping policy

For each collection, you can set the policy against new fields that are not referenced in the collection mapping by modifying the `dynamic` root field.

The value of this configuration will change the way Elasticsearch manages the creation of new fields that are not declared in the collection mapping.
  - `"true"`: Stores the document and updates the collection mapping with the inferred type
  - `"false"`: Stores the document and does not update the collection mapping (fields are not indexed)
  - `"strict"`: Rejects the document

Refer to Elasticsearch documentation for more informations: https://www.elastic.co/guide/en/elasticsearch/guide/current/dynamic-mapping.html

The default policy for new collections is `"true"` and is configurable in the [kuzzlerc]({{ site_base_path }}guide/1/essentials/configuration/) file under the key `services.db.dynamic`.

<div class="alert alert-warning">
We advise not to let Elasticsearch dynamically infer the type of new fields in production.
<br/>
This can be a problem because then the mapping cannot be modified.
</div>

It is also possible to specify a different dynamic mapping policy for nested fields. This can be useful in imposing a strict policy on the collection while allowing the introduction of new fields in a specific location.
  
### Example

If you want a `strict` dynamic policy for your entire collection, you have to define it in root level but you can have a different policy for nested types:

```javascript
{
  "dynamic": "strict"
  "properties": {
    "driver": {
      "dynamic": "false"
      "properties": // allow insertion of new fields in the driver nested field
    }
  }
}
```
<br/>
```bash
# Define a strict dynamic policy for the yellow-taxi collection
curl -X PUT -d '{ "dynamic": "strict" }' -H "Content-Type: application/json"  "http://localhost:7512/nyc-open-data/yellow-taxi?pretty"

# Try to create a document with a field that is not referenced in the mapping
curl -X POST -d '{"language":"nepali"}' -H "Content-Type: application/json" "http://localhost:7512/nyc-open-data/yellow-taxi/_create?pretty"

# You should see an error with the following message:
# "mapping set to strict, dynamic introduction of [language] within [yellow-taxi] is not allowed"
```

---

## Collection metadata

Elasticsearch allows the definition of metadata that is stored next to the collections in the root field `_meta`.  
These metadata are ignored by Elasticsearch, they can contain any type of information specific to your application.

<div class="alert alert-warning">
Unlike the properties types definition, new collection metadata are not merged with the old one.
<br/>
If you set the <code>_meta</code> field in your request, the old value will be overwritten.
</div>

Refer to Elasticsearch documentation for more informations: https://www.elastic.co/guide/en/elasticsearch/reference/5.6/mapping-meta-field.html

These metadata can be retrieved with the [collection:getMapping]({{ site_base_path }}api/1/controller-collection/get-mapping/) API method.

### Example

```javascript
{
  "_meta": {
    "area": "Panipokhari"
  }
}
```
<br/>
```bash
# Add collection metadata
curl -X PUT -d '{ "_meta": { "area": "Panipokhari" } }' -H "Content-Type: application/json"  "http://localhost:7512/nyc-open-data/yellow-taxi/_mapping?pretty"

# Retrieve it
curl -X GET -H "Content-Type: application/json"  "http://localhost:7512/nyc-open-data/yellow-taxi/_mapping?pretty"
```

---

## What Now?

* Learn to work with [Persistent Data]({{ site_base_path }}guide/1/essentials/persisted)
* Read our [Elasticsearch Cookbook]({{ site_base_path }}guide/1/elasticsearch) to learn more about how querying works in Kuzzle
* Use [document metadata]({{ site_base_path }}guide/1/essentials/document-metadata) to find or recover documents
* Keep track of data changes using [Real-time Notifications]({{ site_base_path }}guide/1/essentials/real-time)
