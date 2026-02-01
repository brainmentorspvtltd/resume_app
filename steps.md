# Master Deployment Guide: Spring Boot + MySQL

This guide covers all changes made to the project and the final steps to deploy on EC2 or Hetzner.

## 📁 Files Modified locally
Before deploying, ensure these changes are committed and pushed:

1.  **`springmvcjdbc/pom.xml`**:
    - Changed `<packaging>` from `war` to `jar`.
    - Enabled embedded Tomcat (removed `<scope>provided`).
2.  **`springmvcjdbc/src/main/resources/static/`**:
    - [NEW] Moved `index.html` and `logic.js` here from the old `front-end` folder.
3.  **`springmvcjdbc/src/main/resources/static/logic.js`**:
    - Updated `url` to `/register` (relative path).
4.  **`springmvcjdbc/src/main/resources/schema.sql`**:
    - [NEW] Added for automatic table creation on startup.
5.  **`springmvcjdbc/src/main/resources/application.properties`**:
    - Verified DB credentials match the server setup (`root` / `amit123456`).

---

## 🚀 Step-by-Step Deployment (Last to First)

### 1. Server Prerequisites
Run on your EC2/Hetzner terminal:
```bash
sudo apt update && sudo apt upgrade -y
sudo apt install openjdk-17-jdk mysql-server git -y
```

### 2. Database Preparation
Configure the root user to match your code's password:
```bash
sudo mysql
```
Inside MySQL:
```sql
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'amit123456';
CREATE DATABASE authdb_jdbc;
FLUSH PRIVILEGES;
EXIT;
```

### 3. Clone & Build
```bash
git clone https://github.com/yashkumar84/FullStack.git
cd FullStack/springmvcjdbc
chmod +x mvnw
./mvnw clean package -DskipTests
```

### 4. Run the Application
Start the app in the background (so it stays running after you close terminal):
```bash
nohup java -jar target/springmvcjdbc-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```
*The tables will be created automatically via `schema.sql` on first start.*

### 5. Open AWS/Cloud Firewall
**CRITICAL**: You must allow traffic on **Port 8080** in your Cloud Security Group (Inbound Rules).

### 6. Verification
Visit: `http://<YOUR_IP>:8080/index.html`
