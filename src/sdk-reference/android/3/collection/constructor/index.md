---
layout: sdk.html.hbs
algolia: true
title: constructor
description: Collection:constructor
order: 1
---

  

# Collection
In Kuzzle, you manipulate documents and subscriptions, both related to data collections.

A data collection is a set of data managed by Kuzzle. It acts like a data table for persistent documents, or like a room for pub/sub messages.


## Properties

| Property name | Type | Description | get/set |
|--------------|--------|-----------------------------------|---------|
| ``collection`` | string | The name of the data collection handled by this instance | get |
| ``index`` | object | Name of the index containing the data collection | get |
| ``headers`` | object | Headers for all sent documents. | get/set |
| ``kuzzle`` | object | linked Kuzzle instance | get |

**Note:** the ``headers`` property is inherited from the main ``Kuzzle`` object and can be overrided

## Usage

[snippet=constructor-1]
