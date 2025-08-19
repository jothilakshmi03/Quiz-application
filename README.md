# Quiz-application
📚 Quiz App

A fully functional Quiz Application built using Spring Boot (Java 21, Maven) for the backend and HTML, CSS, and JavaScript for the frontend.
This app allows users to register, log in, participate in quizzes, and view a leaderboard with top scores.

The project is designed as a simple yet scalable full-stack application, making it suitable for learning, educational institutions, and personal projects.

✨ Features

🔐 User Authentication – Secure registration & login system

❓ Quiz Module – Multiple-choice quiz functionality with score calculation

🏆 Leaderboard – Displays top-performing users

🎨 Frontend – Static pages (HTML/CSS/JS) served via Spring Boot

⚡ Backend – REST APIs and business logic using Spring Boot

🗄️ Database – Supports H2 (in-memory) or MySQL for persistence

🧩 Modular Architecture – Easily extendable for new features

🛠️ Tech Stack

Backend:

Java 21

Spring Boot 3.2.4

Spring Security (if authentication is implemented)

Maven for dependency management

Frontend:

HTML5

CSS3

JavaScript

Database (configurable):

H2 (in-memory, default for development)

MySQL (for production)

📂 Project Structure
quiz-app/
 ├── src/
 │   └── main/
 │       ├── java/com/example/quizapp/     # Spring Boot backend code
 │       │   ├── controller/               # REST controllers
 │       │   ├── model/                    # Entities (User, Quiz, Leaderboard)
 │       │   ├── repository/               # JPA Repositories
 │       │   ├── service/                  # Business logic
 │       │   └── QuizAppApplication.java   # Main application
 │       └── resources/
 │           ├── static/                   # Frontend (HTML, CSS, JS)
 │           │   ├── login.html
 │           │   ├── register.html
 │           │   ├── quiz.html
 │           │   └── leaderboard.html
 │           └── application.properties    # App configuration
 ├── pom.xml                               # Maven build file
 ├── LICENSE
 └── README.md

⚡ Getting Started
✅ Prerequisites

Make sure you have installed:

Java 21+

Maven 3+

Git

MySQL (optional, if using persistent DB)

🚀 Run Locally
# Clone the repository
git clone https://github.com/your-username/quiz-app.git
cd quiz-app

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run


The app will be available at:
👉 http://localhost:9090

🗄️ Database Setup
Using H2 (Default)

No setup required, app will automatically use H2 in-memory database.

Access H2 console at:
👉 http://localhost:9090/h2-console

Using MySQL

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/quizdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


Then create the database:

CREATE DATABASE quizdb;

🧑‍💻 API Endpoints (if used)
Method	Endpoint	Description
POST	/api/auth/register	Register new user
POST	/api/auth/login	Authenticate user
GET	/api/quiz	Fetch quiz data
POST	/api/quiz/submit	Submit quiz & score
GET	/api/leaderboard	Get top scores


🔮 Future Enhancements

✅ Add support for multiple quiz categories

✅ Timer-based quiz functionality

✅ REST API documentation with Swagger

✅ Deployment on Docker/Kubernetes

✅ User profile & history of attempts
