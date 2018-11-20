---
layout: full.html.hbs
algolia: true
title: Installation
description: learn how to install elasticsearch from scratch
order: 100
---


# Installation

We want you to work with Elasticsearch while you are reading this cookbook,
to do so you will need [cURL](https://curl.haxx.se/download.html), a terminal (Linux, Mac, Cygwin...)
and, optionally, [docker](https://www.docker.com/products/docker) to speed up the installation.

Alternatively you can simply trust the results we provide in the cookbook and skip the installation chapter.


## Confirm Elasticsearch is Running

To ensure that Elasticsearch is running, execute the following command:

```bash
curl -g -X GET "http://localhost:9200/"
```

You should receive a reply similar to the one below. Note that this cookbook assumes that your Elasticsearch `version.number` is above **5.0**:

```javascript
{
    "cluster_name": "elasticsearch",
    "cluster_uuid": "AyJUa63UTlqQgHV9I9UzXQ",
    "name": "kp9tiLV",
    "tagline": "You Know, for Search",
    "version": {
        "build_date": "2016-11-24T10:07:18.101Z",
        "build_hash": "f6b4951",
        "build_snapshot": false,
        "lucene_version": "6.2.1",
        "number": "5.0.2"
    }
}
```
