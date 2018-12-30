---
layout: sdk.html.hbs
title: scroll
description: Collection:scroll
---
  

# scroll
Returns a [SearchResult]({{ site_base_path }}sdk-reference/search-result/) object containing the next page of the scroll session, and the `scrollId` to be used in the next `scroll` action.  
A scroll session is always initiated by a `search` action and including the `scroll` argument; more information below.

<div class="alert alert-info">
There is a small delay between the time a document is created and its availability in our search layer (usually a couple of seconds). That means that a document that was just created might not be returned by this function at first.
</div>

<div class="alert alert-info">
  To get more information about scroll sessions, please refer to the <a href="{{ site_base_path }}api/1/controller-document/search">API reference documentation</a>.
</div>

---

## scroll(scrollId, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``scrollId`` | string | The "scrollId" provided with the last scroll response or from the initial search request if it is the first scroll call |
| ``options`` | JSON object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |
| ``scroll`` | string | Re-initializes the scroll session timeout to its value. If not defined, the scroll timeout is defaulted to a Kuzzle configuration | ``undefined`` |
---

## Callback Response

Returns an instantiated [SearchResult]({{ site_base_path }}sdk-reference/search-result) object.

---
## Usage

[snippet=scroll-1]
