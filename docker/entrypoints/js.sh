#!/bin/bash
#docker run --rm --privileged -it --mount type=bind,source="$(pwd)",target=/app js-tester
set -e

npm install
npm i -g eslint eslint-plugin-import@latest eslint-plugin-node@latest eslint-plugin-promise@latest eslint-plugin-standard@latest
node test/docTester.js -L js