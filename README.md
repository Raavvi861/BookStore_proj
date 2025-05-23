# Bookstore API Automation with CI/CD Integration

An end-to-end API automation framework for a Bookstore REST API using **Cucumber BDD**, **TestNG**, and **Java 18**, designed with robust CI/CD capabilities for streamlined development and testing workflows.

---

## 🚀 Tech Stack

| Component     | Description                                                                 |
|---------------|-----------------------------------------------------------------------------|
| IDE           | IntelliJ IDEA (or any Java-compatible IDE)                                 |
| Language      | Java 18                                                                     |
| Framework     | Cucumber BDD + RestAssured for behavior-driven API testing                 |
| Build Tool    | Maven (dependency management and CI/CD integration)                         |
| Test Runner   | TestNG (supports parallel execution, retries, and flexible configurations)  |

---

## ✅ Why This Stack?

- **TestNG**: Ideal for API testing with parallel execution, retry logic, and integration with Jenkins pipelines.
- **Cucumber BDD**: Allows writing test cases in plain English using Gherkin syntax, bridging gaps between developers, testers, and business stakeholders.
- **RestAssured**: Provides a fluent API for simplifying the validation of REST services.

---

## ⚙️ Prerequisites

### Java 18
- Install Java 18 (Oracle/OpenJDK)
- Set `JAVA_HOME` (e.g., `C:\Program Files\Java\jdk-18`)
- Add `%JAVA_HOME%\bin` to system PATH
- Confirm with:
  ```bash
  java -version
  ```

### Maven
- Download from [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
- Add Maven’s `bin` directory to PATH
- Confirm with:
  ```bash
  mvn -version
  ```

### Git
- Download from [https://gitforwindows.org/](https://gitforwindows.org/)
- Configure SSH keys for GitHub: [GitHub SSH Setup Guide](https://docs.github.com/en/authentication/connecting-to-github-with-ssh)
- Confirm with:
  ```bash
  git --version
  ```

### Optional Tools
- **IDE**: IntelliJ IDEA / Eclipse
- **API Client**: Postman for manual verification

---

## 🧪 Setup & Running Tests

### 1. Clone the Repository

**Using SSH:**
```bash
git clone git@github.com:Raavvi861/BookStore_proj.git
cd Proj_BookStore
```

**Using HTTPS:**
```bash
git clone https://github.com/Raavvi861/BookStore_proj.git
cd Proj_BookStore
```

### 2. Check Java Version
Ensure you're using Java 18:
```bash
java -version
```

### 3. Build the Project
Install dependencies and compile:
```bash
mvn clean install
```

### 4. Run Tests
Execute all Cucumber test scenarios:
```bash
mvn test
```

---

## 📘 API Endpoints Covered

| Method | Endpoint            | Description            |
|--------|---------------------|------------------------|
| POST   | `/signup`           | Register a new user    |
| POST   | `/login`            | Authenticate a user    |
| POST   | `/books`            | Create a new book      |
| PUT    | `/books/{id}`       | Update book details    |
| GET    | `/books/{id}`       | Get book by ID         |
| GET    | `/books`            | List all books         |
| DELETE | `/books/{id}`       | Delete a book          |

---

## 🛠️ Troubleshooting

- **Test Failures**: Check for status code mismatches (e.g., `422 Unprocessable Entity`). Ensure step definitions (e.g., `UserStepDefs.java`) have the correct assertions.
- **Java Errors**: Confirm `JAVA_HOME` is correctly set to Java 18.
- **Debug Mode**: Run with extended logging:
  ```bash
  mvn test -e -X
  ```

---

## 🔁 CI/CD Integration

### Prerequisites
- **Jenkins**: Installed with Git, Maven, GitHub, and Pipeline plugins
- **Ngrok**: To expose local Jenkins server for webhook testing

### Step-by-Step Integration

#### 1. Jenkinsfile for Dev Repository
```groovy
pipeline {
    agent any
    stages {
        stage('Build Dev') {
            steps {
                echo 'Building Dev code...'
            }
        }
        stage('Trigger QA Automation') {
            steps {
                build job: 'QA-Repo'
            }
        }
    }
}
```

#### 2. Jenkinsfile for QA Repository
```groovy
pipeline {
    agent any
    tools {
        maven 'Maven 3.6.3'
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Raavvi861/BookStore_proj.git', branch: 'master'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}
```

#### 3. Jenkins Setup
- Launch Jenkins (`java -jar jenkins.war` or via installed service)
- Create jobs for:
  - `Dev-Repo` (uses Dev Jenkinsfile)
  - `QA-Repo` (uses QA Jenkinsfile)

#### 4. Setup GitHub Webhook
- Start Ngrok:
  ```bash
  ngrok http 8080
  ```
- Use Ngrok URL in GitHub webhook:
  - Payload URL: `https://<ngrok-id>.ngrok.io/job/Dev-Repo/build`
  - Content type: `application/json`
  - Trigger: `Just the push event`

#### 5. Trigger CI/CD Pipeline
- Push code to the repository
- Jenkins pipeline triggers automatically and executes API test suite

---

## 📊 Reporting

Test execution reports are available in:
```
target/cucumber-reports
```

These reports include:
- Feature-wise execution summary
- Scenario pass/fail breakdown
- Step-level insights with status and duration

---

## ⚠️ Notes

- Always ensure Java 18 compatibility across the environment (`JAVA_HOME`, `pom.xml`)
- For production CI/CD, use a secured, public Jenkins instance
- Prefer SSH cloning for better security; use HTTPS only when SSH setup is unavailable

---
