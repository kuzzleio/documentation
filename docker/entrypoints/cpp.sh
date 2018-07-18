#!/bin/bash
#docker run --rm --privileged -it --mount type=bind,source="$(pwd)",target=/app kuzzleio/documentation-v2:cpp
set -e

echo "Download C++ SDK"
rm -rf test/bin/sdk-cpp
mkdir -p test/bin/sdk-cpp
cd test/bin/sdk-cpp
curl -o kuzzlesdk-cpp.tar.gz https://dl.kuzzle.io/sdk/nightly/kuzzlesdk-cpp.tar.gz
tar xf kuzzlesdk-cpp.tar.gz
cp include/kuzzlesdk.h include/kuzzle.h

cd /app
npm install
node test/main.js -l cpp $@
