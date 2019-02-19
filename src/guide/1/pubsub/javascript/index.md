---
layout: full.html.hbs
title: Javascript
---

## Pub/Sub with Javascript

For this example we will use Node.js. You will need to install Node.js and NPM.

Let's create a new project folder called `realtimePubSub`:


```bash
    mkdir realtimePubSub
```

For this code example we'll need Kuzzle's Javascript SDK. To install it, run:

```bash
    npm install kuzzle-sdk@beta
```
We can create an `index.js` file in the `realtimePubSub` folder to program our test.

```bash
    touch index.js
```

## Connect to Kuzzle

The first thing we need to do is connect to Kuzzle. To do this write the following code:

[snippet=load-sdk]

Replace the `kuzzle` with the ip or name of the Kuzzle server, or localhost.

## Subscribe to Documents with Specific Criteria

Let's use the *subscribe* method.

We will perform a subscription request that tells Kuzzle that the App wants to be notified anytime a document is created that contains the *message* field. We define this subscription filter as follows, for more information about filters click [here]({{ site_base_path }}koncorde/1/):

[snippet=subscribe]

We have now programmed the subscription side of the test.

## Publish a Document

Now let's move on to the publish side of the test. Here we will publish a document that contains the `message` field. When Kuzzle receives this message, it will detect that there is a subscriber listening for such messages and will send it to these subscribers, in this case to our Android App.

We will use the *publish* method that creates a document that contains the value `hello world` in the `message` field.

[snippet=publish]
 
## Run the Test

The full code should look something like this:

[snippet=pubsubjs]

Your console should output the following message:

```bash
    hello world
```
