## Triggerly

Event-driven cryptocurrency tracking backend built with Spring Boot and a microservice architecture.

This project aims to build a scalable crypto monitoring system that provides real-time market data, intelligent price alerts, and push notifications — without requiring users to connect their exchange accounts.

---

## Project Status

This repository is currently in the architecture and planning phase.

The primary focus at this stage:

- Designing clean and independent microservices
- Defining event-driven communication between services
- Establishing clear data ownership boundaries
- Preparing the foundation for alert evaluation and push delivery

Core development has not yet started.

---

## Architecture Overview

The system is designed as a microservice-based backend with asynchronous messaging between services.

Planned(!!!) services:

- auth-service  
  Handles OAuth-based authentication (Google / Apple) and JWT issuance.

- device-service  
  Manages user devices and FCM tokens for push notifications.

- market-data-service  
  Integrates with external providers to fetch and cache market prices.

- alert-service  
  Evaluates user-defined rules based on market price updates.

- notification-service  
  Sends push notifications when alerts are triggered.

Communication model:

- HTTP is used for user-driven operations.
- Async communications if system need it.

Each service owns its own database.

---

## MVP Scope

The initial version will include:

- Device registration and FCM token management
- Market price ingestion
- Price-based alert creation
- Push notification delivery

Out of scope for the MVP:

- Automated trading
- Exchange API key storage
- Bot execution engines

---

## Security Approach

The system does not store exchange API keys.

Users can track assets and create alerts without connecting trading accounts.

The design prioritizes minimal data storage and service isolation.

---
