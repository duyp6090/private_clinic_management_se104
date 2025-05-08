## 📝 Business Analysis

**Project:** Private Clinical Hospital Management System  
**Tech Stack:** Java Spring Boot, MySQL, Docker Compose  

### 🎯 Objectives
- Centralize and streamline patient, examination and reporting workflows.  
- Enforce Role-Based Access Control (RBAC) for Admin, Doctor & Staff.  
- Generate timely monthly performance and utilization reports.  

### 👥 Stakeholders
- **Hospital Administrator:**  
  - Oversees system configuration, user management, high-level metrics.  
- **Doctors:**  
  - Create and update patient examination records, review historical data.  
- **Staff (Nurses / Reception):**  
  - Register new patients, schedule appointments, manage basic demographics.  

### 🔑 Key Features & User Stories
1. **RBAC**  
   - As an **Admin**, I can create/assign roles and manage users to ensure data security.  
   - As a **Doctor**, I can only access my patients’ records and write examination notes.
   - As a **Staff**, I can only access help in receptionist and money bill process  
2. **Patient Management**  
   - As Staff, I can register new patients, update contact info, and look up existing profiles.  
3. **Examination Record Management**  
   - As a Doctor, I can log vitals, diagnosis, prescriptions, and follow-up recommendations.  
4. **Monthly Report Generation**  
   - As an Admin, I can generate PDF/CSV summaries of patient visits, revenue, and bed occupancy.  

---

## 📁 Project Structure

```bash
hospital-management/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── configuration/      # Spring Boot & security configurations
│   │   │   ├── controller/         # REST controllers
│   │   │   ├── domain/             # JPA entities (Patient, ExamRecord, User, Role)
│   │   │   ├── dto/                # Data Transfer Objects
│   │   │   ├── exception/          # Custom exception handlers
│   │   │   ├── repository/         # Spring Data JPA repositories
│   │   │   ├── security/           # JWT filters, user details service
│   │   │   ├── service/            # Business logic services
│   │   │   └── util/               # Utility classes (PDF report generator, mappers)
│   │   │   └── validator/               # handle validate
│   │   └── resources/
│   │   │   ├── application.yml     # Spring Boot & DB properties
│   │   │   └── sql/                # Initial schema & seed data scripts
│   │   └── DemoApplication.java
│   └── test/
│       └── java/com/clinic/        # Unit & integration tests
│
├── docker-compose.yml              # MySQL, Redis (optional), App service definitions
├── Dockerfile                      # Java build & runtime image
├── .env                            # Environment variables for compose
├── .gitignore
├── README.md                       # This file
└── pom.xml                         # Maven project definition

## 🚀 Installation & Local Setup

### 1. 📦 Clone the Repository

```bash
git clone https://github.com/your-username/hospital-management.git

```

```
cd hospital-management

```
### 2. Docker set up
Docker compose up -d

### 3. Run the project
## ⚙️ Usage

### Authentication

All endpoints (except `/auth/login` and `/auth/register`) require a valid JWT in the `Authorization: Bearer <token>` header.

### Key Endpoints

| Resource                  | Method | Endpoint                                              | Roles          | Description                                 |
|---------------------------|--------|-------------------------------------------------------|----------------|---------------------------------------------|
| **Users / RBAC**          | GET    | `/api/admin`                                          | Admin          | List all users & roles                     |
|                           | POST   | `/api/admin/register-doctor`                          | Admin          | Create a new doctor                         |
|                           | POST   | `/api/admin/register-supporter`                       | Admin          | Create a new doctor                         |
| **Patients**              | GET    | `/api/patients`                                       | Admin, Staff   | List all patients                           |
|                           | POST   | `/api/patients`                                       | Staff          | Create a new patient record                 |
|                           | PUT    | `/api/patients/{patientId}`                           | Staff          | Update patient details                      |
| **Examinations**          | GET    | `/api/patients/{patientId}/exams`                     | Admin, Doctor  | List exam records for a patient             |
|                           | POST   | `/api/patients/{patientId}/exams`                     | Doctor         | Add a new examination record                |
| **Monthly Reports**       | GET    | `/api/reports/monthly?year=YYYY&month=MM`             | Admin          | Generate/download monthly summary report    |

---
## 📫 Contact

For issues or feature requests, please open an issue on GitHub or reach out to the maintainer:

- GitHub: [Private hotel management](https://github.com/duyp6090/private_clinic_management_se104)  
- Email Loc Nguyen: `locnvt.it@gmail.com`
- Email Duy Pham: `22520341@gm.uit.edu.vn`

