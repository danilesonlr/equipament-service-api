# base do OpenJDK
FROM openjdk:8

# diretório de trabalho
WORKDIR /equipament-service-api/target/

# Copie o arquivo JAR 
COPY . .

# porta 
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "equipament-service-api-0.0.1-SNAPSHOT.jar"]artifactId