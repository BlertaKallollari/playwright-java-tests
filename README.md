# Automated Test Suite: UI and API Testing with Playwright (Java)

## 📌 Overview

This project demonstrates an automated test suite built using **Playwright for Java**. It validates:
- Web UI functionality of the file upload page on [The Internet](https://the-internet.herokuapp.com/upload)
- Public API functionality using [JSONPlaceholder](https://jsonplaceholder.typicode.com)

---

## 🧪 Technologies Used

- Java
- JUnit 5
- Playwright Java
- Git
- GitHub

---
## 🧱 Test Architecture & Design
### Modular Design:
- Tests are logically grouped (UI and API) for clarity and maintainability.

### Base Class:
- Manages browser lifecycle using Playwright’s setup and teardown.

### Separation of Concerns:
- Utilities are decoupled from tests, keeping code reusable and clean.

### Assertions:
- Built using JUnit 5 assertions for reliability.

---

## 🧗 Challenges Encountered
### Playwright CLI Binary Installation

#### Drag-and-Drop Area Testing

- Targeted a specific DOM area (#drag-drop-upload) for file interaction. Despite a known server-side 500 Internal Server Error, the UI test verifies correct file placement and interaction with the Upload button.

#### Strict Mode in Playwright

- Resolved locator conflicts by explicitly narrowing file input scope within the drag-and-drop component.

---

## 🕹️ How to Run the Tests

### ✅ Prerequisites

- Java 17+
- Maven
- Git

### 🚀 Setup Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/BlertaKallollari/playwright-java-tests.git
