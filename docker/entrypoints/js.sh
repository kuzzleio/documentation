#!/bin/bash
set -e

npm install
npm i $(node -e "require('./getConfig').getSdk('js');")
node test/main.js -l js $@
