#!/usr/bin/env bash

if [ "$1" == "" ]; then
    echo "Usage: buildDockerImage.sh <name of jarfile to run>"
    exit -1
fi

cat src/main/docker/Dockerfile | sed "s/JARFILENAME/$1/" > target/Dockerfile

(cd target &&
  docker build -t fulfillment . &&
  docker tag fulfillment jvermeir/shop-fulfillment:v1
  docker push jvermeir/shop-fulfillment:v1
)
