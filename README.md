# problemStatement
In this program I will put together a Java Project for a job technical interview.


I will create a walkthrough of the steps I do.

## Step 1: Create MySql DB and tables:
- Set up MySql database, using MySql WorkBench 8 and this SQL query.
```
CREATE DATABASE lilyData;
```

- Set up tables to store information. We will need two tables: product table and genders table, use the following queries:
```
USE lilyData;

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

  - We need to get appropriate columns for each table, so lets insert data into tables, use the following queries:
```
LOAD DATA INFILE 'path_to_genders.csv'
INTO TABLE genders
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
IGNORE 1 LINES;
```

```
LOAD DATA INFILE 'path_to_productTypes.csv'
INTO TABLE product_types
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
IGNORE 1 LINES;
```


## Step 2: Write Java Program To do the following:
- Read input file to read and validate input file
```
Input handling:
- CSVReader.java: Responsible for reading the input CSV file and returning the data as a list of arrays.
- CSVWriter.java: Handles writing data to the status CSV file.
//Read the input file: The code reads the input file using CSVReader.
```
- Gender validation by comparing gender info with input file with genders in database, note error if any discrepancies are found
```
- GenderValidator.java: Performs gender validation against the database.
//The code uses the GenderValidator class to validate the gender in the input file against the genders from the table.
```
- Product type validation: Compare product types from the input file with the ones stored in the database, if mismatch occurs, note it.
```
- ProductTypeValidator.java: Performs product type validation against the database.
//The code uses the ProductTypeValidator class to validate the product type in the input file against the product types from the table.
```
- Image validation: split the image urls from the input file using the delimiter '||' , and check the image urls, verify the response code is 200 for each url, which indicates a valid image, if any of them are invalid make a note of it.
```
- ImageValidator.java: Validates the image URLs.
//The code uses the ImageValidator class to validate all the images in each row. It checks if all the images are valid (HTTP status 200).
```
- Create a status file in the form of a new csv file to store the rows and columns from the input file. 
- Include additional column named "status"
- Iterate through input file, validation each row and then write the row to the status file with the validation status and any errors
```
The code creates a status file (CSV) with all the rows and columns from the input file. It adds an additional "status" column to denote if the information for a specific row is valid or not. If there are any validation errors, the error messages are listed in plain English.
```

## Class walkthrough (modular approach with separated functionality into different classes)

Main class:
- Main.java: Contains the main method and serves as the entry point for the program.

Rest are covered above.



