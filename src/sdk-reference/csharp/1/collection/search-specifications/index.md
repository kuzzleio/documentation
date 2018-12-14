---
layout: sdk.html.hbs
title: searchSpecifications
description: Searches collection specifications
---

# searchSpecifications

{{{since "1.0.0"}}}

Searches collection specifications.

There is a limit to how many items can be returned by a single search query.
That limit is by default set at 10000, and you can't get over it even with the from and size pagination options.

<div class="alert alert-info">
  When processing a large number of items (i.e. more than 1000), it is advised to paginate the results using <code>SearchResult.next</code> rather than increasing the size parameter.
</div>

## Signature

```csharp
public SWIGTYPE_p_kuzzleio__SearchResult searchSpecifications(string body);
public SWIGTYPE_p_kuzzleio__SearchResult searchSpecifications(string body, QueryOptions options);
```

## Return

Returns a [Kuzzleio::SearchResult]({{ site_base_path }}src/sdk-reference/csharp/1/search-result).

## Return

Returns a [Kuzzleio::SearchResult]({{ site_base_path }}src/sdk-reference/csharp/1/search-result).

