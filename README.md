
## _Broker Service_

This a broker-service developed on Springboot as a rest service,

## How to Build
- Clone the broker folder
- Build the project using following command 
    - mvn -U clean install
- Locate the jar (broker/target/broker-0.0.1-SNAPSHOT.jar)
- Run using the command 
    - java -jar broker-0.0.1-SNAPSHOT.jar --server.domain.url=http://localhost:9092
    (Replace the server.domain.url with actual server url)


## Current Supported Endpoints
- URL - http://localhost:8080/tasklist-queries/{{query_name}}

## Plugins

Dillinger is currently extended with the following plugins.
Instructions on how to use them in your own application are linked below.

| Version | Comment |
| ------ | ------ |
| 0.1 | First Commit |


