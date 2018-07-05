#!/bin/bash
#docker run --rm --net codepipeline_default --link kuzzle --privileged -it --mount type=bind,source="$(pwd)",target=/app --mount type=bind,source="$(pwd)"/test/bin,target=/go/src/github.com/kuzzleio/go-test go-tester
set -e

npm install
echo "Get SDK"
git clone $(node -e "require('./getConfig').getSdk('go');")
mv sdk-go /go/src/github.com/kuzzleio/sdk-go
cd /go/src/github.com/kuzzleio/sdk-go && go get ./...
cd /app
echo "Run tests"
node test/main.js -L go
