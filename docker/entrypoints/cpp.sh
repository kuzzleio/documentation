#!/bin/bash
#docker run --rm --privileged -it --mount type=bind,source="$(pwd)",target=/app cpp-tester
set -e

npm install
# npm i $(node -e "require('./getConfig').getSdk('js');")
node test/main.js -L cpp