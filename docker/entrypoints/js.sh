#!/bin/bash
set -e

npm install --unsafe-perm
npm i $(node -e "require('./getConfig').getSdk('js');")
node test/main.js -l js $@
