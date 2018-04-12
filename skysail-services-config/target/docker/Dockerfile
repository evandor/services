FROM openjdk:8-jre-alpine
MAINTAINER Carsten Gräf <evandor@gmail.com>
WORKDIR /opt/docker
ADD opt /opt
RUN ["chown", "-R", "daemon:daemon", "."]
USER daemon
ENTRYPOINT ["bin/skysail-services-config"]
CMD []
