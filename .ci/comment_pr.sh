#!/usr/bin/env bash
set -e

cppv1=$(if [ -e .ci/failed/cpp-1 ]; then echo 1; elif [ -e .ci/success/cpp-1 ]; then echo 0; elif [ -e .ci/not_implemented/cpp-1 ]; then echo 3; fi;)
javav1=$(if [ -e .ci/failed/java-1 ]; then echo 1; elif [ -e .ci/success/java-1 ]; then echo 0; elif [ -e .ci/not_implemented/java-1 ]; then echo 3; fi;)
gov1=$(if [ -e .ci/failed/go-1 ]; then echo 1; elif [ -e .ci/success/go-1 ]; then echo 0; elif [ -e .ci/not_implemented/go-1 ]; then echo 3; fi;)
jsv6=$(if [ -e .ci/failed/js-6 ]; then echo 1; elif [ -e .ci/success/js-6 ]; then echo 0; elif [ -e .ci/not_implemented/js-6 ]; then echo 3; fi;)
jsv5=$(if [ -e .ci/failed/js-5 ]; then echo 1; elif [ -e .ci/success/js-5 ]; then echo 0; elif [ -e .ci/not_implemented/js-5 ]; then echo 3; fi;)
csharpv1=$(if [ -e .ci/failed/csharp-1 ]; then echo 1; elif [ -e .ci/success/csharp-1 ]; then echo 0; elif [ -e .ci/not_implemented/csharp-1 ]; then echo 3; fi;)

echo '{"prId": "'"$TRAVIS_PULL_REQUEST"'", "cppv1": "'"$cppv1"'", "javav1": "'"$javav1"'", "gov1": "'"$gov1"'", "jsv6": "'"$jsv6"'", "jsv5": "'"$jsv5"'", "csharpv1": "'"$csharpv1"'"}' > .ci/data.json
docker run --rm -it -v "$(pwd)":/mnt alexandrebouthinon/kuttlefish kuttlefish \
        -template /mnt/.ci/comment.html \
        -data /mnt/.ci/data.json \
        -repo documentation \
        -owner kuzzleio \
        -token $GH_TOKEN\
        -id $TRAVIS_PULL_REQUEST
