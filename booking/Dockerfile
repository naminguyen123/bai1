FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# copy toàn bộ source code
COPY . .

# build jar bằng maven
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# rename jar
RUN cp target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]