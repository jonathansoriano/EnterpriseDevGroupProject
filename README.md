# Enterprise Dev Group Project - Design Document

## 1. Introduction

The application created in this problem is a University Student Directory program intended for higher learning institutions within the tri-state region. These include institutions such as Xavier University, the University of Cincinnati (UC), as well as Cincinnati State. The program solves the problem of the lack of centralization of information concerning student enrollment within the institutions within the specified regions

- Users: University administrators and students within the tri-state network.
- Main Purpose: To maintain a searchable record of students’ first and last names, their associated IDs, and their respective universities
---

## 2. Storyboard
The project uses GitHub Projects to maintain a storyboard. Each pull request will address the specific feature by incorporating it from the storyboard, ensuring it aligns with the overall project goals and is subject to change as the feature developments.

---


## 3. Functional Requirements

### Requirement 1: Student Search

**User Story:**
- **As a** student/administrator
- **I want** to be able to search for other students in the tri-state area
- **So that I can** connect with them via email

**Acceptance Criteria:**

#### Scenario 1: Successful Search
- **Given** that I've logged in
- **When** I search for students by a particular field (e.g., Major, First Name, etc.)
- **Then** I should see students populate on the page

#### Scenario 2: Unsuccessful Search
- **Given** that I've logged in
- **When** I search for students by a particular field (e.g., Major, First Name, etc.) that doesn't exist in the database
- **Then** I should see a message telling me no students were found

### Requirement 2: Easy Navigation

**User Story:**
- **As a** student/administrator
- **I want** to be able to easily navigate through the web application
- **So that I** know where to go for certain functions (Searching, Updating Profile)

**Acceptance Criteria:**

#### Scenario 1: Finding Profile Update Option
- **Given** that I'm logged in and I'm looking to update my profile
- **When** I look for where I can edit/update my profile
- **Then** I should easily find the tab/option to update my profile

### Requirement 3: Partial Search Capability

**User Story:**
- **As a** student/administrator user
- **I want** to be able to do partial searches on students based on particular fields
- **So that** if I don't have specific information on a student I want to look up, I can search through the possible matches

**Acceptance Criteria:**

#### Scenario 1: Partial Search with Multiple Criteria
- **Given** that I'm logged in and in the searching tab/option
- **When** I try to search for students whose names start with the letter 'A' and attend Xavier University
- **Then** I should see students that fit this description on the page

#### Scenario 2: Partial Search with No Matches
- **Given** that I'm logged in and in the searching tab/option
- **When** I try to search for students whose names start with the letter 'Z' and attend St. Mary's University
- **Then** I should see a message telling me no students were found

### Requirement 4: Complete Profile

**User Story:**
- **As a** student/administrator
- **I want** to be able to complete my profile
- **So that** people are able to reach me and see my university-related information

**Acceptance Criteria:**

#### Scenario 1: Account Creation with Field Requirements
- **Given** I don't have an account yet
- **When** I try creating one with all required fields filled in
- **Then** I should my account created successfully

#### Scenario 2: Attempting to submit with missing required fields
- **Given** I don't have an account yet
- **When** I try creating one with missing required fields
- **Then** I should get a message telling me to fill in the required fields to create my account

### Requirement 5: Input Validation

**User Story:**
- **As a** student/administrator
- **I want** to be able to know if my search input is valid input prior to searching
- **So that I'm** able to get results back

**Acceptance Criteria:**

#### Scenario 2: Valid Input
- **Given** that I'm logged in and in the searching tab/option
- **When** I try to search for students with alphanumeric characters
- **Then** I should be able to proceed with my search submission

#### Scenario 2: Invalid Input
- **Given** that I'm logged in and in the searching tab/option
- **When** I try to search for students but type a non-alphanumeric character in a field
- **Then** I should get a warning that my input is invalid and needs to be corrected before submission

---

## 4. Class Diagram

[Insert your UML class diagram here - you can use tools like:]
- draw.io
- Lucidchart
- PlantUML
- Visual Paradigm

```
[Insert image or PlantUML code here]
```

### Class Diagram Description

- **User**: Represents application users with authentication credentials and profile information. Implements UserDetails interface for Spring Security.
- **Task**: Main entity representing a work item with title, description, status, priority, and due date. Associated with User (assignee).
- **Project**: Groups related tasks together. Contains multiple tasks and team members.
- **TaskRepository**: JPA repository interface for CRUD operations on Task entities. Extends JpaRepository.
- **UserRepository**: JPA repository interface for user data access. Provides custom query methods for finding users by email and username.
- **TaskService**: Business logic layer for task management. Handles task creation, assignment, and status updates.
- **TaskController**: REST controller exposing task-related endpoints. Maps HTTP requests to service methods.
- **TaskDTO**: Data Transfer Object for task information sent to/from the API. Separates internal model from API representation.



- [ Student: Represents the core entity with attributes for firstName, lastName, studentID, and universityName.
  • University: Represents the tri-state institutions (e.g., Xavier, UC).
  • StudentRepository: JPA repository interface for CRUD operations on student records.
  • StudentService: Business logic layer handling directory searches and data validation.
  • StudentController: REST controller managing JSON-based API requests for student data]
---

## 5. JSON Schema


---

## 6. Scrum Roles

- **Scrum Master/Product Owner/GitHub admin**: Ian Kellenberger & Dharmin Patel - Responsible for defining features and prioritizing backlog
- **UI/UX**: Matthew Brown - Facilitates scrum ceremonies and removes impediments
- **Backend Developer**: Jonathan Soriano 


---

## 7. GitHub Repository

**Repository Link**: ```https://github.com/jonathansoriano/EnterpriseDevGroupProject```

---

## 8. Project Board & Milestones

**Project Board Link**: [https://github.com/YOUR-USERNAME/enterprisedevgroupproject/projects/1](https://github.com/YOUR-USERNAME/enterprisedevgroupproject/projects/1)


---

## 9. Weekly Standup Meeting

**Meeting Time**: Every Monday at 05:00PM EST  
**Platform**: Microsoft Teams  
**Meeting Link**: ``` https://teams.microsoft.com/meet/2727813537852?p=8FKd43Pa4OstTky9WE```

**Meeting Agenda**:
- What did you accomplish this week?
- What are you working on next?
- Are there any blockers or issues?

---

## Technology Stack

- **Backend**: Spring Boot, Spring Data JPA
- **Database**: PostgreSQL / H2 (for development)
- **Frontend**: Thymeleaf
- **Testing**: JUnit, Mockito, Spring Test
- **Build Tool**: Maven
- **Version Control**: Git/GitHub
- **CI/CD**: GitHub Actions
- **Deployment**: [AWS / Heroku / Azure / Other]

---

## Development Guidelines
- All methods must include JavaDocs to explain their functionality. 
- Pull Requests must document major breaking changes and confirm unit testing. 
- A README markdown file will be maintained to track test results and collective progress

### Branching Strategy
- `main` - Production-ready code
- `feature/*` - Individual feature branches
- `bugfix/*` - Bug fix branches

---

## Helps Links

- [JDK Setup and Installation]()
