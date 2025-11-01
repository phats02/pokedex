# Pokedex API

A RESTful API application for querying Pokemon data built with Spring Boot and MySQL. The application provides endpoints to retrieve all Pokemon or search for specific Pokemon by name with pagination support.

## 🚀 Features

- **Get All Pokemon**: Retrieve all Pokemon from the database
- **Search Pokemon**: Search Pokemon by name with pagination support
- **Swagger Documentation**: Interactive API documentation via Swagger UI
- **CSV Data Loading**: Automatically loads Pokemon data from CSV on application startup
- **Error Handling**: Comprehensive error handling with meaningful error messages

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

- **Java 25** (or compatible version)
- **Maven 3.6+**
- **MySQL 8.0+** (or compatible version) **OR Docker** (for containerized MySQL)
- **Git** (optional, for cloning the repository)

## 🐳 Database Setup with Docker (Recommended)

The easiest way to set up MySQL is using Docker Compose. A `docker-compose.yml` file is provided in the project root.

### Start MySQL Container

```bash
# Start MySQL container in detached mode
docker-compose up -d

# Verify the container is running
docker ps
```

### Stop MySQL Container

```bash
# Stop the MySQL container
docker-compose down

# Stop and remove volumes (⚠️ This will delete all data)
docker-compose down -v
```

### Docker Compose File

The `docker-compose.yml` file configures:
- **MySQL 8.0** image
- **Container name**: `pokedex-mysql`
- **Port**: `3306` (mapped to host)
- **Root password**: `admin` (matches application.yaml)
- **Database**: `pokedex_db` (auto-created)
- **Persistent volume**: Data persists across container restarts

The database will be automatically created when the container starts, so no manual database creation is needed.

## 🗄️ Database Setup (Manual Installation)

1. **Start MySQL Server**: Make sure your MySQL server is running

2. **Create Database** (Optional): The application will automatically create the database `pokedex_db` if it doesn't exist. However, you can create it manually:

   ```sql
   CREATE DATABASE IF NOT EXISTS pokedex_db;
   ```

3. **Database Configuration**: Update the database credentials in `src/main/resources/application.yaml` if needed:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/pokedex_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
       username: root
       password: admin  # Change this to your MySQL password
   ```

## 🏃 Running the Application

> **Note**: If you're using Docker for MySQL, make sure the MySQL container is running first:
> ```bash
> docker-compose up -d
> ```

### Option 1: Using Maven Wrapper (Recommended)

```bash
# For Unix/macOS/Linux
./mvnw spring-boot:run

# For Windows
mvnw.cmd spring-boot:run
```

### Option 2: Using Maven (if installed globally)

```bash
mvn spring-boot:run
```

### Option 3: Build and Run JAR

```bash
# Build the project
mvn clean package

# Run the JAR file
java -jar target/Pokedex-0.0.1-SNAPSHOT.jar
```

## 📡 API Endpoints

### Get All Pokemon
- **URL**: `/all`
- **Method**: `GET`
- **Description**: Retrieves all Pokemon from the database
- **Response**: List of all Pokemon

**Example Request:**
```bash
curl http://localhost:8080/all
```

### Search Pokemon
- **URL**: `/search`
- **Method**: `GET`
- **Description**: Search Pokemon by name with pagination
- **Parameters**:
  - `name` (required): Pokemon name to search for
  - `page` (optional, default: 0): Page number (0-indexed)
  - `pageSize` (optional, default: 10, max: 100): Number of results per page

**Example Request:**
```bash
curl "http://localhost:8080/search?name=Pikachu&page=0&pageSize=10"
```

## 📚 Swagger Documentation

Once the application is running, you can access the interactive API documentation using Swagger UI:

### Swagger UI
**URL**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Swagger UI provides an interactive interface where you can:
- View all available API endpoints
- See request/response schemas
- Test API endpoints directly from the browser
- View detailed parameter descriptions and validation rules

### API Docs (JSON)
**URL**: [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

## 🛠️ Technologies Used

- **Spring Boot 3.5.6**: Application framework
- **Spring Data JPA**: Database access and ORM
- **MySQL**: Relational database
- **Lombok**: Reduces boilerplate code
- **OpenCSV**: CSV file processing
- **SpringDoc OpenAPI**: Swagger/OpenAPI documentation
- **Spring Validation**: Request validation
- **Maven**: Build and dependency management

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/ferb/Pokedex/
│   │   ├── controller/     # REST controllers
│   │   ├── service/        # Business logic
│   │   ├── repository/     # Data access layer
│   │   ├── entity/         # JPA entities
│   │   ├── dto/            # Data transfer objects
│   │   └── exception/      # Exception handlers
│   └── resources/
│       ├── data/           # CSV data files
│       └── application.yaml # Configuration
└── test/                   # Test files
```

## 🔧 Configuration

Main configuration is located in `src/main/resources/application.yaml`:

- **Server Port**: 8080 (default)
- **Database**: MySQL (pokedex_db)
- **JPA**: Auto-update schema on startup
- **Swagger**: Enabled with custom paths

## 📝 Notes

- The application automatically loads Pokemon data from `src/main/resources/data/PokemonGOData.csv` on startup
- The database schema is automatically created/updated by JPA on application startup
- API responses include pagination metadata for search endpoints

## 🐛 Troubleshooting

### Port Already in Use
If port 8080 is already in use, you can change it in `application.yaml`:
```yaml
server:
  port: 8081  # or any other available port
```

### Database Connection Issues
- Ensure MySQL server is running
- Verify database credentials in `application.yaml`
- Check if MySQL is accessible on `localhost:3306`

### Data Not Loading
- Ensure `PokemonGOData.csv` exists in `src/main/resources/data/`
- Check application logs for any CSV parsing errors

## 📄 License

This project is part of a learning exercise.
