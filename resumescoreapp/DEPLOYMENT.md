# Deployment Guide: Spring Boot (Hibernate) + MySQL

Based on the `resumescoreapp` codebase, use this updated guide.

## ⚠️ Critical Local Fixes Required
Before deploying, you **MUST** apply these changes to your project locally:

1.  **Missing Frontend Files**:
    *   Create the folder: `src/main/resources/static/`
    *   Move/Copy your `index.html` and `logic.js` into this folder.
    *   *Current Status: These files are missing in your project!*

2.  **`pom.xml` Updates** (Enable `java -jar`):
    *   Change packaging to `jar`:
        ```xml
        <packaging>jar</packaging>
        ```
    *   Remove the `provided` scope from Tomcat (so it works standalone):
        ```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <!-- <scope>provided</scope> Remove or comment this line -->
        </dependency>
        ```

3.  **`application.properties` Tweak**:
    *   Change `ddl-auto` to `update` to prevent data loss on restarts:
        ```properties
        spring.jpa.hibernate.ddl-auto=update
        ```

---

## 🚀 Step-by-Step Deployment

### 1. Server Prerequisites
Run on your EC2/Hetzner terminal:
```bash
sudo apt update && sudo apt upgrade -y
sudo apt install openjdk-17-jdk mysql-server git -y
```

### 2. Database Preparation
*Note: Your code uses `resumescoreappdb`, not `authdb_jdbc`.*

```bash
sudo mysql
```

Inside MySQL:
```sql
-- Set root password to match application.properties
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'amit123456';

-- Create the specific database for this app
CREATE DATABASE resumescoreappdb;

FLUSH PRIVILEGES;
EXIT;
```

### 3. Clone & Build
```bash
# Clone the repo (replace URL with your actual new repo if changed)
git clone https://github.com/yashkumar84/FullStack.git 
cd <RepoName>/resumescoreapp

# Ensure mvnw is executable
chmod +x mvnw

# Build the JAR
./mvnw clean package -DskipTests
```

### 4. Run the Application
Start the app in the background. Note the new artifact name `resumescoreapp`.

```bash
nohup java -jar target/resumescoreapp-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

*Hibernate will automatically create the `ResumeCheck` table on first start.*

### 5. Verification
Visit: `http://<YOUR_IP>:8080/index.html`

*Ensure Port 8080 is open in your security group/firewall.*
