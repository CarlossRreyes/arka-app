# ARKA APP
The following was discovered as part of building this project:

* The original package name 'com.arka.app-services' is invalid and this project uses 'com.arka.app_services' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.4/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.4/maven-plugin/build-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#using.devtools)
* [Validation](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#io.validation)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.


### Dev
Clone this repository:
````
git remote add origin https://github.com/CarlossRreyes/arka-app.git
````

Run application:
````
mvn spring-boot:run
````

Execute seed ( Insert user por default )
````
http://localhost:4041/api/administration/seed/client
````
Execute store procedure in database gestore
````
src/main/resources/sql/operations.sql
````
Execute this command and up volume
````
docker-compose up -d
````

## El backend lo pueden obtener de aquí

[Teslo Backend - Nest RestServer](https://hub.docker.com/repository/docker/klerith/flutter-backend-teslo-shop/general)

# Importante
Recuerden leer y seguir la guía para montar el backend localmente.