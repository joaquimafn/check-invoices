# Use OpenJDK 17 como imagem base
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml e baixar as dependências
COPY pom.xml .
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline -B

# Copiar o código fonte
COPY src ./src

# Compilar a aplicação
RUN mvn clean package -DskipTests -Dcheckstyle.skip=true -Dspotless.skip=true -Dfmt.skip=true

# Expor a porta 8080
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "target/infuse-app-0.0.1-SNAPSHOT.jar"] 