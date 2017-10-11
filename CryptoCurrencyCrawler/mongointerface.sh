#!/bin/bash
#Open an interactive bash session with the mongo container
#To quit use the escape sequence ctrl+p+ctrl+q
#Open a connection to the mongo database running in the container
docker exec -it cryptocurrencycrawler_mongodb_1 mongo
