# Image Java légère
FROM eclipse-temurin:21-jdk


# Metadata
LABEL authors="ibrah"
LABEL description="Application Spring Boot DevOps MPISI"
LABEL version="1.0"

# Répertoire de travail
WORKDIR /app

# Copier le jar construit par Maven
COPY target/*.jar app.jar

# Exposer le port de Spring Boot
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]

