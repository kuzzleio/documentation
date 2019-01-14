---
layout: sdk.html.hbs
title: Constructor
description: Create a new Websocket object connected to Kuzzle server
order: 0
---

# Constructor

WebSocket is a class implementing the virtual class [Protocol]({{site_base_path}}sdk-reference/cpp/1/protocol).

This is the main entry point to communicate with Websocket protocol.
 Pass an instance of this class to the [constructor of the Kuzzle SDK]({{site_base_path}}sdk-reference/cpp/1/kuzzle/constructor/) to use this protocol to connect to the Kuzzle server.

## Signature

```cpp
WebSocket(const std::string& host,);

WebSocket(const std::string& host, kuzzleio::options& options);
```

## Arguments

| Argument  | Type        | Description                     |
| --------- | ----------- | ------------------------------- |
| `host`    | <pre>const std::string&</pre> | Kuzzle server hostname or IP |
| `options` | <pre>kuzzleio::options\*</pre>   | WebSocket protocol configuration |


### options

| Option               | Type<br/>(`default`)               | Description                                                        |
| -------------------- | ------------------ | ------------------------------------------------------------------ |
| `port`     | <pre>int</pre><br/>(`7512`)      | Kuzzle server port              |
| `ssl`    | <pre>bool</pre><br/>(`false`)      | Use SSL to connect to Kuzzle server              |


## Usage

[snippet=constructor]
