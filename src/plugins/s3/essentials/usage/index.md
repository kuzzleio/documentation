---
code: false
type: page
order: 200
title: Usage
---

# Usage

The plugin allows user to request an URL to directly send a file to Amazon S3.  

Once the file has been uploaded, it has to be validated otherwise the uploaded file is automaticcaly removed after a configurable TTL.  

This behavior prevent users from filling the S3 bucket with useless files.  

## Get a Presigned URL

```json
// Kuzzle request
{
  "controller": "s3/upload",
  "action": "getUrl",
  "filename": "headcrab.png",
  "uploadDir": "xen"
}

// Kuzzle response
{
  "fileKey": "xen/<uuid>-headcrab.png",
  "uploadUrl": "https://s3.eu-west-3.amazonaws.com/...",
  "fileUrl": "https://s3.eu-west-3.amazonaws.com/...",
  "ttl": 1200000
}
```

## Upload the file to the URL

Send a PUT request to the `uploadUrl` URL with the body set to the file's content and a `Content-Type` header corresponding to the file mime type.  

## Validate the file

Finally, validate the uploaded file. If not validated in a timely manner (the TTL is configurable), the uploaded file is automatically removed.

```json
// Kuzzle request
{
  "controller": "s3/upload",
  "action": "validate",
  "fileKey": "xen/<uuid>-headcrab.png"
}
```

## Using the SDK Javascript and axios

You can use the [SDK Javascript](/sdk/js/6) to interact with the s3 plugin and [axios](https://github.com/axios/axios) to send the file to S3.

```js
  // Get a Presigned URL
  const file = document.getElementById('uploadInput').files[0];
  const { result } = await kuzzle.query({
    controller: 's3/upload',
    action: 'getUrl',
    uploadDir: 'xen',
    filename: file.name
  });

  // Upload the file directly to S3
  const axiosOptions = {
    headers: {
      'Content-Type': file.type
    }
  };
  await axios.put(result.uploadUrl, file, axiosOptions);

  // Validate the uploaded file
  await kuzzle.query({
    controller: 's3/upload',
    action: 'validate',
    fileKey: result.fileKey
  });
```

## Full web example

You can see a full example here: [s3-upload-test.html](https://github.com/kuzzleio/kuzzle-plugin-s3/blob/master/test/s3-upload-test.html)