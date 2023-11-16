FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /opt/app
COPY ./build/libs/*.jar /opt/app/
CMD ["java", "-jar", "/opt/app/cc-service-1.0.jar"]
