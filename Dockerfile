FROM openjdk:8-jre-alpine

ENTRYPOINT ["/usr/bin/java", "-Xmx1G", "-jar", "/usr/share/yuelia/yuelia.jar"]

COPY target/yuelia-*.jar /usr/share/yuelia/yuelia.jar
