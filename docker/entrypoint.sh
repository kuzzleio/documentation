#!/bin/bash

set -e

npm install --unsafe-perm

chmod 777 node_modules

/app/test/snippet-testing run $@

chmod 777 /app/test/bin/*
