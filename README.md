
# BeverageStore JEE Assignment by Group 1 WS2018/19.

## Project Overview

- `backend` includes the business functionality of the BeverageStore application.
- `frontend` includes the JSP frontend to manage Customer Orders and Salesman activities like add, update and delete beverages with incentives respectively. It also includes a JSP frontend for sending orders to the JMS queue that will be persisted by the MDB of the `backend` project. Similarly JSP front end is used for sending and retrieving data to and from beverageentity to persist and retrieve beverage related information to and from `backend` project.
- `ear` creates an EAR from all the subprojects for deployment inside Glassfish.
- `shared` includes all classes/interfaces that are used by the other projects.

## Configuration

### PostgreSQL Database
1. Setup Postgres Database driver:
   Get the correct JDBC driver JAR for your PostgreSQL version from https://jdbc.postgresql.org/
   Copy `resources/postgresql-*.*.****.jar` inside `[GlassFish directory]/glassfish/domains/[domain dir]/lib/`

2. Setup of Postgres Database:
   [PostGres directory]/pgAdmin4/bin/pgAdmin4.exe (default password: admin)
   Add new login role (you have to use a password as GlassFish JDBC Resources will require one) [Name: tester, Password: tester, Privileges: can_login: true]
   Add new database [Name: BeverageStore, Owner: tester].
   
### Build project
1. Check settings in `gradle.properties`, especially the path to Glassfish and postgres DB login credentials.

2. In the project's root folder:
Run target `gradlew startGlassfish`
Run target `gradlew initServer`
Run target `gradlew build`
Run target `gradlew deploy`

Please see manual GF configuration (below) if there are problems with the `startGlassfish, initServer, deploy` tasks.

### After Deployment of BeverageStore App on Glassfish

- Go to http://localhost:8080/frontend for placing order as customer, Add or Alter Beverage as Salesman and get the BI report as Admin or owner of the application. 

## Manual Glassfish Configuration

Starting Glassfish: `[GlassFish directory]\glassfish\bin\startserv` and `[GlassFish directory]\glassfish\bin\stopserv`
GF Admin Console: `http://localhost:4848` (username: admin, default password: adminadmin)

### Setup JMS queue in GlassFish
        - Setup connection factory
              - Resources -> JMS Resources -> Connection Factories
              - Pool Name: BeverageStoreCF
              - Resource Type: javax.jms.QueueConnectionFactory
              - Configure XATransaction Support for this factory
        - Setup destination  
              - Resources -> JMS Resources -> Destination Resources
              - JNDI Name: BeverageStoreQueue
              - Physical Destination Name: PhysicalQueue
              - Resource Type: javax.jms.Queue

### Setup of JDBC Resource in GlassFish
  	- Setup a Connection Pool first:
          - Resources -> JDBC -> JDBC Connection Pools
  	  - Pool Name: BeverageStore
  	  - use javax.sql.XADataSource
  	  - use PostGres template
  	  - configure the following properties
  	  	- DatabaseName: BeverageStore
  	  	- PortNumber: 5433
  	  	- User: tester
  	  	- Password: tester
  	 - Setup a JDBC-Resource using the Connection Pool you have created:
  	   - JNDI Name: jdbc/BeverageStore

### Build and execute the demo project

- In the project's root folder run target `gradlew build`
    The project will build an Java EE enterprise archive (EAR) with all projects inside `ear/build/libs/`
- Copy the EAR inside `[GlassFish directory]\glassfish\domains\domain1\autodeploy` for autodeployment or deploy via `asadmin`

