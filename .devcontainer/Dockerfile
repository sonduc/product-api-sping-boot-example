FROM maven:3.9-eclipse-temurin-21

WORKDIR /workspace

# Keep the JDWP agent on the Spring Boot JVM, not Maven's JVM.
COPY .devcontainer/mvn-with-debug /usr/local/bin/mvn
RUN chmod +x /usr/local/bin/mvn
ENV PATH="/usr/local/bin:${PATH}"
