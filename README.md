# 🏛️ National Health Ecosystem (Microservices)

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-2024.0.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-cloud)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203-85EA2D?style=for-the-badge&logo=openapi&logoColor=black)](https://swagger.io/)

An enterprise-grade, highly secure, distributed healthcare architecture managing national citizen data and multi-tiered government medical health insurance systems via containerized microservices.

---

## 📌 Architecture Overview

This platform is structured as a **decoupled, multi-database microservice mesh** ensuring total isolation of critical citizen profiles from financial subsidy data pools.

* **🆔 National Health Registry Service (`Port 8081`)**: Acts as the single source of truth for citizen identity verification. It handles demographic checks, regional classifications, and identity matches against a dedicated PostgreSQL database.
* **💳 Gov Health Insurance Service (`Port 8082`)**: An independent engine managing health schemes, insurance enrollments, and subsidy qualifications. It communicates with the Registry Service securely using **Spring Cloud OpenFeign** client structures to validate citizen eligibility before executing updates on its separate PostgreSQL cluster.

```text
                  [ Multi-Service Gateway Layer ]
                                 │
         ┌───────────────────────┴───────────────────────┐
         ▼                                               ▼
┌─────────────────────────────────┐             ┌─────────────────────────────────┐
│   🆔 National Health Registry   │             │   💳 Gov Health Insurance       │
│          (Port 8081)            │             │          (Port 8082)            │
├─────────────────────────────────┤             ├─────────────────────────────────┤
│ • Spring Boot 3.4.3             │   Feign     │ • Spring Boot 3.4.3             │
│ • Swagger UI Engine             │ ──────────> │ • OpenFeign Client Interceptors │
└────────────────┬────────────────┘             └────────────────┬────────────────┘
                 │                                               │
                 │ (Internal Bridge Network)                     │ (Internal Bridge Network)
                 ▼                                               ▼
┌─────────────────────────────────┐             ┌─────────────────────────────────┐
│    🗄️ registry-db Container    │             │   🗄️ insurance-db Container     │
│          (Port 5433)            │             │          (Port 5434)            │
└─────────────────────────────────┘             └─────────────────────────────────┘
