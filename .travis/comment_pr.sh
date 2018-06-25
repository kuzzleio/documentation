#!/usr/bin/env bash
set -e

wget https://github.com/alexandrebouthinon/kuttlefish/releases/download/v1/kuttlefish
chmod +x kuttlefish
chmod 777 comment.html
./kuttlefish -f ./comment.html -id $TRAVIS_PULL_REQUEST -r $TRAVIS_REPO_SLUG -token $GH_TOKEN -t 0