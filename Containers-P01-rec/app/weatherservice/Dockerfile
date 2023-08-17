# Build Stage
FROM maven:3.8.4-openjdk-17 AS build
RUN mvn clean package -Pnative -Dquarkus.native.container-build=true

# Run Stage
FROM registry.access.redhat.com/ubi8/ubi-minimal:8.6
WORKDIR /work/
RUN chown 1001 /work \
    && chmod "g+rwX" /work \
    && chown 1001:root /work
COPY --chown=1001:root target/*-runner /work/application

EXPOSE 9090
USER 1001

CMD ["./application", "-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=9090"]
