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
Theses protocol classes must implement the [Protocol]({{ site_base_path }}sdk-reference/cpp/1/protocol) class.

The following table summarizes the differences between the available protocols:

| Feature | [WebSocket]({{ site_base_path }}sdk-reference/cpp/1/websocket) | Http | 
| -------- | :--------: | :--------: | 
| Realtime notifications | yes | *wip* |
| Connection loss resilience | yes | *wip* |

## Volatile data

Volatile data are data in JSON format that will be transmitted to Kuzzle in each request.  

This data is used in real-time notifications for [user join/leave notifications]({{site_base_path}}api/1/essentials/volatile-data/) to provide additional informations about the client who sent the request.

If additional data is provided within a specific request, the volatile data will be merged with the request specific data taking priority over the global ones.

## Public class definition

```cpp
namespace kuzzleio {
  class Kuzzle : public KuzzleEventEmitter {
    public:
      Kuzzle(Protocol*, options *options=nullptr);
      virtual ~Kuzzle();

      void connect();
      void disconnect();
      void emitEvent(Event, const std::string&);
      kuzzle_response* query(kuzzle_request*, query_options* options=nullptr);

      // Offline queue
      Kuzzle* startQueuing();
      Kuzzle* stopQueuing();
      Kuzzle* playQueue();
      Kuzzle* flushQueue();

      // Setters
      Kuzzle* setAutoReplay(bool);
      Kuzzle* setVolatile(const std::string&);

      // Getters
      Protocol* getProtocol();
      std::string getJwt();
      std::string getVolatile();
      std::map<int, EventListener*> getListeners();

      // KuzzleEventEmitter implementation
      virtual int listenerCount(Event) override;
      virtual KuzzleEventEmitter* addListener(Event, EventListener*) override;
      virtual KuzzleEventEmitter* removeListener(Event, EventListener*) override;
      virtual KuzzleEventEmitter* removeAllListeners(Event) override;
      virtual KuzzleEventEmitter* once(Event, EventListener*) override;

  };
}
```
