---
layout: sdk.html.hbs
algolia: true
title: deleteMyCredentials
description: Kuzzle:deleteMyCredentials
algolia: true
---
  

# deleteMyCredentials
Delete the current user's credentials for the specified `strategy`. 

---

## deleteMyCredentials(strategy, [options], [callback])

| Arguments | Type | Description
|-----------|------|------------
| `strategy` | string | Strategy you want to delete credentials from
| `options` | JSON object | Optional parameters
| `callback`| function | Optional Callback handling the response

---

## Options

| Option | Type | Description | Default
|--------|------|-------------|---------
| `queuable` | boolean | Make this request queuable or not  | `true`

---

## Callback Response

Returns an object with the query status.

## Usage

[snippet=delete-my-credentials-1]
