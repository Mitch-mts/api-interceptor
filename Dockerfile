FROM openjdk:16-alpine3.13
RUN echo "Africa/Harare" > /etc/timezone
ADD target/Interceptor.jar app.jar
# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
