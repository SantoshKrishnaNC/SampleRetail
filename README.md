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
