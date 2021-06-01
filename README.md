# Startup Benchmarks
This repository measures the initialization times introduced by various 3 instrumentation techniques: SpecialAgent, OpenTelemetry and Dependency Injection. The repository is adapted from how the official [Spring site](https://spring.io/blog/2018/12/12/how-fast-is-spring) and [Spring Boot founder](https://github.com/dsyer/spring-boot-startup-bench) measure startup times.
 
We measure the benchmarks of these techniques using 3 microservices:
 - SpringHelloWorld showcasing a demo of Spring Boot 
 - The Carts microservices from the [SockShop](https://microservices-demo.github.io/) microservice reference application made by [Weaveworks](https://www.weave.works/).
 - The ts-travel-service from [Train Ticket](https://github.com/FudanSELab/train-ticket/).

## Build
This project uses two agents for instrumentation. These are already included in this project within the benchmark/agent folder. Alternatively, they can be retrieved by doing the following:
 - Build the jar from our fork of SpecialAgent which can be found [here](https://github.com/Xitric/java-specialagent)
 - Download the jar for OpenTelemtry [here](https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent-all.jar)

The 3 microservices in this project are also already built. Control projects are microservices without instrumentation and are found within benchmark/control. The others are instrumented with Dependency Injection and are found within benchmark/di:
 - SpringHelloWorld
 - SpringHelloWorldControl
 - carts-control
 - carts
 - ts-travel-service-control
 - ts-travel-service

Alternatively, these can be built again using the following command:
```bash
mvn clean install
```

## Run benchmarks
Before running the benchmarks make sure that the files within the benchmark folder points to the correct microservice jars and agents.
To build the benchmark application use the following command in the benchmark folder. This will build the jar file to run the benchmarks:

```bash
mvn clean install
```

To run the benchmark use the following command in the benchmark folder. This will run the jar file that will conduct benchmarks and print the results in a text file:

```bash
java -jar target/benchmarks.jar -rf text
```
