#/bin/bash

sh .ci/start_kuzzle.sh

ERROR=0

function start_sdk_tests() {
    sdk=$1
    version=$2

  echo ""
  echo "#######################################################################"
  echo "                Test SDK $sdk $version snippets"
  echo "#######################################################################"

  bash run-snippet-tests.sh -n -p src/ -s $sdk -v $version

  if [[ ! $? -eq 0 ]]; then
    ERROR=1
  fi

  curl -s -o /dev/null -X POST localhost:7512/admin/_resetKuzzleData
  curl -s -o /dev/null -X POST localhost:7512/admin/_resetDatabase
}

start_sdk_tests js 5
start_sdk_tests js 6
start_sdk_tests go 1
start_sdk_tests cpp 1
start_sdk_tests java 1
start_sdk_tests csharp 1

exit $error
