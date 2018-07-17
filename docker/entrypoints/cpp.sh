#!/bin/bash
#docker run --rm --privileged -it --mount type=bind,source="$(pwd)",target=/app documentation-v2:cpp
set -e

npm install
# npm i $(node -e "require('./getConfig').getSdk('js');")
node test/main.js -l cpp $@
