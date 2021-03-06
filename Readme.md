## Spring Boot, PostgreSQL, JPA, Hibernate REST API Demo

[Spring Boot, PostgreSQL, JPA, Hibernate RESTful CRUD API Example]

**1. Configure PostgreSQL**

First, create a database named `postgres_demo`. Then, open `src/main/resources/application.properties` file and change the spring datasource username and password as per your PostgreSQL installation.

**2. Run the app**

Type the following command from the root directory of the project to run it -

```bash
mvn spring-boot:run or java -jar *.jar from the targer folder
```

**3. Run the client**
```bash
npm run start
```

**Required Software**
```
Maven
PgAdmin (Postgres)
Node js (React)
```

**4. Referred sorces**
```
https://hellokoding.com/jpa-many-to-many-extra-columns-relationship-mapping-example-with-spring-boot-hsql/
https://bezkoder.com/spring-boot-react-postgresql/
https://stackoverflow.com/questions/53961912/in-a-postgresql-database-where-a-seller-has-many-products-and-products-have-many
```

Since H2 didn't had support for inMEM postGres mode had to go with original installation
![alt text](https://github.com/Karan-Babloo/Project/blob/master/resources/Screenshot%202021-02-07%20142734.png?raw=true)
App
![alt text](https://github.com/Karan-Babloo/Project/blob/master/resources/Screenshot%202021-02-07%20143109.png)
