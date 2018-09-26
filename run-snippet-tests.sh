#!/usr/bin/env bash

set -e

function extract_language() {
  path=$1
  previous_part=""
  parts=$(echo $path | tr "/" "\n")

  for part in $parts
  do
    if [[ $previous_part = "sdk-reference" ]]; then
      LANGUAGE=$part
    fi

    if [[ $LANGUAGE = $previous_part ]]; then
      SDK_VERSION=$part
    fi

    previous_part=$part
  done

  if [ -z $LANGUAGE ]; then
    echo "Can not find the sdk language in path '$path'"
    exit 1
  fi

  if [ -z $SDK_VERSION ]; then
    echo "Can not find the sdk version in path '$path'"
    exit 1
  fi
}

START_KUZZLE=1
INTERACTIVE=0

show_help() {
  echo "Possible options are"
  echo " -p <test-path> [MANDATORY] specifies the tests directory"
  echo " -n             Prevent to start the Kuzzle stack (useful if you keep it running yourself to run many tests)"
  echo " -i             Launch a webserver after the tests to show a report"
}

while getopts ":np:i" opt; do
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

if [ $START_KUZZLE -eq 1 ]; then
  sh .ci/start_kuzzle.sh
fi

extract_language $TESTS_PATH

echo "Detected SDK: $LANGUAGE version $SDK_VERSION"

case $LANGUAGE in
  js)
    docker run -it --name runner_js --rm -e DEV_MODE="$DEV_MODE" --network ci_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:js $TESTS_PATH
  ;;
  go)
    docker run -it --name runner_go --rm -e DEV_MODE="$DEV_MODE" --network ci_default --link kuzzle -v "$(pwd)":/app -v "$(pwd)"/test/bin:/go/src/github.com/kuzzleio/go-test kuzzleio/documentation-v2:go $TESTS_PATH
  ;;
  cpp)
    docker run -it --name runner_cpp --rm -e DEV_MODE="$DEV_MODE" --network ci_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:cpp $TESTS_PATH
  ;;
  java)
    docker run -it --name runner_java --rm -e DEV_MODE="$DEV_MODE" --network ci_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:java $TESTS_PATH
  ;;
  *)
    echo "$LANGUAGE is not a valid language"
    show_help
    exit 1
  ;;
esac

if [ ! -z "$TRAVIS" ]; then
  aws s3 cp reports/ s3://$AWS_S3_BUCKET/reports/$TRAVIS_PULL_REQUEST/$LANGUAGE/$SDK_VERSION/ --recursive --exclude "*.gitkeep"
fi

if [ $START_KUZZLE -eq 1 ]; then
  sh .ci/stop_kuzzle.sh
fi

if [ $INTERACTIVE -eq 1 ]; then
  node test/lib/helpers/reports.js
fi
