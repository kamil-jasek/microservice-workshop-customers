FROM gcr.io/distroless/java17-debian11
COPY /target/customers-0.0.1-SNAPSHOT.jar /app.jar
CMD ["app.jar"]
