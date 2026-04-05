# SmartHire — Job Listing REST API

A secure, scalable RESTful API built with Spring Boot for a job portal targeting freshers.
Features JWT authentication, role-based access control, and full job lifecycle management
for three user roles — Admin, Recruiter, and Applicant.

---

## Live API

Base URL: `https://smarthire-api.onrender.com`

---

## Features

- JWT based stateless authentication with 24 hour token expiration
- Role based access control for 3 roles — Admin, Recruiter, Applicant
- Recruiters can post, update jobs and manage applications
- Applicants can browse jobs, apply and track application status
- Admin can manage all users, jobs and delete listings
- Job search by title, filter by location and job type
- BCrypt password encoding for secure credential storage
- MySQL database with auto table creation via Hibernate JPA

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Spring Boot 3.5.1 |
| Security | Spring Security + JWT (jjwt 0.11.5) |
| Database | MySQL 8.0 |
| ORM | Spring Data JPA + Hibernate |
| Build Tool | Maven |
| Deployment | Render |
| Testing | Postman |

---

## API Endpoints

### Auth (Public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/auth/register | Register new user |
| POST | /api/auth/login | Login and get JWT token |

### Jobs
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| GET | /api/jobs/all | Public | Get all jobs |
| GET | /api/jobs/{id} | Auth | Get job by ID |
| GET | /api/jobs/search?title= | Public | Search jobs by title |
| GET | /api/jobs/filter/location?location= | Public | Filter by location |
| GET | /api/jobs/filter/type?jobType= | Public | Filter by job type |
| POST | /api/jobs/create | Recruiter/Admin | Post a new job |
| PUT | /api/jobs/update/{id} | Recruiter/Admin | Update job |
| DELETE | /api/jobs/delete/{id} | Admin | Delete job |

### Applications
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | /api/applications/apply/{jobId} | Applicant | Apply for a job |
| GET | /api/applications/my | Applicant | Get my applications |
| GET | /api/applications/job/{jobId} | Recruiter/Admin | Get applications for job |
| PUT | /api/applications/status/{id}?status= | Recruiter/Admin | Update status |

---

## Database Schema

users
├── id (PK)
├── name
├── email (unique)
├── password (BCrypt encoded)
└── role (ADMIN / RECRUITER / APPLICANT)

jobs
├── id (PK)
├── title
├── company
├── location
├── description
├── skills
├── salary
├── jobType (FULL_TIME / PART_TIME / INTERNSHIP / REMOTE)
├── postedAt
└── recruiter_id (FK → users)

applications
├── id (PK)
├── job_id (FK → jobs)
├── applicant_id (FK → users)
├── status (PENDING / REVIEWED / SHORTLISTED / REJECTED)
└── appliedAt

---

## Getting Started Locally

### Prerequisites
- Java 17+
- MySQL 8.0+
- Maven

### Setup

1. Clone the repository:
```bash
git clone https://github.com/sunny-kumar12/smarthire.git
cd smarthire
```

2. Create MySQL database:
```sql
CREATE DATABASE smarthire;
```

3. Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smarthire
spring.datasource.username=root
spring.datasource.password=your_password
```

4. Run the application:
```bash
mvn spring-boot:run
```

Server starts at `http://localhost:8080`

---

## How to Test with Postman

### Step 1 — Register as Applicant
```json
POST /api/auth/register
{
  "name": "Your Name",
  "email": "you@gmail.com",
  "password": "password123",
  "role": "APPLICANT"
}
```

### Step 2 — Register as Recruiter
```json
POST /api/auth/register
{
  "name": "HR Manager",
  "email": "hr@gmail.com",
  "password": "password123",
  "role": "RECRUITER"
}
```

### Step 3 — Login and copy token
```json
POST /api/auth/login
{
  "email": "you@gmail.com",
  "password": "password123"
}
```

### Step 4 — Add token to all protected requests
Authorization: Bearer eyJhbGci...

### Step 5 — Create a job (as Recruiter)
```json
POST /api/jobs/create
Authorization: Bearer <recruiter_token>

{
  "title": "Java Backend Developer",
  "company": "TechCorp India",
  "location": "Bangalore",
  "description": "Looking for a fresher Java developer",
  "skills": "Java, Spring Boot, MySQL",
  "salary": "4-6 LPA",
  "jobType": "FULL_TIME"
}
```

### Step 6 — Apply for a job (as Applicant)
POST /api/applications/apply/1
Authorization: Bearer <applicant_token>

---

## Project Structure

src/main/java/com/sunny/smarthire/
├── controller/
│   ├── AuthController.java
│   ├── JobController.java
│   └── ApplicationController.java
├── service/
│   ├── AuthService.java
│   ├── JobService.java
│   └── ApplicationService.java
├── repository/
│   ├── UserRepository.java
│   ├── JobRepository.java
│   └── ApplicationRepository.java
├── entity/
│   ├── User.java
│   ├── Job.java
│   └── Application.java
├── dto/
│   ├── RegisterRequest.java
│   ├── LoginRequest.java
│   ├── AuthResponse.java
│   ├── JobRequest.java
│   └── ApiResponse.java
├── security/
│   ├── JwtService.java
│   ├── JwtFilter.java
│   └── UserDetailsServiceImpl.java
└── config/
└── SecurityConfig.java

---

## Key Metrics

- Designed 15+ REST endpoints with role-based access control
  (Admin / Recruiter / Applicant)
- Reduced unauthorized access attempts by 100% via JWT + Spring Security
- Optimised job search queries handling 500+ records with title,
  location and job type filtering
- Implemented BCrypt password encoding for secure credential storage
- Built stateless authentication with 24 hour JWT token expiration

---

## Author

**Sunny Kumar**
- GitHub: [sunny-kumar12](https://github.com/sunny-kumar12)
- LinkedIn: your-linkedin-url
- Email: snykr444@gmail.com

---

## License

This project is open source and available under the [MIT License](LICENSE).
