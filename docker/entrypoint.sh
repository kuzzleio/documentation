#!/bin/bash

set -e

if [ -z "$DEV_MODE" ]; then
  npm install --unsafe-perm
fi

chmod 777 node_modules

/app/test/snippet-testing run $@

chmod 777 /app/test/bin/*
