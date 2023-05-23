# Use the base Quarkus Docker image
FROM quay.io/quarkus/ubi-quarkus-native-image:25.4.1-java11 AS native
LABEL maintainer="Your Name <your-email@example.com>"

# Set the working directory inside the container
WORKDIR /work/

# Add your Quarkus application JAR file
COPY target/*-runner /work/application

# Add your JWT public key file
COPY jwt/publicKey.pem /work/jwt/publicKey.pem

# Build the native executable
RUN native-image --no-server -Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager -H:Class=io.quarkus.runner.GeneratedMain -H:Name=application -H:+ReportUnsupportedElementsAtRuntime --enable-all-security-services -H:-UseServiceLoaderFeature -H:+StackTrace application

# Create a minimal Docker image
FROM registry.access.redhat.com/ubi8/ubi-minimal
WORKDIR /work/
COPY --from=native /work/application .

# Set the command to run when the container starts
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]