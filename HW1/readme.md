Autor: José Luca Baptista Pereira 97689

HW1-Mid Term Assigment


run ./mvnw spring-boot:run to start the application on http://localhost:8080/

Note #1: This command might not work with certain maven/java versions, this project was only working reliable when launched from the IDE

Note #2:There are 2 videos with the demonstration given that there where problems in recording the screen for longer


API Calls

GET localhost:8080/all -> All warnings for all districts 

GET localhost:8080/district/{district} -> All warnings for a determined district

GET localhost:8080/level/{level} -> All warnings of the specified severity/level

GET localhost:8080/type/{type} -> All warnings of the specified type

GET localhost:8080/cache -> Get cache information (hits , misses and requests)
