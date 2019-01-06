---
layout: sdk.html.hbs
title: Getters
description: Getters for UserRight class
order: 100
---

# UserRight class getters

## controller

Returns the controller name on which the rights apply.  
Can be a wildcard (`*`).

## Signature

```csharp
public string controller();

public void controller(string arg0);

```

## action

Returns the action name on which the rights apply.  
Can be a wildcard (`*`).

## Signature

```csharp
public string action();

public void action(string arg0);

```

## index

Returns the index name on which the rights apply.   
Can be a wildcard (`*`).

## Signature

```csharp
public string index();

public void index(string arg0);

```

## collection

Returns the collection name on which the rights apply.   
Can be a wildcard (`*`).

## Signature

```csharp
public string collection();

public void collection(string arg0);

```

## value

Returns the value name on which the rights apply.  
Can be one of the following: `allowed`, `denied` or `conditional`

## Signature

```csharp
public string value();

public void value(string arg0);

```

## Usage

[snippet=getters]
