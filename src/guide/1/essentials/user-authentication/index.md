---
layout: full.html.hbs
algolia: true
title: Users & Authentication
order: 750
---


# Users & Authentication

## Creating Users

Once we have created security [roles and profiles]({{ site_base_path }}guide/1/essentials/security), we can go on to create the users that will access the Kuzzle.

Users can be created by either using the [Kuzzle Admin Console]({{ site_base_path }}guide/1/essentials/installing-console), the [API]({{ site_base_path }}api/1/controller-security/create-user/), or the [SDK]({{ site_base_path }}sdk-reference/security/create-user/).

When creating a user, you will need to assign them one or more [profiles]({{ site_base_path }}guide/1/essentials/security/#defining-profiles).

Additionally, you can set:

* [User credentials]({{ site_base_path }}guide/1/essentials/user-authentication/#user-credentials): If no credentials are provided, then the user cannot [login]({{ site_base_path }}api/1/controller-auth/login/)
* Any number of properties that you want to store in your user object, such as a lastname or a list of hobbies. These properties are stored at the user level and are not linked to any particular authentication strategy.

Let's create a user with username `jondoe` and password `letmein` through the API:

```bash
curl -X POST -H "Content-Type: application/json" -d '{ "content": { "profileIds": ["default"], "name": "John Doe" }, "credentials": { "local": { "username": "jondoe", "password": "letmein" } } }' http://localhost:7512/users/_create
```

You should get the following response:

```json
{
  "requestId": "<random unique request id>",
  "status": 200,
  "error": null,
  "controller": "security",
  "action": "createUser",
  "collection": null,
  "index": null,
  "volatile": null,
  "result": {
    "_id": "<kuid>",
    "_source": {
      "profileIds": [
        "default"
      ],
      "name": "John Doe"
    },
    "_meta": {
      "author": "-1",
      "createdAt": 1516186256993,
      "updatedAt": null,
      "updater": null
    }
  }
}

```

## Kuzzle User Identifier (kuid)

When a user is created, Kuzzle will automatically generate a random unique identifier for that user. This id is referred to as a `kuid` and is used by the security layer to identify a unique user and link them to multiple external identifiers (email, phone number, etc.) for use with different authentication plugins.

This system allows a user to login to Kuzzle using different strategies and, potentially, different login identifiers, while still being considered as an unique entity by Kuzzle.

If you're interested for a more in-depth explanation on how all of this work, then please check our [Kuzzle In-Depth Documentation]({{ site_base_path }}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuid).


## User Credentials

In Kuzzle, a user's credentials are composed of a list of authentication strategies and their respective profile data.

For instance, if a user registered on Kuzzle with both facebook and twitter authentication strategies, then their credentials would look like this:

```json
{
  "facebook": {
    "kuid": "<Kuzzle Unique User Identifier>",
    "login": "<login name>",
    "email": "johndoe@foobar.qux"
  },
  "twitter": {
    "kuid": "<Kuzzle Unique User Identifier>",
    "login": "<login name>",
    "avatar_url": "http://..."
  }
}
```

Notice that the `kuid` is present in both the facebook property and the twitter property.

---
