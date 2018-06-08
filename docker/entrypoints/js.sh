#!/bin/bash

set -e

npm install
npm i -g eslint
node test/docTester.js -L js