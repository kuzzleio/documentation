#!/bin/bash
#docker run --rm --net codepipeline_default --link kuzzle --privileged -it -v "$(pwd)":/app -v "$(pwd)"/test/bin:/go/src/github.com/kuzzleio/go-test go-tester
set -e

if [ -z "$SDK_VERSION" ]; then
  echo "SDK_VERSION not specified. Exiting"
  exit 1
fi

npm install --unsafe-perm

echo "Get SDK"
git clone $(node -e "require('./getConfig').getSdk('go');")
mv sdk-go /go/src/github.com/kuzzleio/sdk-go
cd /go/src/github.com/kuzzleio/sdk-go && go get ./...
cd -

echo "Run tests"
node test/main.js -l go $@
