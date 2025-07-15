# Truckers-Project
[![GitHub](https://img.shields.io/github/license/aahndeshpande/Truckers-Project)](https://github.com/aahndeshpande/Truckers-Project/blob/main/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/aahndeshpande/Truckers-Project)](https://github.com/aahndeshpande/Truckers-Project/issues)
[![GitHub contributors](https://img.shields.io/github/contributors/aahndeshpande/Truckers-Project)](https://github.com/aahndeshpande/Truckers-Project/graphs/contributors)
[![GitHub stars](https://img.shields.io/github/stars/aahndeshpande/Truckers-Project)](https://github.com/aahndeshpande/Truckers-Project/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/aahndeshpande/Truckers-Project)](https://github.com/aahndeshpande/Truckers-Project/network/members)

A full-stack web application that connects food truck owners with food enthusiasts through a community-driven platform.

## Project Overview

Truckers-Project is a modern web application that brings together food truck owners and food enthusiasts in a vibrant community. The platform allows users to:
- Discover and follow food truck communities
- Browse and join food truck events
- Review and rate food truck experiences
- Connect with fellow food enthusiasts

## Features

### Core Features
- User Authentication (Admin, Owner, User roles)
- Food Truck Management
- Community System
- Event Management
- Review System
- Rating System

### Technical Features
- Full-stack architecture (Spring Boot + React)
- RESTful API design
- JWT-based authentication
- Real-time updates
- Responsive UI
- Secure data handling

## Tech Stack

### Backend (Spring Boot)
- Java 17
- Spring Boot 3.2.5
- Spring Security
- Spring Data JPA
- H2 Database (Development)
- AWS SDK (for future production)
- Lombok
- Flyway (Database migrations)

### Frontend (React)
- React 18
- TypeScript
- Material-UI
- Redux Toolkit
- Axios
- Formik
- Yup
- React Router v6

## Project Structure

```
Truckers-Project/
├── backend/                # Spring Boot backend
│   ├── src/main/java/     # Java source code
│   │   ├── com/aditya/trucker/
│   │   │   ├── config/   # Configuration classes
│   │   │   ├── controller/ # REST controllers
│   │   │   ├── model/    # Domain models
│   │   │   ├── repository/ # Database repositories
│   │   │   └── service/   # Business services
│   │   └── resources/     # Application resources
│   └── pom.xml            # Maven configuration
├── frontend/              # React frontend
│   ├── src/              # Source code
│   │   ├── api/          # API client
│   │   ├── components/   # Reusable components
│   │   ├── pages/        # Page components
│   │   └── store/        # Redux store
│   └── package.json      # NPM dependencies
└── README.md             # Project documentation
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6.3 or higher
- Node.js 16 or higher
- npm 7 or higher

### Installation

1. Clone the repository:
```bash
git clone [repository-url]
cd Truckers-Project
```

2. Install backend dependencies:
```bash
cd backend
mvn clean install
```

3. Install frontend dependencies:
```bash
cd frontend
npm install
```

### Running the Application

1. Start the backend server:
```bash
cd backend
mvn spring-boot:run
```

2. In a new terminal, start the frontend:
```bash
cd frontend
npm start
```

The application will be available at:
- Backend: http://localhost:8080
- Frontend: http://localhost:3000

### H2 Database Console
Access the H2 database console at:
http://localhost:8080/h2-console

Default credentials:
- JDBC URL: jdbc:h2:mem:truckerdb
- Username: sa
- Password: (empty)

## Default User Credentials

- Admin:
  - Username: admin
  - Password: admin123

- Owner:
  - Username: owner1
  - Password: owner123

- Regular User:
  - Username: user1
  - Password: user123

## API Documentation

### Authentication
- POST /api/auth/login - Login user
- POST /api/auth/register - Register new user
- GET /api/auth/me - Get current user info

### Food Trucks
- GET /api/food-trucks - List all food trucks
- POST /api/food-trucks - Create new food truck
- GET /api/food-trucks/{id} - Get food truck details
- PUT /api/food-trucks/{id} - Update food truck
- DELETE /api/food-trucks/{id} - Delete food truck

### Communities
- GET /api/communities - List all communities
- POST /api/communities - Create new community
- GET /api/communities/{id} - Get community details
- PUT /api/communities/{id} - Update community
- DELETE /api/communities/{id} - Delete community

### Events
- GET /api/events - List all events
- POST /api/events - Create new event
- GET /api/events/{id} - Get event details
- PUT /api/events/{id} - Update event
- DELETE /api/events/{id} - Delete event

## Development

### Backend Development
- Code organization follows the standard Spring Boot structure
- Services handle business logic
- Controllers handle HTTP requests
- Repositories handle data access
- Models represent domain entities

### Frontend Development
- Components are organized by feature
- State management using Redux Toolkit
- API calls through Axios client
- Form handling with Formik and Yup
- Routing with React Router

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Spring Boot team for the excellent framework
- React community for the amazing ecosystem
- Material-UI team for the beautiful UI components
- All contributors who helped with this project