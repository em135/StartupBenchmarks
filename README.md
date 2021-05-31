# Startup Benchmarks
This repository measures the initialization times introduced by various 3 instrumentation techniques: SpecialAgent, OpenTelemetry and Dependency Injection. 

We measure the benchmarks of these techniques using 3 microservices:
 - SpringHelloWorld showcasing a demo of Spring Boot 
 - The Carts microservices from the [SockShop](https://microservices-demo.github.io/) microservice reference application made by [Weaveworks](https://www.weave.works/).
 - The ts-travel-service from [Train Ticket](https://github.com/FudanSELab/train-ticket/).

## Run
To build the benchmark application use the following command in the benchmark folder. This will build the jar file to run the benchmarks:

```bash
mvn clean install
```

To run the benchmark use the following command in the benchmark folder. This will run the jar file that will conduct benchmarks and print the results in a text file:

```bash
java -jar target/benchmarks.jar -rf text
```
