---
code: true
type: page
title: delete
---

# delete

Deletes a previously uploaded file.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/_plugin/s3/files/<fileKey>
Method: DELETE
```

### Other protocols

```js
{
  "controller": "s3/file",
  "action": "delete",

  "fileKey": "xen/<uuid>-headcrab.png"
}
```

---

## Arguments

- `fileKey`: [file key](https://docs.aws.amazon.com/AmazonS3/latest/dev/UsingMetadata.html#object-keys) in S3 bucket

---

## Response

Returns a boolean indicating that the file is deleted from S3.

```js
{
  "status": 200,
  "error": null,
  "action": "delete",
  "controller": "s3/file",
  "requestId": "<unique request identifier>",
  "result": true
}
```

---

## Possible errors

- [Common errors](/core/1/api/essentials/errors/#common-errors)
- [NotFoundError](/core/1/api/essentials/errors/#specific-errors)
