# WebScraper
Console app to scrape sainsbury's test product page

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

```
Gradle
Java version 8
```

### Installing

```
Download or clone the project
```

```
Either unpack the zip file if downloaded and import into an IDE or import from GitHub straight into an IDE as gradle project
```


## Running the tests

For IDE run following task

```
run "gradle clean" then "gradle test"
```

For console

```
Navigate to the root of the project

run "gradle clean test"
```

## Running the application

From IDE

```
Simply run the "Application" from run configuration
```

from console

```
Navigate to the root of the project

run "gradle clean run"

the output of the process should be printed on the console after successful run
```

run from a jar

```
Navigate to the root of the project

run "gradle clean fatJar"

this should create a "sainsbury-scrape-test-1.0-all.jar" under build/libs dir

navigate to "build/libs"

run "java -jar sainsbury-scrape-test-1.0-all.jar" to run the program

the output of the process should be printed on the console after successful run
```

## Assumptions made

    - the program will only scrape the url provided in the test, hence no options to input the url is provided
    - If the first line of description is empty, then an empty description will be returned in the json

## Notes

    * I have spent 5-7 hours, on and off on the excercise, there are some obvious further changes I will like to make given more time.
    -   Increase test coverage specially in services folder.
    -   Code can be refactored a bit for example few of the parsers can be consolidated, an input of serch criteria can be provided etc.
    -   The print service can also provide multiple ways of displaying the output like saving it to a file etc.
    -   The service can be enhanced to implement SpringBoot rest api and the json can be returned as response to a get call.
    -   wiremock can be used to add integration tests
    -   Enhanced integration test can be written using Selenium webdriver (or similar) to test the scraping capability of the code.
