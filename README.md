# Currency-Exchange-Conversion-Microservices-Backend

#### Spring cloud configuration server URLs:

    http://localhost:8888/db-credentials

    http://localhost:8888/limits-service/qa


#### Eureka URLs:

    http://localhost:8761

#### Currency-exchange URLs

    http://localhost:8000/currency-exchange/from/USD/to/INR


#### Currency Conversion URLs

    http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/20

#### Api-Gateway 

##### URLs: http://localhost:8765/<name of service from Eureka>/<service URL exposed>

    http://localhost:8765/CURRENCY-EXCHANGE-SERVICE/currency-exchange/from/USD/to/INR
    http://localhost:8765/CURRENCY-CONVERSION-SERVICE/currency-conversion-feign/from/USD/to/INR/quantity/20
    http://localhost:8765/CURRENCY-CONVERSION-SERVICE/currency-conversion/from/USD/to/INR/quantity/20


After adding property: spring.cloud.gateway.discovery.locator.lower-case-service-id=true everything can be lower caps

    http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR
    http://localhost:8765/currency-conversion-service/currency-conversion-feign/from/USD/to/INR/quantity/20
    http://localhost:8765/currency-conversion-service/currency-conversion/from/USD/to/INR/quantity/20
    
 After adding filters and removing properties **spring.cloud.gateway.discovery.locator.lower-case-service-id=true**  and **spring.cloud.gateway.discovery.locator.enabled=true**
 
    http://localhost:8765/currency-exchange/from/USD/to/INR
    http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/20
    http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/20
    http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/20 -> rename url for-> http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/20
    
##### Spring cloud Gateway: 
    Efficient way to route to APIs
    Provide cost cutting concerns: Security, monitoring/Metrics are some examples on what can be implemented in gateway. 
    Functions common to all services are implemented  here.

##### Features:
     Match routes on any request attribute
     Define Predicates and Filters
     Integrates with spring cloud discovery client (Load Balancing)
     Path Rewriting
