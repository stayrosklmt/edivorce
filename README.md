# E-Divorce

Backend for E-Divorce, it is a Spring Boot REST API, using Spring Security.

## Run Locally

First make sure that you have installed PostgreSQL, if you don't click [here](https://www.postgresql.org/download/).
Then create a user in PostgreSQL with:  
```bash
username: divorce  
password: divorce
```
Or just change username & password with your credentials in: "./edivorce/src/main/resources/application.yml"

Create database with name "edivorce".

Then clone the project

```bash
git clone https://link-to-project
```

Go to the project directory

```bash
cd my-project
```

Build JAR

```bash
mvn clean install
```

Go to target folder and start the server

```bash
java -jar edivorce-0.0.1-SNAPSHOT.jar
```

In othen case you can clone the project and run it your favorite IDE.
## Documentation

When server starts initialize six users and you can find theis credentials on src/main/java/com/divorce/edivorce/initUser.java all users after log in take a jwt token, with this tehy are authorized on all endpoints.

All the available end-points:
- /api/v1/auth/authenticate [POST]
- /api/v1/auth/reset-password [POST]
- /api/v1/admin [GET]
- /api/v1/admin/{id} [GET]
- /api/v1/admin [POST]
- /api/v1/admin/{id} [PUT]
- /api/v1/admin/{id} [DELETE]
- /api/v1/lawyer [POST]
- /api/v1/notary [GET]
- /api/v1/notary [PUT]
- /api/v1/husband/{id} [GET]
- /api/v1/husband [PUT]

On every path to have access you must have the correct Role:
- ADMIN 
- LAWYER 
- NOTARY 
- USER