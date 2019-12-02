# Homework 6 Part 1
## Due date:  Tuesday Oct 29 at  2p.m.
## Author: Christopher Fulgham


## Part 1 Code Review

### Solution 1 

* **Main.java:**
    Counter variable is used to limit the number of attempts to get datapath to 3. If this fails the user is not notified.
    ```javascript
      int counter = 0;
	    while(!(new File(inputPath)).exists()&& (counter <3)){
			  System.out.println("Data dir not found here: "+ inputPath);
			  System.out.println("Provide the path to the names folder:"); 
			  inputPath = in.nextLine();
			  counter++;
	    }
    ```
    Case 0 in the while loop for the main program will not run and the Thank you message will not display as the loop will end if the selection is 0.


* **NameAnalyzer.java:**

  Creates a name database only usable for the year portion of the problem.

  The use of a HashMap to store the data makes retaining a sorted list of the data more complicated. It might work better with a list or a treemap.

  The sex associated with the name is lost when stored in hashmap.


* **NameGuess.java:**

  Creates another name database only usable for the name look up portion of the problem

  As with the Name Analyzer there may be better data types to use for sorting the data.

### Solution 2 

* **NamesApp.java:**

  As with Main.java the counter is used to limit the attempts to enter a data path and the program does not provide an error message if the third one fails.

  Here too the 0 case will not be triggered.

* **NamesDB.java:** 

  Entire data set is loaded at time of instantiation, this may improve performance. 

  The List implementation makes this easy to sort using the Collections.sort method

  All data is maintained in the database.

* **NamesRecord.java:**

  compareFreqNames will return 1 by default instead of the gender result.



  Solution one does solve the problem and will result in correct answers, however there are redundancies that cause similar work to be done twice. The data must be process from the files twice once in the name analyzer and then again for the name guesser. The analyzing of the data is better accomplished in solution 2 where there are two classes one that just holds an entity of data and one that processes it. This is more inline with object oriented programming by encapsulating the data and responsibilities of the classes. Solution one does have some abstarction from the main program but there is no interaction between the other two classes. In solution 2, NamesDB and NamesRecord hides the extact function of their methods from each other. In this particular case there is not a lot of polymorphism or inheritence going on. 

All in all the code is pretty easy to read in both cases to figure out what is supposed to be going on. For solution one I would change it to be more like solution two where there is a single processing of the data. I feel like solution two does a good job at making the code and problem easy to understand.
