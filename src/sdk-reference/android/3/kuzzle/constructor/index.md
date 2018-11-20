---
layout: sdk.html.hbs
language-tab:
  js: Javascript
  java: Android
  php: PHP
algolia: true
title: Kuzzle
description: Entry point and main class for the entire SDK
show-subheader: true
subheader-title: Constructor
order: 100
---


# Constructor
This is the main entry point to communicate with Kuzzle. Every other object inherits properties from the `Kuzzle` object.


## Options

| Option | Type | Description | Default |
|
## Properties

| Property name | Type | Description | Writable? |
|
## Callback response

If the connection succeeds, resolves to the `Kuzzle` object itself.
If the `connect` option is set to `manual`, the callback will be called after the `connect` method is resolved.

## Usage

[snippet=constructor-1]
