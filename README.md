Andrew 

References:
- https://docs.aws.amazon.com/rds/index.html
- https://aws.amazon.com/elasticbeanstalk/developer-resources/

Connecting ElasticBeanstalk to RDS (MySQL):
- https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/java-rds.html
    - Requires setting of env
    
Okta:
- https://github.com/okta/okta-spring-boot#:~:text=To%20create%20a%20new%20OIDC,as%20a%20login%20redirect%20URI.
- https://github.com/okta/okta-sdk-java

Log: 
4/6:
- Created base backend:
    - Ref: https://github.com/osu467-sp21/shoppingapp/commit/b1ded12bbd75192136aa6a0b4338470b655faf70
- Created AWS RDS database instance
    - Tested with MySQL WorkBench
- Added rough GET/POST endpoints for updating a shopping cart
    - Will further discuss at tomorrow's team meeting 4/7
- Added pom.xml depen for aws sdk
- Confirmed local + EB 

For local development using the mvnw:
-  ./mvnw clean spring-boot:run

To deploy to Elastic Beanstalk, need to package as a .jar:
- ./mvnw package
    
Action Items:
- Configuration: Connect api with rds instance
    - Have endpoint set
- Address security group of rds - currently based on ip with all inbound
- Extract the rds client builder and DI
- Flush out models with team 4/7
- Create logic for comparison algo through POST
- Add billing alarms and restrictions
