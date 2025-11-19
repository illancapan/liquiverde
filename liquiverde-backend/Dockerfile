# -------------------
# Imagen base de build
# -------------------
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copiar archivos de Gradle
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Copiar código fuente
COPY src /app/src

# Construir sin tests
RUN ./gradlew clean build -x test --no-daemon

# -------------------
# Imagen final
# -------------------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiar jar compilado
COPY --from=build /app/build/libs/liquiverde-backend-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto
EXPOSE 8080

# Ejecutar aplicación
ENTRYPOINT ["java","-jar","app.jar"]
