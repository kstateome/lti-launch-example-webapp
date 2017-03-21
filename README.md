# LTI Launch Example Webapp

This is an example web application to demonstrate how the lti-launch works.

### Tecnologies Used
- Java 8
- Springboot 1.5.2.RELEASE
- LTI-Launch Library

### Usage
The LTI launch example webapp has a springboot application.properties file that needs to be configured with the values that is valid for your institution. The required properties are the canvasUrl, oAuthClientId, oAuthClientSecret and the oAuthClientSecreteId.
If you want to run the embedded server in a secure port, you must generate a self signed cert, put it under the resources directory and update the server.ssl.key-store properties in the application.properties. 
- Build the project with:
> mvn install
- To run the web application:
> java -jar target/lti-launch-example.jar