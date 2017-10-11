#!/bin/bash
#Shell script to execute the application startup. It makes use of docker and the gradle wrapper included in the project

#Making sure there is no docker swarm currently running
#However it would be best practice to manually call docker-compose down when closing the current environment
echo "Removing old docker swarm"
docker-compose down
#Making sure the /build directory gets cleaned
echo "cleaning build directory"
./gradlew clean
#Rebuild the application, create a jar and transport the jar to build/docker
echo "executing build"
./gradlew build
#Build a new image based on the dockerfile in the project
#This step is essential, as otherwise docker-compose up used the image build of the first time it ran
echo "Building new container images"
docker-compose build
#Start up a container swarm, running in detached mode (In the background)
echo "running new container"
docker-compose up -d
