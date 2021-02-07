## Spring Boot, PostgreSQL, JPA, Hibernate REST API Demo

[Spring Boot, PostgreSQL, JPA, Hibernate RESTful CRUD API Example]

**2. Configure PostgreSQL**

First, create a database named `postgres_demo`. Then, open `src/main/resources/application.properties` file and change the spring datasource username and password as per your PostgreSQL installation.

**3. Run the app**

Type the following command from the root directory of the project to run it -

```bash
mvn spring-boot:run
```

Alternatively, you can package the application in the form of a JAR file and then run it like so -

```bash
mvn clean package
```


**3. Referred sorces**
```bash
https://stackoverflow.com/questions/53961912/in-a-postgresql-database-where-a-seller-has-many-products-and-products-have-many
https://hellokoding.com/jpa-many-to-many-extra-columns-relationship-mapping-example-with-spring-boot-hsql/
https://bezkoder.com/spring-boot-react-postgresql/
```
