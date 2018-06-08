#!/bin/bash

set -e

npm install
mkdir -p /go/src/github.com/kuzzleio/go-tests
node test/docTester.js -L go
