---
layout: sdk.html.hbs
title: Getters
description: Getters for UserRight class
---

# UserRight class getters

## controller

Returns the controller name on which the rights apply. 
Can be a wildcard (`*`).

### Signature

```cpp
const std::string& controller() const
```

## action

Returns the action name on which the rights apply.
Can be a wildcard (`*`).

### Signature

```cpp
const std::string& action() const
```

## index

Returns the index name on which the rights apply.
Can be a wildcard (`*`).

### Signature

```cpp
const std::string& index() const
```

## collection

Returns the collection name on which the rights apply.
Can be a wildcard (`*`).

### Signature

```cpp
const std::string& collection() const
```

## value

Returns the value name on which the rights apply.  
Can be one of the following: `allowed`, `denied` or `conditional`

### Signature

```cpp
const std::string& value() const
```
