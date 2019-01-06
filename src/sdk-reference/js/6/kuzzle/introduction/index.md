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
 - [SDK events]({{ site_base_path }}sdk-reference/cpp/1/events) handling
 - activation of resilience to connection loss
 - offline queue management

## Network protocol

Each instance of the class communicates with the Kuzzle server through a class representing a network protocol implementation.  

The following protocols are available in the SDK JS 6:
  - [WebSocket]({{ site_base_path }}sdk-reference/js/6/websocket)
  - [Http]({{ site_base_path }}sdk-reference/js/6/http)
  - [SocketIO]({{ site_base_path }}sdk-reference/js/6/socketio)

## Volatile data

Volatile data are additional data that will be transmitted to Kuzzle in each request.  

This data is used in real-time notifications for [user join/leave notifications]({{site_base_path}}api/1/essentials/volatile-data/) to provide additional informations about the client who sent the request.

If additional data is provided within a specific request, the volatile data will be merged with the request specific data taking priority over the global ones.
