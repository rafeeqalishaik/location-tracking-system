# location-tracking-system
# SHORT DESCRIPTION
location tracking system is a spring boot application which allows users to search for location pings based on different criteria's.

# PRE-REQUISTIES

1. Java JDK installed on machine.

reference for configuring java on machine:
https://www.wikihow.com/Install-the-Java-Software-Development-Kit
https://www.wikihow.com/Set-Java-Home

2. Eclipse IDE or STS IDE or Any Other IDE to run and alter the code.

reference for different IDE's:
http://www.eclipse.org/downloads/
https://spring.io/tools/sts/all

3. Mongo DB setup on machine

reference for installing mongo db:
https://docs.mongodb.com/v3.0/installation/

4. Postman to test the application

reference link: https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en

# APPLICATION PROPERTIES
1. change application properties if required. properties like
- server port
- mongo db port
- mongo db IP
- mongo db user
- mongo db password etc.

reference for all common properties:
https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

# HOW TO RUN

1. Once pre-requisties are done.
2. clone project to your local machine and import into IDE workspace.
3. Now run the LocationTrackingSystem.java from main menu run.
4. Check the working functionality of the application using postman.

# END-POINTS
1. /tracker/addAssetEvent
2. /tracker/addMobileEvent
3. /tracker/getPingsByAssetId/{assetId}
4. /tracker/getPingsByMobileId/{mobileId}
5. /tracker/getPingsByAssetIdAndTimeInterval/{assetId}/{startTime}/{endTime}
6. /tracker/getPingsByMobileIdAndTimeInterval/{mobileId}/{startTime}/{endTime}