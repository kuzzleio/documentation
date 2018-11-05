---
layout: sdk.html.hbs
algolia: true
title: <%= _.camelCase(action) %>
description:
order: 200
---

# <%= _.camelCase(action) %>

## Signature

```java
void <%= _.camelCase(action) %>()
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``changeme`` | changme | changeme    | yes

### **arg1**

### **arg2**

## Return

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=<%= _.kebabCase(action) %>]
