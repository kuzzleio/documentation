---
layout: full.html.hbs
algolia: true
title: updateSpecifications
---


# updateSpecifications

{{{since "1.0.0"}}}

You can specify validation specifications in order to enforce your own rules over documents and real-time messages.
Whenever a document is stored or updated, or a message is published, Kuzzle applies these specifications to check if the new data complies to the defined rules. If not, the document or message will be rejected and the request will return an error message.

The updateSpecifications method allows you to create or update the validation specifications for one or more index/collection pairs.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.


## Body properties

The provided body must have the following structure:

```json
{
  "<data index>": {
    "<data collection>": {
      "strict": <boolean>,
      "fields": {
        // field validation rules
      }
    }
  }
}
```


## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/errors/#notfounderror)
