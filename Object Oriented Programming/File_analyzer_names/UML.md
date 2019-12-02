# Homework 6 Part 2
## Due date:  Tuesday Oct 29 at  2p.m.
## Author: Christopher Fulgham


  
## Use Cases

* **Use case: Search for a year**
    Search the data for a year given by the user and return the top names in that year

* **Use case: Search for a name**
    Search the date for a name given by the user and return the top years it was used in

* **Use case: Load data from files**
    Open a data path provided by the user. Scan each file in that path and load information into a 	database for further processing.

* **Use case: Provide data path**
    Store user provided data path for later processing of data.

## Use Case Diagram

![Image of Use Case](https://git.txstate.edu/CS3354/cf1031/blob/master/assign1/usecase.png)

## CRC Cards

**NamesApp**
Responsibilities:
 * Provide menu to user                      
 * Process input and call appropriate method 
Collaborators:
 * NamesDB

**NamesDB**
Responsibilities:
 * Process data from file to database          
 * Find most common names for year
 * Find most common years for name
Collaborators:
 * NamesRecord

**NamesRecord**
Responsibilities:
 * maintain data for a name
 * including ways to compare them 
Collaborators:
 
## Class Diagram

![Image of Class Diagram](https://git.txstate.edu/CS3354/cf1031/blob/master/assign1/ClassDiagram.png)

## Sequence Diagram

![Image of Sequence Diagram](https://git.txstate.edu/CS3354/cf1031/blob/master/assign1/sequence.png)


