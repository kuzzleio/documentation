#!/bin/bash

set -e

npm install --unsafe-perm

chmod 777 node_modules

./test/snippet-testing run $@
