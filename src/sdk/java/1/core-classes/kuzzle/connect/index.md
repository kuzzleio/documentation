---
code: true
type: page
title: connect
description: Connects the SDK to Kuzzle
---

# connect

Connects to Kuzzle using the `host` argument provided in the constructor options.  
Subsequent call have no effect if the SDK is already connected.

## Signature

```java
String connect()
```

## Return

A `String` with the error message or `null` if connection is made successfully.

## Usage

<<< ./snippets/connect.java
