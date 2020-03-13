## EPOS System - CSC1035 - Assessment 3

Run main.java to run program

In the ui.java file, there are different functions. 

The first one is inserting products in the database, with the command 
```insert - unique``` the user can input only one product and its details. 

The command ```insert - csv``` lets the user input multiple products and their 
information from a csv file.

The command ```lookup``` lets the user lookup products in the database using the 
any of its fields : id, name, category, if it is perishable or not, cost, quantity 
and sell price.

The command ```update``` lets the user update product's fields in the database using the 
the product's id.

The command ```purchase``` lets the user buy product from the available product in the 
database.

The command ```exchange - return``` lets the user return product bought.

The  command ```exchange - repurchase``` lets user buy new products from the credits after returning products
through the ```exchange - return``` command and entering the ID that ```exchange - return``` prints.

***Note:** Please make sure the transaction Id you enter is from the ```exchange - return``` transaction receipt.*

The command ```refund``` refunds the products the user has returned.

The command ```exit``` lets the user escape the program and prints out all of the 
products than have a quantity inferior to 5.
