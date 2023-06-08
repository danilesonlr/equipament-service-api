# Use uma imagem base do OpenJDK
FROM openjdk:11

# Defina o diretório de trabalho
WORKDIR /equipament-service-api

# Copie o arquivo JAR da sua aplicação para o diretório de trabalho
COPY . .

# Exponha a porta na qual sua aplicação está ouvindo (se aplicável)
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "api-0.0.1.jar"]