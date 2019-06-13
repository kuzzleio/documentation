---
code: true
type: page
title: Introduction
description: Kuzzle object
order: 0
---

# Kuzzle

The Kuzzle class is the main class of the SDK.
Once instantiated, it represents a connection to your Kuzzle server.

It gives access to the different features of the SDKs:

- access to the available controllers
- [SDK events](/sdk/cpp/1/essentials/events) handling
- activation of resilience to connection loss
- offline queue management

## Network protocol

Each instance of the class communicates with the Kuzzle server through a class representing a network protocol implementation.
Theses protocol classes must implement the [Protocol](/sdk/cpp/1/virtual-classes/protocol) class.

The following protocols are available in the SDK CPP 1:

- [WebSocket](/sdk/cpp/1/protocols/websocket)

## Volatile data

You can tell the Kuzzle SDK to attach a set of "volatile" data to each request. You can set it as an object contained in the `volatile` field of the Kuzzle constructor. The response to a request containing volatile data will contain the same data in its `volatile` field. This can be useful, for example, in real-time notifications for [user join/leave notifications](/core/1/api/essentials/volatile-data/) to provide additional informations about the client who sent the request.

Note that you can also set volatile data on a per-request basis (on requests that accept a `volatile` field in their `options` argument). In this case, per-request volatile data will be merged with the global `volatile` object set in the constructor. Per-request fields will override global ones.

## Public class definition

```cpp
namespace kuzzleio {
  class Kuzzle : public KuzzleEventEmitter {
    public:
      Kuzzle(Protocol* protocol);
      Kuzzle(Protocol* protocol, const options& options);
      virtual ~Kuzzle();

      void connect();
      void disconnect();
      void emitEvent(Event event, const std::string& payload);
      kuzzle_response* query(const kuzzle_request& request);
      kuzzle_response* query(const kuzzle_request& request, const query_options& options);

      // Offline queue
      Kuzzle* startQueuing();
      Kuzzle* stopQueuing();
      Kuzzle* playQueue();
      Kuzzle* flushQueue();

      // Setters
      Kuzzle* setAutoReplay(bool value);
      Kuzzle* setVolatile(const std::string& volatile_data);

      // Getters
      Protocol* getProtocol();
      std::string getJwt();
      std::string getVolatile();
      std::map<int, EventListener*> getListeners();

      // KuzzleEventEmitter implementation
      virtual int listenerCount(Event event) override;
      virtual KuzzleEventEmitter* addListener(Event event, EventListener* listener) override;
      virtual KuzzleEventEmitter* removeListener(Event event, EventListener* listener) override;
      virtual KuzzleEventEmitter* removeAllListeners(Event event) override;
      virtual KuzzleEventEmitter* once(Event event, EventListener* listener) override;
  };
}
```
