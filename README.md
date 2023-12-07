# cc2023

## Gradlew Commands

### 1. Build Docker Image

To build the Docker image, use the following command:


```
./gradlew jibDockerBuild
```

This command leverages the Jib plugin to construct a Docker image for your application. The resulting image is ready for deployment using Docker.

**Windows Equivalent:**

```
gradlew.bat jibDockerBuild
```

### 2. Build and Load Docker Image into Kind Cluster

To load the Docker image into a Kind cluster, ensure your Kind cluster is active, and then execute the following command:

```
./gradlew loadDockerImageIntoKind
```

This command is essential for deploying your application within the Kind cluster. It must be executed before with the deploying the `deployment.yml` file.

**Windows Equivalent:**

```
gradlew.bat loadDockerImageIntoKind

```

