# hotel-management-system
Hotel Management Service

## How to build
Setup java 8 and Maven, see [environment_setup.md](environment_setup.md)

### build project
  mvn clean install

### Servers setup
  you need to setup 2 local Tomcat servers with 8080 and 8002, you can do it with InteliJ IDEA tools (Edit Confgurations -> Add New Configuration -> Tomcat -> Local).The first one is used to deploy htm-client:war exploded
  wich is a client war file.The second is used to deploy htm-rest-app:war exploded which is a server war file.
