FROM bellsoft/liberica-openjdk-alpine
WORKDIR opt
COPY spacetrans-0.0.1-SNAPSHOT.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 5432
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/opt/app.jar"]


