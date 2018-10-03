---
layout: full.html.hbs
algolia: true
title: AWS Marketplace
description: AWS Marketplace
order: 1200
---

# AWS Marketplace

<div class="alert alert-info">
To follow this tutorial, you need a __valid AWS account__.
</div>

_In this guide, you'll learn where to find our AWS Marketplace AMI and how to use it. It's a good way to test Kuzzle in a cloud environment. In addition, we recommend that you use our [Kuzzle Admin Console](http://console.kuzzle.io), the most easiest way to play with Kuzzle._

## Get the AMI

Our AMI is stored on AWS Marketplace. It's set up with:

- Ubuntu (**16.04**)
- Kuzzle (**latest**) with MQTT protocol support.
- Elasticsearch (**v5.4.1**).
- Redis (**v3.2.12**).

Go to the marketplace and type **kuzzle** in the search form.
Choose your Amazon EC2 instance type (the minimal requirement is a **t2-medium**).
Recover the public IP or the hostname provided by AWS before you proceed.
Check that Kuzzle is up and running by simply performing the following HTTP request::

```sh
$ curl http://yourInstanceIpOrHostname:7512/_serverInfo\?pretty
{
  "requestId": "9b07a095-7143-49a5-9079-a34e937fdf3e",
  "status": 200,
  "error": null,
  "controller": "server",
  "action": "info",
  "collection": null,
  "index": null,
  "volatile": null,
  "result": {
    # Exhaustive Kuzzle information
  }
}
```

You should see some information about your Kuzzle Server.
If not, wait a few minutes and retry the request.

## Connect with default credentials

Open the [Kuzzle Admin Console](http://console.kuzzle.io) and fill the form with the address of your Kuzzle instance. There is a default admin user with **ec2-user** as username.
Associated password is your unique instance ID. You can get it from the EC2 AWS Console, it looks like this **i-xxxxxxxxxxxxxxxxx**.

![Demo Admin Console First Connection](/assets/images/gifs/demo_aws_console.gif)

## Where do we go from here?

Now that your Kuzzle instance is up and running, dive even deeper to learn how to leverage its full capabilities:

- take a look at the [SDK Reference](/sdk-reference)
- learn how to use [Koncorde](/kuzzle-dsl/essential/koncorde) to create incredibly fine-grained and blazing-fast subscriptions
- follow our guide to learn how to [implement basic authentication](/guide/essentials/user-authentication/#local-strategy).
- follow our guide to learn how to [implement manage users and setup fine-grained access control](/guide/essentials/security).
