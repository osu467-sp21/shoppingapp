For local development using the mvnw:
-  ./mvnw clean spring-boot:run

To deploy to Elastic Beanstalk, need to package as a .jar:
- ./mvnw package
- Make sure to set Configuration SERVER_PORT = 5000 (local default is 8080)
