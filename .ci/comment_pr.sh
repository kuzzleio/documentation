#!/usr/bin/env bash
set -e

cppv1=$(if [ -e .ci/failed/cpp-1 ]; then echo 1; else echo 0; fi;)
javav1=$(if [ -e .ci/failed/java-1 ]; then echo 1; else echo 0; fi;)
gov1=$(if [ -e .ci/failed/go-1 ]; then echo 1; else echo 0; fi;)
jsv6=$(if [ -e .ci/failed/js-6 ]; then echo 1; else echo 0; fi;)
echo '{"prId": "'"$TRAVIS_PULL_REQUEST"'", "cppv1": "'"$cppv1"'", "javav1": "'"$javav1"'", "gov1": "'"$gov1"'", "jsv6": "'"$jsv6"'"}' > .ci/data.json
docker run --rm -it -v "$(pwd)":/mnt alexandrebouthinon/kuttlefish kuttlefish \
        -template /mnt/.ci/comment.html \
        -data /mnt/.ci/data.json \
        -repo documentation-v2 \
        -owner kuzzleio \
        -token $GH_TOKEN\
        -id $TRAVIS_PULL_REQUEST
