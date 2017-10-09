FROM java:8

MAINTAINER Lennard Eijsackers

WORKDIR /project

ADD . /project

ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,address=8000,suspend=n,server=y"

EXPOSE 8000

CMD java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -jar /project/build/docker/cryptocurrency-backend.jar