## 📝 Business Analysis

**Project:** Private Clinical Hospital Management System  
**Tech Stack:** Java Spring Boot, MySQL, Docker Compose  
![Private Clinic Management](media/clinic.jpeg)

### 🎯 Objectives
- Centralize and streamline patient, examination, and reporting workflows.
- Enforce Role-Based Access Control (RBAC) for Admin, Doctor, and Staff.
- Automate and simplify the generation of monthly performance and utilization reports.
- Ensure data security, privacy, and compliance with healthcare regulations (e.g., HIPAA).
- Provide a user-friendly interface for managing patient information and healthcare operations.

### 👥 Stakeholders
- **Hospital Administrator:**  
  - Responsible for overseeing the entire system's configuration, user management, and high-level operational metrics.
  - Can view and manage hospital-wide data and generate reports.
  - Manages user permissions and can assign roles to ensure the right level of access for each role (Admin, Doctor, Staff).

- **Doctors:**  
  - Can access and manage the medical records of their assigned patients.
  - Document examinations, diagnose conditions, prescribe treatments, and track patient progress over time.
  - Review historical data to ensure continuity of care for patients.

- **Staff (Nurses / Receptionists):**  
  - Handles patient registration, scheduling appointments, and updating basic patient demographic information.
  - Manages the creation of invoices and payment processing.
  - Ensures patient records are updated and accurate.

- **Patients:**  
  - Not a direct user of the system, but their data is the central point for all hospital workflows.
  - Will benefit from accurate records, timely care, and the ability to schedule and track appointments.

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
```
## 🚀 Installation & Local Setup

### 1. 📦 Clone the Repository

```bash
git clone https://github.com/duyp6090/private_clinic_management_se104.git

```

```
cd private_clinic_management_se104

```
### 2. Docker set up
```
docker-compose up -d

```

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
- Email Duy Pham: `duyp6090@gmail.com`

