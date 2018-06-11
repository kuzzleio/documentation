#!/bin/bash
#docker run --rm --privileged -it --mount type=bind,source="$(pwd)",target=/app --mount type=bind,source="$(pwd)"/test/bin,target=/go/src/github.com/kuzzleio/go-test go-tester
set -e

npm install
node test/docTester.js -L go
