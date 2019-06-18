---
code: true
type: page
title: validate
---

# validate

Validate and persist a previsously uploaded file.  

::: warning
Without a call to the action, every file uploaded on a Presigned URL will be deleted after a TTL.
:::

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/_plugin/s3/upload/<fileKey>
Method: PUT
```

### Other protocols

```js
{
  "controller": "s3/upload",
  "action": "validate",

  "fileKey": "xen/<uuid>-headcrab.png" }
```

---

## Arguments

- `fileKey`: [file key](https://docs.aws.amazon.com/AmazonS3/latest/dev/UsingMetadata.html#object-keys) in S3 bucket

---

## Response

Returns an object with the following properties:
 - `fileKey`: file key in S3 bucket
 - `fileUrl`: public file URL after successful upload

```js
{
  "status": 200,
  "error": null,
  "controller": "s3/upload",
  "action": "validate",
  "requestId": "<unique request identifier>",
  "result": {
    "fileKey": "xen/<uuid>-headcrab.png", 
    "fileUrl": "https://s3.eu-west-3.amazonaws.com/..."
  }
}
```

---

## Possible errors

- [Common errors](/core/1/api/essentials/errors/#common-errors)
