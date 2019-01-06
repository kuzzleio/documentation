---
layout: sdk.html.hbs
title: Introduction
description: Websocket protocol implementation
order: 0
---

# WebSocket

The WebSocket protocol can be used by an instance of the SDK to communicate with your Kuzzle server.  
This protocol allows you to use all the features of Kuzzle, including [real-time notifications]({{ site_base_path }}sdk-reference/csharp/1/realtime-notifications).

## Public class definition

```csharp
namespace kuzzleio {
  class WebSocket : public Protocol {
    public:
      WebSocket(string host);
      WebSocket(string host, options& query_options);

      std::list<EventListener*> getListeners(int);
      std::list<EventListener*> getOnceListeners(int);
      NotificationListener* getNotificationListener(string);

      // Protocol implementation
      virtual int getState();
      virtual int listenerCount(Event);
      virtual void addListener(Event, EventListener*);
      virtual void removeListener(Event, EventListener*);
      virtual void removeAllListeners(Event);
      virtual void once(Event, EventListener*);
      virtual void connect();
      virtual void close();
      virtual void emitEvent(Event);
      virtual void registerSub(string, string, string, bool, NotificationListener*);
      virtual void unregisterSub(string);
      virtual void cancelSubs();
      virtual void startQueuing();
      virtual void stopQueuing();
      virtual void playQueue();
      virtual void clearQueue();
      virtual string getHost();
      virtual kuzzle_response* send(string, query_options *, string);

      // Getters
      bool isAutoReconnect();
      bool isAutoResubscribe();
      int getPort();
      long getReconnectionDelay();
      bool isSslConnection();
  };
}
```
