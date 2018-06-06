FROM anapsix/alpine-java:8_server-jre_unlimited

VOLUME /tmp

ADD build/libs/storeroom-rest-api.jar app.jar

RUN sh -c 'touch /app.jar'

ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"

EXPOSE 8000 8787

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
