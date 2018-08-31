#!/bin/bash
#docker run --rm --privileged -it -v "$(pwd)":/app kuzzleio/documentation-v2:cpp
set -e

if [ -z "$SDK_VERSION" ]; then
  echo "SDK_VERSION not specified. Exiting"
  exit 1
fi

echo "Download C++ SDK"
rm -rf test/bin/sdk-cpp
mkdir -p test/bin/sdk-cpp
cd test/bin/sdk-cpp
curl -o kuzzlesdk-cpp.tar.gz https://dl.kuzzle.io/sdk/nightly/kuzzlesdk-cpp.tar.gz
tar xf kuzzlesdk-cpp.tar.gz
rm kuzzlesdk-cpp.tar.gz
cp include/kuzzlesdk.h include/kuzzle.h

cd /app
npm install
node test/main.js -l cpp $@
