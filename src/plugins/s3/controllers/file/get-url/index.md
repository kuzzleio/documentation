---
code: true
type: page
title: getUrl
---

# getUrl

Gets a public URL for an uploaded file.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/_plugin/s3/files/<fileKey>
Method: GET
```

### Other protocols

```js
{
  "controller": "s3/file",
  "action": "getUrl",

  "fileKey": "xen/<uuid>-headcrab.png"
}
```

---

## Arguments

- `fileKey`: [file key](https://docs.aws.amazon.com/AmazonS3/latest/dev/UsingMetadata.html#object-keys) in S3 bucket

---

## Response

Returns an object containing the file public URL.

```js
{
  "status": 200,
  "error": null,
  "action": "getUrl",
  "controller": "s3/file",
  "requestId": "<unique request identifier>",
  "result": {
    "fileUrl": "https://s3.eu-west-3.amazonaws.com/..." 
  }
}
```

---

## Possible errors

- [Common errors](/core/1/api/essentials/errors/#common-errors)
