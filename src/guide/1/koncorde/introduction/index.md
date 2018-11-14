---
layout: full.html.hbs
title: Introduction
order: 0
description: Introduction to Koncorde
---

## Koncorde

Here you will find documentation on [Koncorde](https://www.npmjs.com/package/koncorde),
which is part of Kuzzle's real-time engine, and is used to:

- trigger notifications on [real-time subscriptions]({{ site_base_path }}guide/essentials/real-time/)
- [perform data validation]({{ site_base_path }}guide/essentials/data-validation/) 

Koncorde exposes a DSL that enables you to define filters you can apply to any
stream of data and be notified whenever the content of the stream matches the filter.
This paradigm is called "percolation" and is the foundation of Kuzzle's real-time engine.

In other words, a percolation engine is the inverse of a search engine, where 
data is indexed and filters are used to retrieve data that matches them.

**This is different from document search [read more about how to search persistent data]({{ site_base_path }}guide/1/essentials/persisted/#document-search).**

A data percolation engine has the following properties:

* an arbitrary number of filters can be indexed
* whenever data is submitted to the engine, it returns the indexed filters matching it
* data is never stored in the engine

The DSL that Koncorde exposes is directly inspired by Elasticsearch, so that defining
real-time filters isn't much different than defining search querires.

If you are looking for information about the real-time subscriptions mechanism
in Kuzzle, please refer to [the specific docs in the Essentials section]({{ site_base_path }}guide/1/essentials/real-time/).

