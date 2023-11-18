FROM openjdk:11
EXPOSE 8089

Copy /target/gestion-station-ski-1.0.jar gestion-station-ski-1.0.jar

ENTRYPOINT ["java", "-jar","gestion-station-ski-1.0.jar"]