#!/usr/bin/env bash
set -e

if [ "$TRAVIS_PULL_REQUEST" != "false" ] ; then

  COMMENT="<h2>CODE-EXAMPLE TESTS REPORT</h2><p>GO : https://docs-v2.kuzzle.io/reports/go/${TRAVIS_COMMIT}</p><p>JS : https://docs-v2.kuzzle.io/reports/js/${TRAVIS_COMMIT}</p>"

  curl -v -H "Authorization: token ${GITHUB_TOKEN}" -X POST \
  -d "{\"body\": \"$COMMENT\"}" \
  "https://api.github.com/repos/${TRAVIS_REPO_SLUG}/issues/${TRAVIS_PULL_REQUEST}/comments"
fi