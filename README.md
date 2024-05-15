# Back to the future

## Description
This is a small project that will have the following behavior:
- As input, a basket in text form, separated by newlines which contains the name of the films purchased.
- As output, the number representing the price.

## Technical Info
- Java 11
- Spring-Boot 2.7.18
- Maven 3.6.3

## How to run the project
1. To run this project locally, make sure you have jdk 11 and maven 3.6.x installed.
2. Clone the project using git clone.
```bash
    git clone project-url
```
3. To build the project use below maven command.
   (The flag ```-DskipTests``` is used to skip running tests and build faster)
```bash
    mvn clean install -DskipTests
```
4. The project jar should be available in the project's ```target``` directory.
5. The project could be executed using the command,
```bash
    java -jar target/back-to-future-1.0.0.jar
```
6. Sample output:
```bash
Enter your string:
BACK TO THE FUTURE 1
BACK TO THE FUTURE 3
end
The total price of all DVDs is: 27 euros.
```
### Note: 
* As input, the name of the films are separated by newlines. 
* To mark the end of input, you should enter the text "**END**" (_in upper or lower case_) to see the final output.