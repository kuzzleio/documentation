FROM node:alpine

LABEL io.kuzzle.vendor="Kuzzle <support@kuzzle.io>"
LABEL description="Documentation website with running tests"

WORKDIR /var/app

COPY . /var/app

RUN set -eux \
  \
  && npm install
  && npm run dev

CMD ["tail", "-f", "/dev/null"]

