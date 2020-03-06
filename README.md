## EPOS System - CSC1035 - Assessment 3

Run ui.java for a text based ui, type help to view commands

In the ui.java file, there are different functions. 

The first one is inserting products in the database, with the command 
```insert - unique``` the user can input only one product and its details. 

The command ```insert - csv``` lets the user input multiple products and their 
information from a csv file.

The command ```lookup``` lets the user lookup products in the database using the 
any of its fields : id, name, category, if it is perishable or not, cost, quantity 
and sell price.

The command ```exit``` lets the user escape the program and prints out all of the 
products than have a quantity inferior to 5.
