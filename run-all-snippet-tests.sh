#/bin/bash

sh .ci/start_kuzzle.sh

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

  if [[ $error -eq 1 ]]; then
    touch .ci/failed/$(echo $sdk_path | cut -d/ -f3)-$(echo $sdk_path | cut -d/ -f4)
  elif [[ $error -eq 0 ]]; then
    touch .ci/success/$(echo $sdk_path | cut -d/ -f3)-$(echo $sdk_path | cut -d/ -f4)
  fi

  curl -s -o /dev/null -X POST localhost:7512/admin/_resetKuzzleData
  curl -s -o /dev/null -X POST localhost:7512/admin/_resetDatabase

done

exit $error
