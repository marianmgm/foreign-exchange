# Foreign Exchange Application
| **Marian Maximov**                                                                                                                                                                                                                                                                                 
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![portfolio](https://img.shields.io/badge/github-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/marianmgm) [![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/marian-maximov-6070662b1) |
## Project Description
The Foreign Exchange application is a straightforward financial service tool that provides real-time currency exchange rates and conversion features. This application is built using Spring Boot and uses exchangerate-api.com

## Swagger
     API documentation available at:
- http://localhost:8080/swagger-ui/index.html

## Installation Guide

- **Clone the repository on your local machine**
- **Make sure you have JDK 17 and MariaDB installed**
-  **run the script located at `\foreign-exchange\foreignExchange\db\database.sql` to create the schema**
-  **run the scripts located at `\foreign-exchange\foreignExchange\db\insert.sql` to populate
   the Database**
- **add your MariaDB login credentials in application properties**
- **Example**
- spring.datasource.username=root
- spring.datasource.password=root   
- **Run the application**

## Technologies
- Java 17
- SpringBoot
- MariaDB
- Hibernate 
- JUnit
- Mockito

## Main Functionalities

### Exchange Rate Endpoint
- **Input:** Pair of currency codes (e.g., USD to EUR).
- **Output:** Current exchange rate between the specified currencies.

### Currency Conversion Endpoint
- **Input:** Amount in the source currency, source currency code, and target currency code.
- **Output:** Converted amount in the target currency along with a unique transaction identifier.

### Conversion History Endpoint
- **Input:** Transaction identifier or a transaction date for filtering.
- **Output:** Paginated list of currency conversions filtered by the provided criteria.

### External Exchange Rate Integration
- Integrates with the external service provider exchangerate-api.com to fetch exchange rates and perform currency conversions.

### Refer to swagger for more information

## Technical Achievements 

- **RESTful API Design:** Developed using Spring Boot, implements 2 versions of the POST controller.
- **Build & Dependency Management:** Managed with appropriate tools.
- **Use of Design Patterns:** Enhances code quality and maintainability.
- **Code Structure:** Organized to reflect a clear separation of concerns.
- **Unit Testing:** 92% lines covered in Service.
- **API Documentation:** Complete and accurate, with request and response examples.
- **Git Repository:** Code maintained with a clear history of commits and adherence to version control best practices.
