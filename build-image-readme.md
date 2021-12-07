### Run the following commands from the classpath of each service:
$ mvn spring-boot:build-image -DskipTests

### Container images location
#### Api Gateway
unipi/microservices-cloud-api-gateway:0.0.1-SNAPSHOT'
#### Naming Server
unipi/microservices-cloud-naming-server:0.0.1-SNAPSHOT
#### Currency Exchange Service
unipi/microservices-cloud-currency-exchange-service:0.0.1-SNAPSHOT
#### Currency Conversion Service
unipi/microservices-cloud-currency-conversion-service:0.0.1-SNAPSHOT
#### Zipkin Distributed Tracing Server Docker
$ docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin:2.23

