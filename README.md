# DiscountCalculator
Sample application to calculate discount for a Retail.

## Pre-requisites
- Java8
- Maven

## How to execute the code?
Build the source code using maven
> cd sample-retail
> mvn clean install

Go to the target folder
> cd target

Execute the command
> java -jar sample-retail-\<version\>.jar -h
 
It will list down all the options to be used with the 

## Options
ba - total bill amount without any discounts, needs a **double** value.  
em - use it if the customer is an employee of the retailer.  
af - use it if the customer is an affiliate to the retaier.  
ca - use it if both the above are not the case, it takes a number of years as value to be mentioned in **integer**.  
gr - use it to mention if the retail has groceries.  
gra - grocery amount, needs a **double** value.  


## Examples
> java -jar sample-retail-0.0.1.jar -h

> java -jar sample-retail-0.0.1.jar -em -gr -gra 100.0 -ba 850.0

> java -jar sample-retail-0.0.1.jar -af -gr -gra 50.0 -ba 150.0

> java -jar sample-retail-0.0.1.jar -ca 1 -gr -gra 200 -ba 500.0


## UML Diagram
Added the UML diagram under ![retail-uml.jpeg](https://github.com/SantoshKrishnaNC/SampleRetail/tree/master/sample-retail/src/main/resources/images/retail-uml.jpeg)


## Code Analysis
Ran the code using the sonarcloud workflow, here is the result from when running the workflow.![sonarqube.jpeg](https://github.com/SantoshKrishnaNC/SampleRetail/tree/master/sample-retail/src/main/resources/images/sonarqube.jpeg)
