# springboot-microservice-hotel-management

![Architecture](https://github.com/Prasadpoojary/springboot-microservice-hotel-management/blob/master/Architecture.png)


Hotel management project with below architecture style. 

## Saga pattern
## Proxy pattern
## Event driven architecture
## JWT authentication/authorization 
## API gateway
## Eureka service discovery
## Logging 


Kafka setup :

Need to create below two topic 
-- HotelNotificationTopic
-- HotelPaymentTopic

.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

.\bin\windows\kafka-server-start.bat .\config\server.properties

.\bin\windows\kafka-topics.bat --create --topic HotelNotificationTopic --bootstrap-server localhost:9092  --partitions 1

.\bin\windows\kafka-topics.bat --create --topic HotelPaymentTopic --bootstrap-server localhost:9092  --partitions 1

.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
