# ConnectCities
Spring Boot project to find if a given origin and destination are connected based on the data in file.<br />
 The logic involves recursive object to keep track of all the connected cities to a particular city. The search is based on recursion too until the destination is found.


##Prerequisites
 * IDE capable with Java 8 and Gradle.
 * ```file.location``` needs to be set in ```application.yml```


##Assumptions
* Project is imporetd as a Gradle project and dependencies are resolved.
* Each line in file contains 2 city names separated by a comma.


## Running Tests
* Unit Tests can be run using the command ```gradle test``` from terminal.
* Current test line coverage is 100%.
* Mutation Tests can be run using the command ```gradle pitest``` from terminal.
* Current Mutation Test coverage is 95% and the threshold is set to that.


##Running the App
 * Paste the data in the file located in the above path.
 * Run the Application file ```ConnectCitiesApplication.java```.
 * Hit the REST endpoint with format:
 ```http://localhost:8080/connected?origin=city1&destination=city2```<br />
 Both 'origin' and 'destination' are mandatory.

