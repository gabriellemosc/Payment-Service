# Payment Service — Stripe Integration

Backend payment processing service built with **Java and Spring Boot**, designed to handle secure online transactions for an e-commerce platform.

This service integrates with **Stripe** to manage checkout sessions, process payments, and synchronize transaction states through asynchronous webhooks.

The project was developed as part of a **real freelance collaboration for an e-commerce platform**, focusing on building a reliable and maintainable payment infrastructure.

---

## Overview

The **Payment Service** acts as the financial transaction layer of an e-commerce system.
Its main responsibility is to handle the lifecycle of payments, from order creation to payment confirmation.

The service communicates with Stripe to create checkout sessions and receives asynchronous events from Stripe to update the status of transactions in the system.

---

## Architecture

The project follows a **layered architecture**, promoting separation of concerns and maintainability.

```
Controller Layer
   ↓
Service Layer
   ↓
Gateway Layer (Stripe integration)
   ↓
Repository Layer
   ↓
Database (PostgreSQL)
```

### Layers

**Controllers**

* Expose REST endpoints
* Receive requests from the client application
* Validate input and forward requests to the service layer

**Services**

* Contain business logic
* Manage the payment lifecycle
* Coordinate interactions between repositories and external APIs

**Gateway**

* Responsible for external communication with Stripe
* Creates checkout sessions
* Handles Stripe-related logic

**Repositories**

* Manage database persistence
* Implemented using Spring Data JPA

**DTOs**

* Handle data transfer between layers
* Prevent exposing internal domain entities

---

## Technologies

Main technologies used in the project:

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Stripe API
* Maven

---

## Payment Flow

The payment lifecycle follows this process:

1. A user creates an order in the e-commerce system.
2. The backend creates a **Stripe Checkout Session**.
3. The user is redirected to Stripe's secure payment page.
4. Stripe processes the payment.
5. Stripe sends a **Webhook Event** to the backend.
6. The system updates the transaction status in the database.

Possible payment states:

* `pending`
* `succeeded`
* `failed`

---

## Stripe Webhooks

Stripe sends asynchronous events to notify the system about payment updates.

The service listens for events such as:

* `checkout.session.completed`
* `payment_intent.succeeded`
* `payment_intent.payment_failed`

These events trigger updates in the payment lifecycle stored in the database.

---

## Database Model

The main domain entities include:

### Users

Represents customers of the e-commerce platform.

### Orders

Represents purchase requests created by users.

### Payments

Stores transaction information related to orders, including:

* payment status
* Stripe session ID
* transaction metadata

Relationships:

```
User → Order → Payment
```

This structure allows full traceability of financial operations.

---

## Security Considerations

The system follows several best practices for secure payment handling:

* Sensitive API keys stored as environment variables
* Webhook signature verification
* Separation between internal domain entities and external DTOs
* Structured exception handling

---

## Running the Project

### Requirements

* Java 17+
* Maven
* PostgreSQL
* Stripe developer account






## Project Goals

This project was built to explore and implement real-world concepts used in financial backend systems:

* payment gateway integration
* asynchronous event processing
* transactional data modeling
* layered backend architecture
* external API integration

---

## Learning Objectives

While developing this project, the following topics were explored:

* Stripe payment flows
* webhook-driven systems
* domain modeling for financial data
* REST API design with Spring Boot
* backend architecture patterns

---

## Future Improvements

Potential improvements include:

* idempotency for payment operations
* message queue for event processing
* distributed transaction handling
* observability and metrics
* automated tests

---

## License

This project is provided for educational and portfolio purposes.
