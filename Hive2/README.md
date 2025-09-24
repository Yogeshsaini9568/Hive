# 🏠 Hive - Accommodation Platform

A comprehensive web application for property management and accommodation booking built with Spring Boot and Thymeleaf.

## 🚀 Features

- **User Management**: Registration, login, profile management
- **Property Management**: Add, edit, and manage properties
- **Room Management**: Add rooms with amenities to properties
- **Image Upload**: Support for property images
- **Search Functionality**: Search properties by name
- **Responsive Design**: Mobile-friendly interface
- **Security**: Password hashing, CSRF protection, input validation

## 🛠️ Technology Stack

### Backend
- **Spring Boot 3.4.5** - Main framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database operations
- **MySQL** - Database
- **Lombok** - Code generation
- **BCrypt** - Password hashing

### Frontend
- **Thymeleaf** - Server-side templating
- **Bootstrap 5** - CSS framework
- **JavaScript** - Client-side functionality
- **Google Maps API** - Location services

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Node.js (for frontend assets if needed)

## 🚀 Quick Start

### 1. Database Setup

```sql
CREATE DATABASE hive2;
USE hive2;
```

### 2. Configuration

Update database credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the Application

#### Main Application (Port 1234)
```bash
cd Hive2
mvn spring-boot:run
```

#### RESTful Web Services (Port 2003)
```bash
cd Hive2_RESTfulWebServices
mvn spring-boot:run
```

### 4. Access the Application

- Main Application: http://localhost:1234
- REST API: http://localhost:2003

## 🔧 Configuration

### Environment Variables

Set these environment variables for production:

```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://your-db-host:3306/hive2
export SPRING_DATASOURCE_USERNAME=your_username
export SPRING_DATASOURCE_PASSWORD=your_password
export GOOGLE_MAPS_API_KEY=your_google_maps_api_key
```

### Security Configuration

The application includes:
- CSRF protection for forms
- Password hashing with BCrypt
- Session management
- Input validation
- CORS configuration

## 📁 Project Structure

```
Hive2/
├── src/main/java/com/hive/
│   ├── config/           # Configuration classes
│   ├── controllers/      # MVC controllers
│   ├── exception/        # Exception handling
│   ├── models/          # Data models
│   └── services/        # Business logic
├── src/main/resources/
│   ├── static/          # Static assets
│   ├── templates/       # Thymeleaf templates
│   └── application.properties
└── pom.xml

Hive2_RESTfulWebServices/
├── src/main/java/com/hive/
│   ├── config/          # Configuration classes
│   ├── controllers/     # REST controllers
│   ├── models/         # Entity models
│   ├── repo/           # Repository interfaces
│   └── services/       # Business logic
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

## 🔒 Security Features

### Authentication
- Password hashing with BCrypt
- Session-based authentication
- CSRF token protection

### Authorization
- Role-based access control
- Secure session management
- Input validation and sanitization

### Data Protection
- SQL injection prevention
- XSS protection
- Secure file upload handling

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Verify MySQL is running
   - Check database credentials
   - Ensure database exists

2. **Port Already in Use**
   - Change port in `application.properties`
   - Kill existing processes

3. **Google Maps Not Loading**
   - Replace API key in templates
   - Enable Maps JavaScript API

### Logs

Check application logs for detailed error information:

```bash
tail -f logs/application.log
```

## 🧪 Testing

### Unit Tests
```bash
mvn test
```

### Integration Tests
```bash
mvn verify
```

## 📦 Deployment

### Docker Deployment

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/hive-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 1234
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Production Checklist

- [ ] Update database credentials
- [ ] Set environment variables
- [ ] Configure Google Maps API key
- [ ] Enable HTTPS
- [ ] Set up monitoring
- [ ] Configure logging
- [ ] Set up backup strategy

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Contact the development team
- Check the documentation

## 🔄 Recent Updates

### v1.0.0 (Current)
- ✅ Fixed security vulnerabilities
- ✅ Added proper input validation
- ✅ Implemented service layer architecture
- ✅ Added global exception handling
- ✅ Fixed frontend form validation
- ✅ Removed hardcoded values
- ✅ Added CSRF protection
- ✅ Improved error handling
- ✅ Enhanced user experience

---

**Note**: This is a production-ready application with security best practices implemented. Make sure to update API keys and database credentials before deployment. 