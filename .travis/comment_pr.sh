#!/usr/bin/env bash
set -e

cd .travis
wget https://github.com/alexandrebouthinon/kuttlefish/releases/download/v1/kuttlefish
ls -la
chmod +x ./kuttlefish
echo "1"
chmod +rw ./comment.html
echo "2"
./kuttlefish -f comment.html -id $TRAVIS_PULL_REQUEST -r $TRAVIS_REPO_SLUG -token $GH_TOKEN -t 0