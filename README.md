# LTI Launch Example Web Application

This is a trivial web application to demonstrate the usage of the [lti-launch](https://github.com/kstateome/lti-launch) project. All it does is respond to an LTI launch request from the Canvas LMS and display a page to the user.

### Tecnologies Used
- Java 8
- Apache Maven (Compatible with 3.5.2, requires 3.1+)
- Spring Boot (1.5.2)
- Apache Tomcat
- [LTI-Launch](https://github.com/kstateome/lti-launch) Library

### Set Up
Before you can build and run this application you need to modify some values in the [src/main/resources/application.properties](src/main/resources/application.properties) file. The important one is `config.property.canvasUrl` which needs to match the URL that you use to access Canvas in a browser.

Since LTI applications are run from an iframe inside of Canvas, browsers get unhappy if you do not use HTTPS. We have bundled a self-signed certificate for `localhost` with this application to make getting up and running as easy as possible but you will obviously have to replace it with something more robust if you want to use this outside of a developer workstation.

The `config.property.lti_launch_secret` value should also not be used outside of a development environment or the security of the LTI launch process will be compromised.

### Running
Once your application properties are set, build the application using Maven:
> mvn clean package

This will create an executable .jar file in the `target` subdirectory.  
Now simply run the .jar file with java:
> java -jar target/lti-launch-example.jar

This will start up an embedded Tomcat instance and start listening for HTTPS traffic on port 10443 (unless otherwise specified in the properties file).

To verify that the application is running, point your browser at: [https://localhost:10443/lti-launch-example/](https://localhost:10443/lti-launch-example/). Due to the self-signed certificate you will have to tell your browser to accept the invalid certificate. This is a required step before launching the application in Canvas anyway.

### Installing in Canvas
The index page returned from the above URL contains instructions on how to install this LTI application in a course via the Canvas UI.

### License
This software is licensed under the LGPL v3 license. Please see the [License.txt](License.txt) file in this repository for license details.
