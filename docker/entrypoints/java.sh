set -e
#docker run --rm --privileged -it --mount type=bind,source="$(pwd)",target=/app java-tester

npm install
wget https://s3-us-west-2.amazonaws.com/dl.kuzzle.io/sdk/kuzzlesdk-1.0.0.jar -O /app/test/bin/sdk-java.jar
node test/main.js -L java
