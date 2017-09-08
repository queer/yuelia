FROM openjdk:8-jre

ENTRYPOINT ["/usr/bin/java", "-Xmx1G", "-jar", "/usr/share/yuelia/yuelia.jar"]

ADD target/yuelia-*.jar /usr/share/yuelia/yuelia.jar
