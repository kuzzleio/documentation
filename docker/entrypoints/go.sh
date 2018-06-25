#!/bin/bash
#docker run --rm --privileged -it --mount type=bind,source="$(pwd)",target=/app --mount type=bind,source="$(pwd)"/test/bin,target=/go/src/github.com/kuzzleio/go-test go-tester
set -e

npm install
go get golang.org/x/tools/cmd/goimports
git clone -b 1.x https://github.com/kuzzleio/sdk-go.git 
mv sdk-go /go/src/github.com/kuzzleio/sdk-go
cd /go/src/github.com/kuzzleio/sdk-go && go get ./...
cd /app
node test/docTester.js -L go