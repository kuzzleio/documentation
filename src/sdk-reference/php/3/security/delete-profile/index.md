---
layout: sdk.html.hbs
algolia: true
title: deleteProfile
description: Security:deleteProfile
---

  

# deleteProfile
Delete the provided profile.

<aside class="notice">
There is a small delay between the time a profile is deleted and it being reflected in the search layer (usually a couple of seconds).
That means that a profile that was just deleted may still be returned by the <code>searchProfiles</code> function at first.
</aside>


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `Security` object to allow chaining.

---

## Callback Response

Returns the ID of the security profile that has been deleted.

## Usage

[snippet=delete-profile-1]
> Callback response

```json
"deleted profile identifier"
```