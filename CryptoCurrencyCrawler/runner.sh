#!/bin/bash
echo "Removing old docker swarm"
DOWN="docker-compose down"
$DOWN
echo "cleaning build directory"
CLEAN="./gradlew clean install"
$CLEAN
echo "executing build"
BUILD="./gradlew build"
$BUILD
echo "Building new container images"
IMAGE="docker-compose build"
$IMAGE
echo "running new container"
UP="docker-compose up"
$UP