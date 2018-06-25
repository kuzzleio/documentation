#!/usr/bin/env bash
set -e

wget https://github.com/alexandrebouthinon/kuttlefish/releases/download/v1/kuttlefish
chmod +x kuttlefish
./kuttlefish -f ./comment.html -id $TRAVIS_PULL_REQUEST -r $TRAVIS_REPO_SLUG -token $GH_TOKEN -type 0