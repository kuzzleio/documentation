[![Build Status](https://api.travis-ci.org/kuzzleio/sdk-android.svg?branch=master)](https://travis-ci.org/kuzzleio/sdk-android) [![codecov.io](http://codecov.io/github/kuzzleio/sdk-android/coverage.svg?branch=master)](http://codecov.io/github/kuzzleio/sdk-android?branch=master)
[ ![Download](https://api.bintray.com/packages/kuzzle/maven/kuzzle-sdk-android/images/download.svg) ](https://bintray.com/kblondel/maven/kuzzle-sdk-android/_latestVersion)
[![Join the chat at https://gitter.im/kuzzleio/kuzzle](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/kuzzleio/kuzzle?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Official Kuzzle Android SDK
======

## About Kuzzle

A backend software, self-hostable and ready to use to power modern apps.

You can access the Kuzzle repository on [Github](https://github.com/kuzzleio/kuzzle)

## SDK Documentation

The complete SDK documentation is available [here](http://docs.kuzzle.io/sdk)

## Get trained by the creators of Kuzzle :zap:

Train yourself and your teams to use Kuzzle to maximize its potential and accelerate the development of your projects.  
Our teams will be able to meet your needs in terms of expertise and multi-technology support for IoT, mobile/web, backend/frontend, devops.  
:point_right: [Get a quote](https://hubs.ly/H0jkfJ_0)

## Installation

You can configure your Android project to get Kuzzle's Android SDK from jcenter in your build.gradle:

    buildscript {
        repositories {
            maven {
                url  "http://dl.bintray.com/kuzzle/maven"
            }
            jcenter()
        }

    }

    dependencies {
        compile 'io.kuzzle:sdk-android:3.0.9'
        implementation 'tech.gusavila92:java-android-websocket-client:1.2.2'
    }

## Basic usage

```java
Kuzzle kuzzle = new Kuzzle("host", new ResponseListener<Void>() {
@Override
public void onSuccess(Void object) {
    // Handle success
    KuzzleDocument doc = new KuzzleDocument(dataCollection);
    doc.setContent("foo", "bar").save();
}

@Override
public void onError(JSONObject error) {
    // Handle error
}
});
```

## SSL Connection

```java
Options options = new Options();
options.setSsl(true);
Kuzzle kuzzle = new Kuzzle("host", options, new ResponseListener<Void>() {
@Override
public void onSuccess(Void object) {
    // Handle success
    KuzzleDocument doc = new KuzzleDocument(dataCollection);
    doc.setContent("foo", "bar").save();
}

@Override
public void onError(JSONObject error) {
    // Handle error
}
});
```

## KuzzleDocument

KuzzleDocument is an encapsulation of a JSONObject.

```java
KuzzleDataCollection myCollection = new KuzzleDataCollection(kuzzle, "myNewCollection");
KuzzleDocument myDocument = new KuzzleDocument(myCollection);
// Add properties to the body
myDocument.setContent("foo", "bar");
// Persist the document
myDocument.save();
// Send it on real time (not persistent)
myDocument.publish();
```

## Adding metadata

As stated [here](http://kuzzle.io/api-reference/#sending-metadata) you can add metadata to a subscription.

```java
KuzzleOptions options = new KuzzleOptions();
JSONObject metadata = new JSONObject();
metadata.put("foo", "bar");
options.setMetadata(metadata);
myCollection.subscribe(options);
```

# Login

## Prerequisite

To login using kuzzle you need at least one authentication plugin. You can refer [here](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-local) for a local authentication plugin
or [here](https://github.com/kuzzleio/kuzzle-plugin-auth-passport-oauth) to refer to our OAuth2 plugin.

To know more about how to log in with a Kuzzle SDK, please refer to our [documentation](http://docs.kuzzle.io/sdk/android/3/core-classes/kuzzle/login/)

If you have the kuzzle-plugin-auth-passport-local installed you can login using either the Kuzzle's constructor or the login method.

### Login with an OAuth strategy

If you have an OAUTH plugin like kuzzle-plugin-auth-passport-oauth, you may use the KuzzleWebViewClient class to handle the second authentication phase:

```java
Handler handler = new Handler();
WebView webView = (WebView) findViewById(R.id.webView);
webView.setWebViewClient(kuzzle.getKuzzleWebViewClient());
kuzzle.login("github", new KuzzleResponseListener<JSONObject>() {
      @Override
      public void onSuccess(final JSONObject object) {
        handler.post(new Runnable() {
          @Override
          public void run() {
            try {
              if (object.has("headers")) {
                webView.loadUrl(object.getJSONObject("headers").getString("Location"));
              }
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
        });
      }

      @Override
      public void onError(JSONObject error) {
        Log.e("error", error.toString());
      }
    });
```

## License

[Apache 2](LICENSE)
