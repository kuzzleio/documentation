#!/usr/bin/env bash
set -e
../../kuttlefish -f ./comment.html -id $TRAVIS_PULL_REQUEST -r $TRAVIS_REPO_SLUG -token $GH_TOKEN -type 0