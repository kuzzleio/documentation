---
code: false
type: page
title: Installation
order: 100
---

# Installation

Clone the plugin repository in your `plugins/available` directory and then link it to the `plugins/enabled` directory.

```bash
git clone https://github.com/kuzzleio/kuzzle-plugin-s3 plugins/available/kuzzle-plugin-s3
ln -s ../available/kuzzle-plugin-s3 plugins/enabled/kuzzle-plugin-s3
```

Then go to your plugin directory and run the following command `npm install`.

You can now restart Kuzzle and check [http://localhost:7512](http://localhost:7512), you should see the plugin name under the key `serverInfo.kuzzle.plugins.s3`.

## Plugin configuration

You need to set your AWS access key in the environment: `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY`.  
Your access key must have the following rights: `PutObject` and `DeleteObject`.  

Then in your `kuzzlerc` file, you can change the following configuration variable:

  - `bucketName`: AWS S3 bucket
  - `region`: AWS S3 region
  - `signedUrlTTL`: TTL in ms before Presigned URL expire or the uploaded file is deleted
  - `redisPrefix`: Redis key prefix

```js
{
  "plugins": {
    "s3": {
      "bucketName": "your-s3-bucket",
      "region": "eu-west-3",
      "signedUrlTTL": 1200000,
      "redisPrefix": "s3Plugin/uploads"
    }
  }
}
```

## AWS S3 Bucket configuration

First you must configure your bucket to allow public access to uploaded files.  
Go to the `Permissions` tab in your bucket configuration and in `Bucket Policy` add the following statement:

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "AddPerm",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::your-bucket-name/*"
    }
  ]
}
```

Then you have to allow Cross Origin Request by editing the CORS Configuration:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<CORSConfiguration xmlns="http://s3.amazonaws.com/doc/2006-03-01/">
<CORSRule>
    <AllowedOrigin>your-app-domain.com</AllowedOrigin>
    <AllowedMethod>GET</AllowedMethod>
    <AllowedMethod>PUT</AllowedMethod>
    <AllowedMethod>POST</AllowedMethod>
    <MaxAgeSeconds>3000</MaxAgeSeconds>
    <AllowedHeader>Content-Type</AllowedHeader>
    <AllowedHeader>Authorization</AllowedHeader>
</CORSRule>
</CORSConfiguration>
```
