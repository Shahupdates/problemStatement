# problemStatement
In this program I will put together a Java Project for a job technical interview.


I will create a walkthrough of the steps I do.

#Step 1: Create MySql DB and tables:
- Set up MySql database, using MySql WorkBench 8 and this SQL query.
```
CREATE DATABASE your_database_name;
```

- Set up tables to store information
  - After looking at the tables, it seems ilke we will need two tables: product table and genders table, use the following queries:
```
-- Table for product types
CREATE TABLE product_types (
    id INT PRIMARY KEY,
    display_name VARCHAR(255),
    vertical_display_name VARCHAR(255)
);

-- Table for genders
CREATE TABLE genders (
    id INT PRIMARY KEY,
    display_name VARCHAR(255)
);
```

  - We need to get appropriate columns for each table


#Step 2: Write Java Program To do the following:
- Read input file to read and validate input file
- Gender validation by comparing gender info with input file with genders in database, note error if any discrepancies are found
- Product type validation: Compare product types from the input file with the ones stored in the database, if mismatch occurs, note it.
- Image validation: split the image urls from the input file using the delimiter '||' , and check the image urls, verify the response code is 200 for each url, which indicates a valid image, if any of them are invalid make a note of it.
- Create a status file in the form of a new csv file to store the rows and columns from the input file. 
- Include additional column named "status"
- Iterate through input file, validation each row and then write the row to the status file with the validation status and any errors


#Class walkthrough (modular approach with separated functionality into different classes)

Main class:
- Main.java: Contains the main method and serves as the entry point for the program.

Input handling:
- CSVReader.java: Responsible for reading the input CSV file and returning the data as a list of arrays.
- CSVWriter.java: Handles writing data to the status CSV file.

Validation:

- GenderValidator.java: Performs gender validation against the database.
- ProductTypeValidator.java: Performs product type validation against the database.
- ImageValidator.java: Validates the image URLs.
