## Project Setup

**Language used**  

Groovy

**Testing framework** 

Spock

**Build tool**

Gradle (and its wrapper)

## Requirements

Java 8 (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## High Level Description

Everything is designed around things that a writer can do. So we have a Writer class can write, edit and read content
onto/from a given surface (paper in this implementation) using a given utensil (pencil in this implementation). 
As well as perform things to that utensil like sharpen it.  

## Tests

2 types of tests are in the project unit and integration. In all the unit tests we mock out the classes dependencies whereas
in the integration tests we actually leave all the real implementations in place and validate the behavior all the way 
through. 

A good starting place to see how the full system works is [WriterIntegrationSpec](src/test/groovy/com/zender/pencil/WriterIntegrationSpec.groovy)
since it performs all the actions that a writer can perform. 

### How to run the tests

Since this uses gradle and its wrapper it will download the gradle build tool if its not already downloaded so all you 
need to do is have java installed and then execute the gradle tasks found below.

#### Mac OSX/Linux

1. Open a new terminal window
2. Navigate to the project directory 
3. Run the following `./gradlew clean test`

#### Windows

1. Open a new command prompt window 
2. Navigate to the project directory
3. Run the following `gradlew.bat clean test`