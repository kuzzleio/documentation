#/bin/bash

sh .travis/start_kuzzle.sh

# List all sdk path
sdk_paths=$(find src/sdk-reference -maxdepth 2 -type d | grep 'src/sdk-reference/[a-z]\{1,\}/[0-9]')

error=0
for sdk_path in $sdk_paths; do

  echo ""
  echo "#######################################################################"
  echo "                Test $sdk_path snippets"
  echo "#######################################################################"

  bash run-snippet-tests.sh -n -p $sdk_path

  if [[ ! $? -eq 0 ]]; then
    error=1
  fi

  curl -X POST localhost:7512/admin/_resetKuzzleData
  curl -X POST localhost:7512/admin/_resetDatabase

done

exit $error
