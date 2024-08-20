# 📜 Licensing service on Spring Boot 🍃

🌐 **Ostock microservice** that manages licenses for Optima Growth's products.

🎓 **This educational project** is inspired by [ihuaylupo's microservices tutorial](https://github.com/ihuaylupo/manning-smia). However, **it is not a fork** of her repo.


## 🌟 Features

- 🔧 **Create, Read, Update, and Deactivate Licenses:** Manage the lifecycle of licenses.
- 🗂  **Modularity:** All licenses created for customers are based on the root "buyable license" and contain additional owner-specific info.


## ⚡ Quick Start

### Prerequisites
* ☕ **JDK 17** – java development kit for java 17
* 🏗️  **Maven** – build tool
* 🐙 **Git** – version control system

### Running locally

🐑 Clone the repository:

```bash
git clone https://github.com/Siiir/java-licensing-service.git
cd java-licensing-service
```

⚙️ Build and run the project:  
```bash
# Install dependencies
$ mvn install
# Run the app
$ mvn spring-boot:run
```

🎯 Run any valid http client in a separate terminal session:
```bash
# Play with an endpoint using `wget`
wget -qO- "http://localhost:8080/v1/license/shop?id=1"
```  


## 📝 Endpoints + 🛠️ User Guide for `wget` client

**Accept-Language**: All endpoints accept both English (`en`) and Polish (`pl`). Defaults to `en`.  
**Ids of buyables**: Ids of buyable licenses (as of writing) are {1, 2}.  
**Clean start**: There are no bought licenses at the start of the app. 

### 1. **🔍 View Buyable License**
- **Method**: GET
- **Endpoint**: "/v1/license/shop"
- **Usage**: 
  `wget [--method=GET] [--header="Accept-Language: {accepted_lang}"] "http://localhost:8080/v1/license/shop?id={buyable_license_id}"`  
- **Examples**:  
  `wget --method=GET "http://localhost:8080/v1/license/shop?id=1"`  
  `wget --header="Accept-Language: pl" "http://localhost:8080/v1/license/shop?id=2"`  

### 2. **🛒 Create Bought License**
- **Method**: POST
- **Endpoint**: "/v1/license"
- **Usage**: 
  `wget --method=POST [--header="Accept-Language: {accepted_lang}"] "http://localhost:8080/v1/license?base_id={buyable_license_id}&owner_id={}"`  
- **Examples**:  
  `wget --method=POST --header="Accept-Language: en" "http://localhost:8080/v1/license?base_id=2&owner_id=100"`  
  `wget --method=POST --header="Accept-Language: pl" "http://localhost:8080/v1/license?base_id=1&owner_id=101"`  

### 3. **📄 Read Bought License**
- **Method**: GET
- **Endpoint**: "/v1/license"
- **Usage**: 
  `wget [--method=GET] [--header="Accept-Language: {accepted_lang}"] "http://localhost:8080/v1/license?id={buyable_license_id}"`  
- **Examples**:  
  `wget "http://localhost:8080/v1/license?id=1"`  
  `wget --method=GET --header="Accept-Language: en" "http://localhost:8080/v1/license?id=2"`  

### 4. **📝 Update Bought License**
- **Method**: PUT
- **Endpoint**: "/v1/license"
- **Usage**: 
  `wget --method=PUT [--header="Accept-Language: {accepted_lang}"] "http://localhost:8080/v1/license?id={}&new_owner_id={}"`  
- **Examples**:  
  `wget --method=PUT --header="Accept-Language: en" "http://localhost:8080/v1/license?id=1&new_owner_id=102"`  
  `wget --method=PUT --header="Accept-Language: pl" "http://localhost:8080/v1/license?id=2&new_owner_id=103"`  

### 5. **❌ Deactivate Bought License**
- **Method**: DELETE
- **Endpoint**: "/v1/license"
- **Usage**: 
  `wget --method=DELETE [--header="Accept-Language: {accepted_lang}"] "http://localhost:8080/v1/license?id={}"`  
- **Examples**:  
  `wget --method=DELETE "http://localhost:8080/v1/license?id=1"`  
  `wget --method=DELETE "http://localhost:8080/v1/license?id=2"`  


## 🛠 Technologies Used

- ☕ **Java**: Core programming language
- 🍃 **Spring Boot**: Framework used to simplify the development of new Spring applications
- 🏗️  **Maven**: Dependency management and project build tool


## 📬 Contact

If you have any queries or suggestions, please [open an issue](https://github.com/Siiir/java-licensing-service/issues).


## 📝 License

Distributed under the MIT License. See [LICENSE](./LICENSE) for more information.
