#!/usr/bin/env bash

pushd "$(dirname $0)/.." > /dev/null

mvn clean install -T 2C
docker-compose build
docker-compose up

popd > /dev/null

