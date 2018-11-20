---
layout: sdk.html.hbs
algolia: true
title: createProfile
description: Security:createProfile
---

  

# createProfile
Create a new profile in Kuzzle.

<aside class="notice">
There is a small delay between profile creation and its availability in our search layer (usually a couple of seconds).
That means that a profile that was just created might not be returned by the <code>searchProfiles</code> function at first.
</aside>


## Options

| Filter | Type | Description | Default |
|
## Callback Response

Returns a security [Profile]({{ site_base_path }}sdk-reference/profile) object.

## Usage

[snippet=create-profile-1]
