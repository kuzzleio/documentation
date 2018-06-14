#!/bin/bash
#docker run --rm --privileged -it --mount type=bind,source="$(pwd)",target=/app js-tester
set -e

npm install
npm i -g eslint

node test/docTester.js -L js