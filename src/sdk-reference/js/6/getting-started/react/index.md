---
layout: sdk.html.hbs
title: React
description: Getting started with Kuzzle and React
---

# Getting Started with Kuzzle and React

This section deals with **Kuzzle** (+ **Javascript SDK**) and **React** (with **Redux**). We will create some **documents** in Kuzzle and subscribe to **notifications** about document creations.

## Requirements

- **Node.js** >= 8.0.0 ([install here](https://nodejs.org/en/download/))
- **Create React App** ([install here](https://github.com/facebook/create-react-app))
- **Running Kuzzle Stack** ([instructions here]({{ site_base_path }}guide/1/getting-started/running-kuzzle/))

## Prepare your environment

Create your React app and install all the dependencies from the command line using yarn:

```bash
yarn create react-app kuzzle-playground
cd kuzzle-playground
yarn add kuzzle-sdk redux redux-saga react-redux
```

We have to create our Redux store architecture (more details on [Redux documentation](https://redux.js.org/introduction/getting-started)), like this:

```bash
src
└── state
    ├── actions.js
    ├── reducers.js
    └── sagas.js
```

_src/state/actions.js_ contains an empty object for the moment:

```javascript
export default {};
```

_src/state/reducers.js_ contains the initial state of our app (and later some reducers to update it):

```javascript
const initialState = {};

const reducersMap = {
  leaveStateUnchanged: state => state
};

export default function reducers(state = initialState, action) {
  const reducer = reducersMap[action.type] || reducersMap.leaveStateUnchanged;
  const newState = reducer(state, action.payload, action.meta);
  return newState;
}
```

_src/state/sagas.js_ contains a generator function where we will put our sagas function later (more details on [Redux-saga documentation](https://redux-saga.js.org/)):

```javascript
export default function*() {}
```

Now you can edit _src/index.js_ to link all these files to our app:

```javascript
import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';

import * as serviceWorker from './serviceWorker';
import App from './App';
import storeApp from './state/reducers';
import sagas from './state/sagas';

import './index.css';

const sagaMiddleware = createSagaMiddleware();
const store = createStore(storeApp, applyMiddleware(sagaMiddleware));

sagaMiddleware.run(sagas);

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
```

## Connect to Kuzzle

We have to connect the server so that our client can interact with it.

To do this, we have to create _src/services/kuzzle.js_ file to put our kuzzle instance, a bit like a singleton:

```javascript
import { Kuzzle, WebSocket } from 'kuzzle-sdk';

export default new Kuzzle(new WebSocket('localhost'));
```

You can now edit the _src/App.js_ file to connect to Kuzzle:

```javascript
import React, { Component } from 'react';
import { connect } from 'react-redux';

import ActionCreators from './state/actions';
import kuzzle from './services/kuzzle';

import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this._initialize();
  }

  async _initialize() {
    // handler to be notified in case of a connection error
    kuzzle.on('networkError', error => {
      console.error(error.message);
    });

    await kuzzle.connect();
  }

  render() {
    return <span>Hello, world!</span>;
  }
}

// connect to redux store
export default connect(
  state => ({}),
  {}
)(App);
```

At this step, if you run `yarn start` at the root of your app, then you should see 'Hello, world!' in your browser.

## Display futur messages

Boilerplate is over, let's code our app! It will consist of an input to write some text and save it in Kuzzle. The app will also display all the created messages. It looks like a chat.

First, let's edit some files to store the messages on the client and display them with React.

In _src/state/reducers.js_, we simply add a `messages` field to our initial state:

```javascript
const initialState = {
  messages: []
};

// ...
```

In _src/App.js_, we collect the messages from the state and display them in the render method. We also connect the input to the component state for later.

```javascript
class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      message: ''
    };
    this._initialize();
  }

  // ...

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };

  render() {
    const { messages } = this.props;
    const { message } = this.state;

    return (
      <div>
        <div>
          <input
            type="text"
            name="message"
            id="message"
            value={message}
            onChange={this.handleChange}
          />
          <button>Envoyer</button>
        </div>
        <div>
          {[...messages].reverse().map(message => (
            <p>{message.text}</p>
          ))}
        </div>
      </div>
    );
  }
}

export default connect(
  state => ({
    messages: state.messages
  }),
  {}
)(App);
```

At this step we can test our app by putting some random messages in our `initialState` in _src/state/reducers.js_, and then look in the browser if the messages are displayed.

```javascript
const initialState = {
  messages: [{ text: 'Hello, world!' }, { text: '42' }]
};

// ...
```

## Create our first message

When we click on the submit button of our input, we want to create a document in Kuzzle representing the message.

First, we have to assure that index and collection are already created in our Kuzzle backend. Just add these lines in the `_initialize` method of our `App` component, after the `kuzzle.connect()` call:

```javascript
async _initialize() {
  // ...
  [[snippet=init]]
}
```

Now we can create our Redux action `sendMessage` who take a string and create the document in Kuzzle.

Let's begin with our action function, in _src/state/actions.js_:

```javascript
const ActionCreators = {
  sendMessage: text => ({
    type: 'SEND_MESSAGE',
    payload: {
      text
    }
  })
};

export default ActionCreators;
```

And in _src/state/sagas.js_:

```javascript
import { takeEvery } from 'redux-saga/effects';
import kuzzle from '../services/kuzzle';

const sendMessage = function*({ payload: { text } }) {
  try {
    const document = {
      text
    };
    yield kuzzle.document.create('chat', 'messages', document);
  } catch (e) {
    console.error(e);
  }
};

export default function*() {
  yield takeEvery('SEND_MESSAGE', sendMessage);
}
```

In the `sendMessage` function, we just create a `document` object and send it to Kuzzle to store it in the `chat` index in `messages` collection.

Finally, just load the `sendMessage` action and call it in `App` component:

```javascript
class App extends Component {
  // ...

  sendMessage = event => {
    this.props.sendMessage(this.state.message);
    this.setState({
      message: ''
    });
  };

  render() {
    // ..
    return (
      <div>
        <div>
          {/* ... */}
          <button onClick={this.sendMessage}>Envoyer</button>
        </div>
        {/* ... */}
      </div>
    );
  }
}

export default connect(
  state => ({
    messages: state.messages
  }),
  {
    sendMessage: ActionCreators.sendMessage
  }
)(App);
```

You're thinking inevitably "Ok, it's cool but, I can't see the message on the interface?"... Don't worry.

## Load all messages

We have to load the messages in Kuzzle in app loading, so we need a Redux action to put an array of messages in the Redux state.

In _src/state/actions.js_, we add the `setMessages` action:

```javascript
const ActionCreators = {
  // ...
  setMessages: messages => ({
    type: 'SET_MESSAGES',
    payload: {
      messages
    }
  })
};
```

And we add the associate reducer in _src/state/reducers.js_:

```javascript
// ...
const reducersMap = {
  SET_MESSAGES: (state, payload) => ({
    messages: [...state.messages, ...payload.messages]
  }),
  leaveStateUnchanged: state => state
};
// ...
```

Finally we just use this action in `_initialize` in `App` component, after loading all messages from Kuzzle:

```javascript
class App extends Component {
  // ...

  async _initialize() {
    // ..
    [[snippet=search]]
    if (results.total > 0) {
      this.props.setMessages(results.hits.map(hit => hit._source));
    }
  }
  // ...
}

export default connect(
  state => ({
    messages: state.messages
  }),
  {
    sendMessage: ActionCreators.sendMessage,
    setMessages: ActionCreators.setMessages
  }
)(App);
```

Now if you load your app, you should see previously sent messages!
Of course, if we send a message it doesn't appear until we reload... Don't worry.

## Subscription to message creation

In order to see messages appearing directly in our app, we have to subscribe to realtime document notifications (pub/sub).

Kuzzle provides pub/sub features that can be used to trigger real-time notifications based on the state of your data (for a deep-dive on notifications check out the [realtime notifications]({{ site_base_path }}sdk-reference/js/6/realtime-notifications/) documentation).

So, for our app, we just have to subscript and call the previously created `setMessages` action to put message in the Redux state.
In _src/App.js_:

```javascript
class App extends Component {
  // ...
  async _initialize() {
    // ...
    this._subscribeToNewMessages();
  }

  _subscribeToNewMessages() {
    [[snippet=subscribe]]
  }
  // ...
}
// ...
```

It's done! Now you can see the messages appearing directly in our app. You're ready to make fabulous things with Kuzzle and React.

## Going further

Now that you're more familiar with Kuzzle with React, you can:

- discover what this SDK has to offer by browsing other sections of this documentation
- learn how to use [Koncorde]({{ site_base_path }}koncorde/1/essentials/introduction/) to create incredibly fine-grained and blazing-fast subscriptions
- learn how to perform a [basic authentication]({{ site_base_path }}sdk-reference/js/6/auth/login/)
- follow our guide to learn how to [manage users, and how to set up fine-grained access control]({{ site_base_path }}guide/1/essentials/security/)

To help you starting a new project with Kuzzle and React, you can start with the [**Kuzzle, React and Redux boilerplate**](https://github.com/kuzzleio/kuzzle-react-redux-boilerplate).
