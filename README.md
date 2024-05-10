**Multi-Tenant File Processor Application**

This Spring Boot application demonstrates a robust, scalable, and multi-tenant system designed to handle file ingestion from S3 buckets triggered via Kafka events. It processes these files per tenant-specific configurations and persists the data into tenant-specific databases. The system ensures data integrity and provides detailed alerting in case of processing failures.

**Features**

Multi-Tenancy: Dynamically handles data processing based on the tenant.
Kafka Integration: Listens to events from a Kafka topic to process files added to S3.
AWS S3 Interaction: Retrieves and deletes files from tenant-specific S3 buckets.
Data Persistence: Saves processed data into tenant-specific databases.
Error Handling: Global exception handling and error reporting through Kafka.
REST API: Exposes endpoints to retrieve processed data based on tenant_id and device_id.

**Getting Started**

**Prerequisites**

  Java 11 or newer.
  Maven 3.6 or newer.
  Access to an AWS account and an S3 bucket.
  A Kafka broker running.
  Access to a SQL database (e.g., PostgreSQL).
  
**Setup and Configuration**

  1. **Clone the repository:**
    git clone https://github.com/Jatinchauhan10/GE-Assignment.git
    cd 

  2. **Configure application properties:**Set up src/main/resources/application.properties with the necessary configurations:
      Kafka configurations
      spring.kafka.bootstrap-servers=localhost:9092
      spring.kafka.consumer.group-id=file-ingest-group
      spring.kafka.consumer.auto-offset-reset=earliest
      
      AWS S3 configurations
      cloud.aws.credentials.access-key=YOUR_AWS_ACCESS_KEY
      cloud.aws.credentials.secret-key=YOUR_AWS_SECRET_KEY
      cloud.aws.region.static=us-west-2
      
      Database configurations
      spring.datasource.url=jdbc:postgresql://localhost:5432/yourdatabase
      spring.datasource.username=dbuser
      spring.datasource.password=dbpassword
  
    Ensure to replace placeholders like YOUR_AWS_ACCESS_KEY with actual values.

  3. Build the application:Compile and package the application using Maven:
      mvn clean package
  
  4. Run the application:Start the Spring Boot application:
     java -jar target/multitenant-file-processor-0.1.0.jar


**API Usage**

  To retrieve data for a specific tenant and device, use the following REST endpoint:
      GET /api/data/{tenantId}/{deviceId}
  Replace {tenantId} and {deviceId} with actual values to fetch data.

**Handling Failures**

The application includes global exception handling and uses Kafka to alert on failures. If processing a file fails, the application will log the error and send an alert message to a specified Kafka topic. Ensure to monitor this topic to respond to incidents quickly.

**Contributing**

Feel free to fork the repository and submit pull requests. You can also open issues to discuss potential fixes or enhancements.








      
