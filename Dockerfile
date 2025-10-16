FROM openjdk:17-jdk-slim
RUN echo "Africa/Harare" > /etc/timezone
ADD target/Api-Interceptor.jar app.jar
# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
