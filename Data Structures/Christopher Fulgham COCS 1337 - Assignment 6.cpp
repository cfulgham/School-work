//Christopher Fulgham
// Program status: complete
// This program receives daily food totals in pounds for three monkies from user input, stores the information in a two dimensional array and then
// prints a table with the information, along with the average amount fed, the smallest and largest amounts.

#include "stdafx.h"
#include<iostream>
#include<iomanip>
#include<string>

using namespace std;

//Function Prototypes
void getFood(float [][7]);
void printTable(float[][7]);
float getAverage(float[][7]);
float getSmallest(float[][7]);
float getLargest(float[][7]);


int main()
{
	float monkeyFood[3][7]; //float array to hold pounds of monkey food for a number of monkeys

	getFood(monkeyFood);

	printTable(monkeyFood);
	cout << endl;
	cout << "The average amount of food eaten by all monkeys  :" << setw(10) << setprecision(1) << fixed << showpoint << getAverage(monkeyFood) << " pounds\n";
	cout << "The least amount of food eaten by any monkey     :" << setw(10) << setprecision(1) << fixed << showpoint << getSmallest(monkeyFood) << " pounds\n";
	cout << "The largest amount of food eaten by any monkey   :" << setw(10) << setprecision(1) << fixed << showpoint << getLargest(monkeyFood) << " pounds\n";

    return 0;
}


/*****************************************************************
*                         getFood                                *
* This function receives a two dimensional array of 7 columns    *
* and requires the user to enter the amount of food each monkey  *
* eats over a seven day period.                                  *
******************************************************************/

void getFood(float food[][7])
{
	string daysOfWeek[7] = { "Sun","Mon","Tue","Wed","Thu","Fri","Sat" };

	for (int monkey = 0; monkey < 3; monkey++)
	{
		for (int day = 0; day < 7; day++)
		{
			do
			{
				cout << "Enter pounds of food eaten by monkey " << monkey + 1 << " on " << daysOfWeek[day] << ": ";
				cin >> food[monkey][day];
				if (food[monkey][day] < 0)   //Check for positive number in input
				{
					cout << "Amount must be a positive number.\n";
				}
			} while (food[monkey][day] < 0);

		}
		cout << endl;

	}


}


/*****************************************************************
*                        printTable                              *
* This function receives a two dimensional array of 7 columns    *
* and prints a table of all information in the array             *
******************************************************************/

void printTable(float food[][7])
{

	cout << "Pounts of Food Eaten by Monkey and Day of Week\n\n";
	cout << "Monkey   Sun   Mon   Tue   Wed   Thu   Fri   Sat\n";

	for (int monkey = 0; monkey < 3; monkey++)
	{
		cout << setw(6) << monkey + 1;
		for (int day = 0; day < 7; day++)
		{
				cout << setw(6) << setprecision(1) << fixed << showpoint << food[monkey][day];
		}
		cout << endl;
	}
}

/*****************************************************************
*                        getAverage                              *
* This function receives a two dimensional array of 7 columns    *
* and returns the average of all values                          *
******************************************************************/

float getAverage(float food[][7])
{
	float total = 0;

	for (int monkey = 0; monkey < 3; monkey++)
	{
		for (int day = 0; day < 7; day++)
		{
			total += food[monkey][day];
		}
	}
	return total / 7;
}

/*****************************************************************
*                        getSmallest                             *
* This function receives a two dimensional array of 7 columns    *
* and returns the smallest value in the array.                   *
******************************************************************/
float getSmallest(float food[][7])
{
	float small;

	small = food[0][0];

	for (int monkey = 0; monkey < 3; monkey++)
	{
		for (int day = 0; day < 7; day++)
		{
			if (food[monkey][day] < small)
			{
				small = food[monkey][day];
			}
		}
	}

	return small;
}

/*****************************************************************
*                         getLargest                             *
* This function receives a two dimensional array of 7 columns    *
* and returns the largest value in the array.                    *
******************************************************************/

float getLargest(float food[][7])
{
	float large;

	large = food[0][0];

	for (int monkey = 0; monkey < 3; monkey++)
	{
		for (int day = 0; day < 7; day++)
		{
			if (food[monkey][day] > large)
			{
				large = food[monkey][day];
			}
		}
	}

	return large;

}
