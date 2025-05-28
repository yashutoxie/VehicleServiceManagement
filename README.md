# Vehicle Service Management System  

A capstone backend application built with Spring Boot to manage vehicle servicing operations. The system supports key workflows for both **Admin** and **Service Advisor** roles, including vehicle scheduling, service tracking, billing, and master data management.

## Project Overview

The **Vehicle Service Management System** automates service center operations, enabling Admins to manage scheduling, invoicing, dispatching, and service advisors to track and complete assigned services. This version includes a RESTful API built using Spring Boot, JPA/Hibernate, and a relational database (MySQL, PostgreSQL, etc.).

## User Roles & Functionalities

### Admin

* **Dashboard Overview**:

  * View vehicles due for servicing in the current week.
  * Monitor vehicles currently under service.
  * Access serviced vehicle records with status.

* **Service Scheduling**:

  * Assign vehicles to service advisors.
  * Schedule service appointments.

* **Post-Service Operations**:

  * Generate service invoices.
  * Process payments.
  * Dispatch serviced vehicles.

* **Master Data Management**:

  * Create, update, delete, and search:

    * Vehicles
    * Customers
    * Service Advisors
    * Work Items (Engine Oil, Fuel Filter, Service Charges, etc.)

### Service Advisor

* **Assigned Services**:

  * View scheduled services assigned by Admin.

* **Service Execution**:

  * Add items (e.g., Oil Change, Wheel Alignment) with quantities to the bill of materials.
  * Complete service records, making them ready for Admin review.

> Note: Service Advisors cannot modify the predefined prices of items.

## Technology Stack

* **Backend**:

  * Java 8+
  * Spring Boot
  * Spring Data JPA (Hibernate)

* **Database**:

  * MySQL / PostgreSQL / Oracle Express / MongoDB (choose based on configuration)

* **Build & Dependency Management**:

  * Maven

* **Dev Tools**:

  * Visual Studio Code / Eclipse (with Spring Tools Suite)
  * GIT

* **DevOps (Optional)**:

  * Jenkins, Docker, Selenium (for CI/CD and testing)

## Getting Started

### Prerequisites

* Java 8 or later
* Maven
* MySQL (or any supported DB)
* Postman or Swagger for testing APIs

### Setup & Run

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/yashutoxie/VehicleServiceManagement.git
   cd VehicleServiceManagement
   ```

2. **Configure the Database**:

   * Edit `src/main/resources/application.properties`:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/vehicle_service_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Build & Run the Application**:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the API**:

   * Default base URL: `http://localhost:8080/api/`

5. **Test the Endpoints**:

   * Use Postman or Swagger UI (if enabled) to interact with the API.

--- 
