FROM maven:3-openjdk-8 as build
WORKDIR /app
COPY . /app
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:8
WORKDIR /app
COPY --from=build /app/target/gestion_etudiants-0.0.1-SNAPSHOT.war /app
EXPOSE 8080
CMD ["java", "-jar", "gestion_etudiants-0.0.1-SNAPSHOT.war"]
