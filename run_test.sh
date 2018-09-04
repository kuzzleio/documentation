#!/usr/bin/env bash

set -e

START_KUZZLE=1
INTERACTIVE=0

show_help() {
  echo "Possible options are"
  echo " -l <language>  [MANDATORY] specifies the language to test (valid languages are js, go, cpp and java)"
  echo " -p <test-path> [MANDATORY] specifies the tests directory"
  echo " -n             Prevent to start the Kuzzle stack (useful if you keep it running yourself to run many tests)"
  echo " -i             Launch a webserver after the tests to show a report"
}

while getopts ":l:np:i" opt; do
  case $opt in
    l)
      if [ "$OPTARG" = "" ]; then
        echo "Option -$opt requires an argument" >&2
        exit 1
      fi
      LANGUAGE=$OPTARG
    ;;
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
  sh .travis/start_kuzzle.sh
fi

ENTRYPOINT="docker/entrypoints/$LANGUAGE.js "

case $LANGUAGE in
  js)
    docker run -t --name runner_js -a stdout -a stderr --rm --privileged --network codepipeline_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:js $TESTS_PATH
  ;;
  go)
    docker run -t --name runner_go -a stdout -a stderr --rm --privileged --network codepipeline_default --link kuzzle -v "$(pwd)":/app -v "$(pwd)"/test/bin:/go/src/github.com/kuzzleio/go-test kuzzleio/documentation-v2:go $TESTS_PATH
  ;;
  cpp)
    docker run -t --name runner_cpp -a stdout -a stderr --rm --privileged --network codepipeline_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:cpp $TESTS_PATH
  ;;
  java)
    docker run -t --name runner_java -a stdout -a stderr --rm --privileged --network codepipeline_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:java $TESTS_PATH
  ;;
  *)
    echo "$LANGUAGE is not a valid language"
    show_help
    exit 1
  ;;
esac


if [ $START_KUZZLE -eq 1 ]; then
  sh .travis/stop_kuzzle.sh
fi

if [ $INTERACTIVE -eq 1 ]; then
  node test/lib/helpers/reports.js
fi
