# Use OpenJDK 11 with JDK as the base image
FROM openjdk:11-jdk

# Install Node.js and npm
RUN apt-get update && \
    apt-get install -y nodejs npm && \
    npm install -g @angular/cli

# Set the working directory in the Docker image to /app
WORKDIR /app

# Copy the project from host into /app inside Docker image
COPY . /app

# Build Java backend
RUN ./mvnw package

# Build Angular frontend
WORKDIR /app/src/main/UI
RUN npm install
RUN ng build --prod

# Move back to the project root
WORKDIR /app

# Run the Java application
CMD ["java", "-jar", "target/D387_sample_code-0.0.2-SNAPSHOT.jar"]

# Expose the port the Spring application runs on
EXPOSE 8080
