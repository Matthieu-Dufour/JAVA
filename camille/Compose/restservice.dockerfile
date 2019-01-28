FROM openjdk:8
VOLUME /tmp
ADD target/intervenant-projet-0.0.1-SNAPSHOT.jar intervenant-projet.jar
RUN bash -c 'touch /restservice.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/intervenant-projet.jar"]