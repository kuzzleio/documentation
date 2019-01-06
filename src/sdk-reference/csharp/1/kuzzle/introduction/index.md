---
layout: sdk.html.hbs
title: Introduction
description: Kuzzle object
order: 0
---

# Kuzzle

The Kuzzle class is the main class of the SDK.  
Once instantiated, it represents a connection to your Kuzzle server.

It gives access to the different functionalities of the SDKs:
 - access to the available controllers
 - [SDK events]({{ site_base_path }}sdk-reference/csharp/1/events) handling
 - activation of resilience to connection loss
 - offline queue management

