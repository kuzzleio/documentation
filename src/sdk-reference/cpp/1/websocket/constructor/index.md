---
layout: sdk.html.hbs
title: Constructor
description: Create a new Websocket object connected to the backend
order: 0
---

# Constructor

WebSocket is a class implementing the virtual class [Protocol]({{site_base_path}}sdk-reference/cpp/1/protocol).

This is the main entry point to communicate with Websocket protocol.
Give an instance of WebSocket to Kuzzle to connect to it.

## Signature

```cpp
WebSocket(const std::string& host, protocol, kuzzleio::options *options = nullptr)
```

## Arguments

| Argument  | Type        | Description                     |
| --------- | ----------- | ------------------------------- |
| `host`    | <pre>const std::string&</pre> | Kuzzle host you want to connect to |
| `options` | <pre>kuzzleio::options\*</pre>   | WebSocket connection configuration |


### **options**

| Option               | Type               | Description                                                        | Default  | 
| -------------------- | ------------------ | ------------------------------------------------------------------ | -------- |
| `port`     | int      | The connection port                 | `7512`
| `ssl`    | bool      | Use ssl to connect to the kuzzle instance              | `false`


## Usage

[snippet=constructor]
