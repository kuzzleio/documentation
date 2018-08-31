#!/bin/bash
set -e

if [ -z "$SDK_VERSION" ]; then
  echo "SDK_VERSION not specified. Exiting"
  exit 1
fi

SDK_URL=https://github.com/kuzzleio/sdk-javascript

npm install --unsafe-perm
npm install $SDK_URL#$SDK_VERSION
node test/main.js -l js $@
