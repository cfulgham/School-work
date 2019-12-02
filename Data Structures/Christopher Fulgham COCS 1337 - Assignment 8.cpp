// Christopher Fulgham
//Program Status: Complete
//This program calculates the average of a user provided number of grades. It prompts the user
//for the number of grades, dynamically allocates an array of that size, then prompts the user
//for the grades and prints out the grades and the average

#include "stdafx.h"
#include <iostream>
#include <iomanip>

using namespace std;

//Function Prototyping
void input(int *,int);
void sort(int *, int);
float calcAverage(int *, int);
void displayTable(int *, int);

int main()
{
	int *grades = nullptr; //To dynamically allocate the array
	int numOfStudents;       //number of students/size of array


	cout << "Enter the number of students: ";
	cin >> numOfStudents;

	grades = new int[numOfStudents];  //Allocate memory for array

	input(grades, numOfStudents);    //Requets grades from user
	sort(grades, numOfStudents);     //Sort the grades in ascending order
	displayTable(grades, numOfStudents); //display the grades and average.

	delete [] grades;                //Free up memory by deleting array
	grades = nullptr;                //Prevent dangling pointer
    return 0;
}

/***********************************************************
*                        input                             *
*                                                          *
* This function fills an array of grades integers with     *
* user input. It receives a pointer to the array and the   *
* size of the array. It also validates the inputs are with *
* tolerance limits.                                        *
************************************************************/

void input(int *array, int size)
{
	cout << "Enter the grades below:\n";
	for (int index = 0; index < size; index++)
	{
		cin >> array[index];
		while ((array[index] < 0) || (array[index] > 104))  //input validation
		{
			cout << "Invalid grade, please re-enter:";
			cin >> array[index];
		}
	}
	
}


/***********************************************************
*                          sort                            *
*                                                          *
* This function receives a pointer to the array of grades  *
* and the size of the array. It then uses a bubble sort to *
* place the grades in ascending order.                     *
************************************************************/
void sort(int *array, int size)
{
	int temp;
	bool swap;

	do
	{
		swap = false;
		for (int index = 0; index < (size-1); index++)
		{
			if (array[index] > array[index + 1])
			{
				temp = array[index];
				array[index] = array[index + 1];
				array[index + 1] = temp;
				swap = true;
			}
		}
	} while (swap);
}


/***********************************************************
*                    calcAverage                           *
*                                                          *
* This function receives a pointer to an array and the size*
* of the array. It then calculates and returns the average *
* of the values in the array.                              *
************************************************************/
float calcAverage(int *array, int size)
{
	int total = 0;

	for (int index = 0; index < (size); index++)
	{
		total += *(array + index);
	}

	return total / size;
}

/***********************************************************
*                     displayTable                         *
*                                                          *
* This function receives the point to an array and the size*
* of the array, it prints the content of the array into a  *
* table and then prints the average from calling the       *
* calcAverage array.                                       *
************************************************************/
void displayTable(int *array, int size)
{
	cout << "         Grades\n";
	cout << "         ------\n";
	for (int index = 0; index < (size); index++)
	{
		cout << setw(15) << right << array[index] << endl;
	}
	cout << "         ------\n";
	cout << "Average: " << setw(6) << right << calcAverage(array, size) << endl;
}