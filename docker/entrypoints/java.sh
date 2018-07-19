set -e
#docker run --rm --privileged -it --mount type=bind,source="$(pwd)",target=/app java-tester

npm install --unsafe-perm
wget https://dl.kuzzle.io/sdk/nightly/kuzzlesdk-java.jar -O /app/test/bin/sdk-java.jar
node test/main.js -l java $@
