---
layout: sdk.html.hbs
title: Introduction
description: Websocket protocol implementation
order: 0
---

# WebSocket

The WebSocket protocol can be used by an instance of the SDK to communicate with your Kuzzle server.  
This protocol allows you to use all the features of Kuzzle, including [real-time notifications]({{ site_base_path }}sdk-reference/cpp/1/realtime-notifications).

## Public class definition

```cpp
namespace kuzzleio {
  class WebSocket : public Protocol {
    public:
      WebSocket(const std::string& host);
      WebSocket(const std::string& host, const options& query_options);

      std::list<EventListener*> getListeners(int);
      std::list<EventListener*> getOnceListeners(int);
      NotificationListener* getNotificationListener(const std::string&);

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
      virtual void registerSub(const std::string&, const std::string&, const std::string&, bool, NotificationListener*);
      virtual void unregisterSub(const std::string&);
      virtual void cancelSubs();
      virtual void startQueuing();
      virtual void stopQueuing();
      virtual void playQueue();
      virtual void clearQueue();
      virtual std::string getHost();
      virtual kuzzle_response* send(const std::string&, query_options *, const std::string&);

      // Getters
      bool isAutoReconnect();
      bool isAutoResubscribe();
      unsigned int getPort();
      unsigned long long getReconnectionDelay();
      bool isSslConnection();
  };
}
```
