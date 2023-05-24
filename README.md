# Key Features

Database Integration: The solution includes the establishment of a MySQL database with appropriate tables. This allows for seamless integration and validation of gender and product type information from the database, ensuring alignment with the input file.

Extended Validations: In addition to the gender and product type validations, the solution incorporates image URL validation. The image URLs are checked for their status code (200), ensuring the validity of each image associated with a row in the input file.

Logging: To provide detailed feedback and error reporting, a robust logging mechanism has been implemented using the Java Logger class. Logs are generated at different levels (INFO, SEVERE) throughout the validation process, aiding in identifying and resolving any issues encountered.

Testing: The commit history reflects the testing/debugging process, with appropriate commits containing debug versions of the code and relevant logging or debug statements to identify and resolve any issues encountered.

Multithreading: Before the final edit to submit it, commit history will show proper multithreading implementation for the validation process to execute concurrently, leveraging the available computing resources and reducing the overall execution time.

These enhancements add an extra layer of validation and data integrity to the original requirements. The solution strives to provide a comprehensive and reliable approach for validating input files, ensuring the accuracy and completeness of the data.

# Execution

To execute the JAR file correctly, you need to provide the command-line arguments as follows:
```
java -jar problemStatement.jar <inputFile> <outputFile>
```

For example, if the InputFile.csv file is located in src/main/resources/data/InputFile.csv, and you want the status.csv file to be generated in the same directory, you can use the following command:

```
java -jar problemStatement.jar src/main/resources/data/InputFile.csv src/main/resources/data/status.csv
```

Make sure to adjust the file paths based on the actual locations of the files in your project. By providing the correct command-line arguments, the program should be able to read the input file, perform the required validations, and generate the output file successfully.

## Class walkthrough (modular approach with separated functionality into different classes)

- Set up MySql database, using MySql 8 and this SQL query:
```
CREATE DATABASE lilyData;
```

- Set up tables to store information. We will need two tables: product table and genders table, enter the following queries:
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

## Java Program 


- Main method: serves as the entry point for the program.
```
Main.java:
The main method orchestrates the overall flow of the program, including reading the input file, performing validations, and writing the status file. Any unexpected errors are logged. Make sure to adjust the file paths and filenames as necessary to match the actual location of your input file and desired output file.
```

- Input handling: Read input file to read and validate input file.
```
CSVReader.java: 
Responsible for reading the input CSV file and returning the data as a list of arrays. The CSVReader class provides a convenient method readInputFile to read the input CSV file and obtain its contents as a list of rows. Any errors during the file reading process are logged.


CSVWriter.java: 
Handles writing data to the status CSV file. The CSVWriter class provides a method writeStatusFile to write the status file based on the provided rows and the specified output file. Any errors during the file writing process are logged.
```

- Validating data: Necessary validation logic for the different data fields specified in the problem.
```
ValidationUtils.java:  

validateGender(List<String[]> rows): This method validates the gender values in the given rows. It retrieves the valid genders from the CSV file (Genders.csv) using the getValidGendersFromCSV() method. Then, it checks each row for invalid gender values and logs them using the LOGGER object.

getValidGendersFromCSV(): This method reads the CSV file (Genders.csv) and retrieves the valid gender values from the "display_name" column. It uses the Apache Commons CSV library to parse the CSV file and stores the valid gender values in a list.

isValidGender(String gender): This method checks if the given gender value is valid. It compares the gender value with "men" and "women" (case-insensitive) and returns true if it matches either of them.

validateProductType(List<String[]> rows): This method validates the product type values in the given rows. It retrieves the valid product types from the CSV file (ProductTypes.csv) using the getValidProductTypesFromCSV() method. Then, it checks each row for invalid product type values and logs them using the LOGGER object.

getValidProductTypesFromCSV(): This method reads the CSV file (ProductTypes.csv) and retrieves the valid product type values from the "display_name" column. It uses the Apache Commons CSV library to parse the CSV file and stores the valid product type values in a list.

validateImages(List<String[]> rows): This method validates the image URLs in the given rows. It uses an ExecutorService and multiple threads to parallelize the image validation process. It iterates over each row, splits the image URLs by the separator ||, and creates an ImageValidationTask for each image URL. The tasks are submitted to the executor service for execution.

determineRowStatus(String[] row): This method determines the status of a row based on various validations. It checks the gender, product type, and image URLs in the row to identify any validation failures. It constructs a status string indicating the validation failures and returns it.

isValidImageUrl(String imageUrl): This method validates the given image URL by sending an HTTP HEAD request to the URL and checking the response status code. It returns true if the status code is HTTP OK (200), indicating a valid image URL.



ImageValidationTask.java: 
The ImageValidationTask class represents a task for validating an image URL. It logs messages indicating the progress and result of the image validation.

```




