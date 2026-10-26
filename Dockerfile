# ---------------------------------------------------------------
# Runtime image for the app.
# The jar is built by Maven in the pipeline's "build-and-test" job,
# so this image only needs a Java runtime (JRE), not the full JDK.
# ---------------------------------------------------------------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Run as a non-root user (security best practice)
RUN addgroup -S app && adduser -S app -G app
USER app

COPY target/app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
