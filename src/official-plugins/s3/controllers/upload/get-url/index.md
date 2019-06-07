---
code: true
type: page
title: getUrl
---

# getUrl

Returns a Presigned URL to upload directly to S3.  
The URL expires after a configurable TTL. (Configurable in the [kuzzlerc file](/official-plugins/s3/essentials/installation/#plugin-configuration))

File uploaded to the generated URL must be validated with [upload:validate](/official-plugins/s3/controllers/upload/validate) otherwise they will be deleted after the same TTL as for the URL expiration.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/_plugin/s3/upload
Method: GET
```

### Other protocols

```js
{
  "controller": "s3/upload",
  "action": "getUrl",

  "filename": "headcrab.png", 
  "uploadDir": "xen" 
}
```

---

## Arguments

- `filename`: Uploaded file name
- `uploadDir`: Upload directory (see [s3 file key](https://docs.aws.amazon.com/AmazonS3/latest/dev/UsingMetadata.html#object-keys))

---

## Response

Returns an object with the following properties:
 - `fileKey`: file key in S3 bucket
 - `uploadUrl`: presigned upload URL
 - `fileUrl`: public file URL after successful upload
 - `ttl`: TTL in ms for the URL validity and before the uploaded file deletion

```js
{
  "status": 200,
  "error": null,
  "controller": "s3/upload",
  "action": "getUrl",
  "requestId": "<unique request identifier>",
  "result": {
    "fileKey": "xen/<uuid>-headcrab.png", 
    "uploadUrl": "https://s3.eu-west-3.amazonaws.com/...", 
    "fileUrl": "https://s3.eu-west-3.amazonaws.com/...", 
    "ttl": 1200000 
  }
}
```

---

## Possible errors

- [Common errors](/core/1/api/essentials/errors/#common-errors)
