# JPA Optimistic Locking with Hibernare

This project presents how to configure optimistic locking with Hibernate and is a code behind [allAroundJava's article](https://allaroundjava.com/hibernate-optimistic-locking/) on Optimistic Locking. 
You'll find a detailed explaination on how each option works together with its pros and cons.

You'll find three ways to configure the versioning in this repository. 
* Using the @Version annotated, numeric field (Car entity)
* Using @Version annotated, timestamp field (FinancialTransaction entity)
* Using Hibernate proprietary @OptimisticLocking annotation, comparing dirty properties (House entity)

## Running the project
* Clone the repository to your environment
* run ```mvn clean install -DskipTests=true``` This should download the h2 database dependency into your maven directory
* go to your home directory. On linux this will be ```cd /home/[youor username]```
* navigate to h2 database maven directory ```cd .m2/repository/com/h2database/h2/1.4.197```
* run the h2 jar with ```java -jar h2-1.4.197.jar```
* in h2 configuration page configure the database to run as Generic H2 (Server)
* navigate to project directory and run ```mvn clean install```
* tests should be ran against your local in-memory h2 database.

### Don't forget to visit [allAroundJava](https://allaroundjava.com/) for more Java tutorials and analyses. 

Good luck !