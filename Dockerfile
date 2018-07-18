FROM java:8
ADD target/location-service.jar .
EXPOSE 8003
CMD java -jar -Xmx512M location-service.jar