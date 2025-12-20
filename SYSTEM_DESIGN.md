# Personal Finance Platform – System Design

## 1. Overview

This document describes the system design for a scalable, automated personal finance platform that provides users with a real-time dashboard and AI-driven insights **without requiring manual transaction entry**.

The system is built using:
- Microservices architecture
- Event-driven processing
- Hybrid technology stack:
  - Java + Spring Boot for core financial services
  - Python + FastAPI for AI and ingestion services

The design prioritizes scalability, data integrity, reliability, and extensibility.

---

## 2. Problem Statement

Manual expense tracking has low adoption and poor long-term retention.  
Users do not want to manually record every transaction they make.

**Primary design goal:**  
Minimize user effort by automatically ingesting financial data and generating insights asynchronously.

---

## 3. Architectural Principles

- Strong consistency for financial writes
- Eventual consistency for analytics and AI
- Stateless services for horizontal scalability
- Clear ownership of data per service
- Graceful degradation of non-critical components

---

## 4. High-Level Architecture

The platform consists of:
- Stateless backend microservices
- Dedicated databases per service
- A message queue for asynchronous workflows
- A reporting layer optimized for frontend consumption

Core financial operations are isolated from slow or failure-prone AI and ingestion workflows.

---

## 5. Technology Stack

### Core Services (System of Record)
**Technology:** Java + Spring Boot

Used for:
- Authentication
- Transaction management
- Analytics computation
- Dashboard reporting

Chosen for:
- ACID guarantees
- Mature security ecosystem
- Predictable performance under load
- Strong messaging support

---

### AI & Ingestion Services
**Technology:** Python + FastAPI

Used for:
- Data ingestion and normalization
- AI/ML/LLM-based insight generation

Chosen for:
- Rich ML/NLP ecosystem
- Faster iteration cycles
- Clean separation from financial core logic

---

## 6. Microservices Overview

### Auth Service (Java)
- User registration and authentication
- JWT issuance and validation
- Owns identity data

---

### Transaction Service (Java)
- Single source of truth for income and expenses
- Writes transactions directly to its database
- Publishes `transaction-created` events after successful commits
- No analytics or AI logic

---

### Analytics Service (Java)
- Consumes transaction events
- Maintains precomputed aggregates (monthly totals, category splits)
- Stores derived analytics data
- Publishes `stats-updated` events

---

### AI Insights Service (Python)
- Consumes analytics updates
- Generates natural-language insights using rules, ML, or LLMs
- Stores insights independently
- Failures do not affect core transaction flows

---

### Ingestion Service (Python)
- Ingests financial data from:
  - Emails
  - SMS
  - PDF statements
  - Bank APIs (future)
- Normalizes and deduplicates data
- Sends validated transactions downstream

Manual entry is a fallback, not the primary input method.

---

### Reporting Service (Java)
- Backend-for-Frontend (BFF)
- Aggregates data from:
  - Transaction Service
  - Analytics Service
  - AI Insights Service
- Returns a single optimized dashboard response
- Uses caching to minimize latency and fan-out

---

## 7. Communication Patterns

### Synchronous (REST)
Used when:
- Immediate response is required
- Strong consistency is needed

Examples:
- Reporting → Transaction
- Reporting → Analytics
- Reporting → AI Insights

---

### Asynchronous (Events)
Used when:
- Processing is slow or compute-heavy
- Eventual consistency is acceptable

Examples:
- Transaction → Analytics
- Analytics → AI Insights

Recommended technologies:
- Kafka (production)
- RabbitMQ (learning phase)

---

## 8. Data Ownership & Consistency

- Each service owns its database
- No service reads another service’s database directly
- Cross-service communication occurs via APIs or events

Consistency model:
- Strong consistency for transaction writes
- Eventual consistency for analytics and AI

---

## 9. Caching Strategy

Redis is used to cache:
- Analytics results
- AI insights
- Aggregated dashboard responses

Caching improves latency, reduces load, and limits service fan-out.

---

## 10. Failure Handling

- AI service down → dashboard loads without insights
- Analytics lagging → cached data served
- Queue backlog → workers scale independently
- Core transaction writes are never blocked by downstream failures

Graceful degradation is mandatory.

---

## 11. Security

- JWT-based authentication
- HTTPS everywhere
- User-level data isolation
- Least-privilege database access
- Secure OAuth flows for external integrations

---

## 12. Scalability Considerations

- Stateless services enable horizontal scaling
- Database read replicas for read-heavy workloads
- Partitioning by user or time as data grows
- Independent scaling of AI and ingestion services

---

## 13. Development Approach

- Agile, sprint-based delivery
- Incremental complexity
- Early focus on correctness and observability
- Clear path from MVP to production-grade system

---

## 14. Final Outcome

This design:
- Removes manual data-entry friction
- Scales cleanly with user growth
- Mirrors real-world fintech architectures
- Serves as a strong portfolio and interview artifact
