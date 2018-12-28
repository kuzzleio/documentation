#!/usr/bin/env bash

set -e

START_KUZZLE=1
INTERACTIVE=0

show_help() {
  echo "Possible options are"
  echo " -s <sdk>       [MANDATORY] specifies the sdk to use"
  echo " -v <version>   [MANDATORY] specifies the sdk version"
  echo " -p <test-path> [MANDATORY] specifies the tests directory"
  echo " -n             Prevent to start the Kuzzle stack (useful if you keep it running yourself to run many tests)"
  echo " -i             Launch a webserver after the tests to show a report"
}

while getopts ":np:is:v:" opt; do
  case $opt in
    n)
      START_KUZZLE=0
    ;;
    p)
      if [ "$OPTARG" = "" ]; then
        echo "Option -$opt requires an argument" >&2
        exit 1
      fi
      TESTS_PATH=$OPTARG
    ;;
    i)
      INTERACTIVE=1
    ;;
    s)
      SDK=$OPTARG
    ;;
    v)
      SDK_VERSION=$OPTARG
    ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      show_help
      exit 1
    ;;
  esac
done

if [ -z $TESTS_PATH ]; then
  echo "You must specify the tests path with -p"
  exit 1
fi

if [ -z $SDK ]; then
  echo "You must specify the sdk with -s"
  exit 1
fi

if [ -z $SDK_VERSION ]; then
  echo "You must specify the sdk with -v"
  exit 1
fi

if [ $START_KUZZLE -eq 1 ]; then
  sh .ci/start_kuzzle.sh
fi
echo "SDK: $SDK version $SDK_VERSION"

case $SDK in
  js)
    docker run -it --name runner_js --rm -e DEV_MODE="$DEV_MODE" -e SDK_VERSION="$SDK_VERSION" -e LANGUAGE="$SDK" --network ci_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:js $SDK $SDK_VERSION $TESTS_PATH
  ;;
  go)
    docker run -it --name runner_go --rm -e DEV_MODE="$DEV_MODE" -e SDK_VERSION="$SDK_VERSION" -e LANGUAGE="$SDK" --network ci_default --link kuzzle -v "$(pwd)":/app -v "$(pwd)"/test/bin:/go/src/github.com/kuzzleio/go-test kuzzleio/documentation-v2:go $SDK $SDK_VERSION $TESTS_PATH
  ;;
  cpp)
    docker run -it --name runner_cpp --rm -e DEV_MODE="$DEV_MODE" -e SDK_VERSION="$SDK_VERSION" -e LANGUAGE="$SDK" --network ci_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:cpp $SDK $SDK_VERSION $TESTS_PATH
  ;;
  java)
    docker run -it --name runner_java --rm -e DEV_MODE="$DEV_MODE" -e SDK_VERSION="$SDK_VERSION" -e LANGUAGE="$SDK" --network ci_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:java $SDK $SDK_VERSION $TESTS_PATH
  ;;
  csharp)
    docker run -it --name runner_csharp --rm -e DEV_MODE="$DEV_MODE" -e SDK_VERSION="$SDK_VERSION" -e LANGUAGE="$SDK" --network ci_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:csharp $SDK $SDK_VERSION $TESTS_PATH
  ;;
  *)
    echo "$SDK is not a valid sdk"
    show_help
    exit 1
  ;;
esac

if [ $START_KUZZLE -eq 1 ]; then
  sh .ci/stop_kuzzle.sh
fi

if [ $INTERACTIVE -eq 1 ]; then
  node test/lib/helpers/reports.js
fi
