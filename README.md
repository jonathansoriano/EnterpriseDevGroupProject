# Enterprise Dev Group Project - Design Document

## 1. Introduction

[Brief description of your application - what problem does it solve? Who are the users? What is the main purpose?]

Example: "This application is a task management system designed for software development teams. It allows team members to create, assign, and track tasks throughout the development lifecycle. The system will provide real-time collaboration features and integrate with existing development tools."

---

## 2. Storyboard



---

## 3. Functional Requirements


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

---

## 5. JSON Schema


---

## 6. Scrum Roles

- **Scrum Master/Product Owner/GitHub admin**: [Name] - Responsible for defining features and prioritizing backlog
- **UI/UX**: [Name] - Facilitates scrum ceremonies and removes impediments
- **Backend Developer**: [Name] - 


---

## 7. GitHub Repository

**Repository Link**: https://github.com/jonathansoriano/EnterpriseDevGroupProject

---

## 8. Project Board & Milestones

**Project Board Link**: [https://github.com/YOUR-USERNAME/enterprisedevgroupproject/projects/1](https://github.com/YOUR-USERNAME/enterprisedevgroupproject/projects/1)


---

## 9. Weekly Standup Meeting

**Meeting Time**:  
**Platform**: Microsoft Teams  
**Meeting Link**: [Teams link here, or email to instructor if repository is public]

**Agenda:**
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

### Branching Strategy
- `main` - Production-ready code
- `feature/*` - Individual feature branches
- `bugfix/*` - Bug fix branches

---

## Helps Links

- [JDK Setup and Installation]()
