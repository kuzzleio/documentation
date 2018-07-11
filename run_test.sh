#!/usr/bin/env bash

while getopts ":l:" opt; do
  case $opt in
    l)
      if [ "$OPTARG" = "js" ]
      then
        sh .travis/start_kuzzle.sh
        docker run -a stdout -a stderr --rm --privileged --network codepipeline_default --link kuzzle --mount type=bind,source="$(pwd)",target=/app kuzzleio/documentation-v2:js
        docker rm codepipeline_redis_1 codepipeline_elasticsearch_1 kuzzle --force
        node test/lib/helpers/reports.js
      elif [ "$OPTARG" = "go" ]
      then
        sh .travis/start_kuzzle.sh
        docker run -a stdout -a stderr --rm --privileged --network codepipeline_default --link kuzzle --mount type=bind,source="$(pwd)",target=/app --mount type=bind,source="$(pwd)"/test/bin,target=/go/src/github.com/kuzzleio/go-test kuzzleio/documentation-v2:go
        docker rm codepipeline_redis_1 codepipeline_elasticsearch_1 kuzzle --force
        node test/lib/helpers/reports.js
      else
        echo "Invalid language"
        exit 1
      fi
      ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      exit 1
      ;;
    :)
      echo "Option -$OPTARG requires an argument." >&2
      exit 1
      ;;
  esac
done
