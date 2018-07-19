#!/usr/bin/env bash

START_KUZZLE=1
INTERACTIVE=0

while getopts ":l:nf:i" opt; do
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
    f)
      if [ "$OPTARG" = "" ]; then
        echo "Option -$opt requires an argument" >&2
        exit 1
      fi
      TEST_ONLY="-f $OPTARG"
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

if [ $START_KUZZLE -eq 1 ]; then
  sh .travis/start_kuzzle.sh
fi

ENTRYPOINT="docker/entrypoints/$LANGUAGE.js "

case $LANGUAGE in
  js)
    docker run -a stdout -a stderr --rm --privileged --network codepipeline_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:js $TEST_ONLY
  ;;
  go)
    docker run -a stdout -a stderr --rm --privileged --network codepipeline_default --link kuzzle -v "$(pwd)":/app -v "$(pwd)"/test/bin:/go/src/github.com/kuzzleio/go-test kuzzleio/documentation-v2:go $TEST_ONLY
  ;;
  cpp)
    docker run -a stdout -a stderr --rm --privileged --network codepipeline_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:cpp $TEST_ONLY
  ;;
  java)
    docker run -a stdout -a stderr --rm --privileged --network codepipeline_default --link kuzzle -v "$(pwd)":/app kuzzleio/documentation-v2:java $TEST_ONLY
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

show_help() {
  echo "Possible options are"
  echo " -l <language>  [MANDATORY] specifies the language to test (valid languages are js and go)"
  echo " -n             Prevent to start the Kuzzle stack (useful if you keep it running yourself to run many tests)"
  echo " -f <test-file> Allows to run just one test by specifying the .yml test descriptor. Its path must be relative to src/sdk-reference"
  echo " -i             Launch a webserver after the tests to show a report"
}
