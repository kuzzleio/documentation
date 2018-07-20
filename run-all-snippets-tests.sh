#/bin/bash

set -e

sh .travis/start_kuzzle.sh

for language in js go cpp java; do

  echo ""
  echo "#######################################################################"
  echo "                Test $language snippets"
  echo "#######################################################################"

  sh run_test.sh -l $language -n

  if [ ! -z "$TRAVIS" ]; then
    aws s3 cp reports/ s3://$AWS_S3_BUCKET/reports/$language/$TRAVIS_PULL_REQUEST/ --recursive --exclude "*.gitkeep"
  fi

  curl -X POST localhost:7512/admin/_resetKuzzleData

done
