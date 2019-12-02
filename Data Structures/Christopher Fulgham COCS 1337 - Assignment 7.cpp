// Christopher Fulgham
// Program Status: Completed
// This program receives package information from the user (weight and three dimensions. It checks to 
// ensure the packages are within limites of size and dimensions. It will then determin the shipping cost,
// based on predefined weight limits and prices. It will display the number of transactions processed, if 
// the package was accepted or rejected, the weight of the package and the cost of shipping. Entering a -1 will
// end the program and display the total number of accepted and rejected packages.

#include "stdafx.h"
#include <iostream>
#include <iomanip>

using namespace std;


//Function prototypes
bool checkPackage(int, int, int, int);
float determineShippingCost(int);
bool displayInfo(int, int, int, int, int);


int main()
{
	int packageWeight, side1, side2, side3,  //Variables to hold weight and the three dimensions of the package
	    transaction = 0,                     //Transaction counter
		accepted = 0,                        //Accepted package counter
		rejected = 0;                        //Rejected package counter
	

	do
	{
		cout << "Enter package weight and 3 dimension: ";
		cin >> packageWeight;
		if (packageWeight == -1) //Ends loop if the exit value is entered
		{
			break;
		}
		cin >> side1 >> side2 >> side3; //Receives remaining values from user input
		cout << endl;

		if (checkPackage(packageWeight, side1, side2, side3)) //check to ensure user provided values are valid
		{
			transaction++;  //Increment transaction count;
			if (displayInfo(packageWeight, side1, side2, side3, transaction)) //displays package information, cost and determines if package was accepted.
			{
				accepted++; 
			}
			else
			{
				rejected++;
			}
		}
		else
		{
			cout << "Error - package weight and dimensions must be larger than 0.\nPlease re - enter transaction.\n\n";  //Error message for when package details our outside of limits
		}
	
	} while (packageWeight != -1);

	//Displays number of accepted and Rejected packages
	cout << endl;
	cout << "Number of accepted packages: " << accepted << endl;
	cout << "Number of rejected packages: " << rejected << endl;

	return 0;
}


/**********************************************************************
*                         checkPackage                                *
*                                                                     *
* Function determines if the dimensions and the weights are within    *
* limits. Function returns true value is weight is within range and   *
* the sides are over 0 and is used to clear up more complex if        *
* statement                                                           *
*                                                                     *
***********************************************************************/


bool checkPackage(int weight, int side1, int side2, int side3)
{
	
	if ((weight > 0) && (weight < 51) && (side1 > 0) && (side2 > 0) && (side3 > 0))
	{
		return true;
	}
	else
	{
		return false;
	}
}

/**********************************************************************
*                         determineShippingCost                       *
*                                                                     *
* This functions receives an integer value weight and returns the     *
*  shipping cost of of the package.                                   *
*                                                                     *
***********************************************************************/

float determineShippingCost(int weight)
{
	//declaring static arrays for shipping information to prevent them from being recreated each time the function is called.
	static int weights[15] = { 1,2,3,5,7,10,13,16,20,25,30,35,40,45,50 };
	static float shippingCosts[15] = { 1.50,2.10,4.00,6.75,9.90,14.95,19.40,24.20,27.30,31.90,38.50,43.50,44.80,47.40,55.20 };
	float shippingCost = -1;
	int index = 0;
	bool found = false;

	while ((index < 15) && !found)
	{
		if (weight <= weights[index])
		{
			found = true;
			shippingCost = shippingCosts[index];
		}
		index++;
	}

	return shippingCost;
}

/**********************************************************************
*                           displayInfo                               *
*                                                                     *
* This funcion receives interger values for the weight, three sides,  *
* and the transaction number of a package. It will determine if       *
* the package will be accepted based on the package girth. It will    *
* display the transaction number, if the package was accepted or      *
* rejected, the weight and cost of shipping the package.              *
*                                                                     *
* It will return true if the package was accepted or false is if was  *
* rejected.                                                           *
*                                                                     *
***********************************************************************/
bool displayInfo(int weight, int side1, int side2, int side3, int transaction)
{
	using std::cout;
	using std::endl;
	using std::setw;
	using std::right;
	float cost = -1;
	bool status;
	
	//Determines the largest side of the package
	int largest = side1;

	if (side2 > side1 && side2 > side3)
	{
		largest = side2;
	}
	else if (side3 > side1 && side3 > side2)
	{
		largest = side3;
	}


	cout << "Transaction:" << setw(10) << right << transaction << endl;

	//Determines if the package girth or weight is too large
	if (checkPackage(weight, side1, side2, side3))
	{
		if ((side1 > 36 || side2 > 36 || side3 > 36) || ((2 * (side1 + side2 + side3 - largest)) > 60))
		{
			cout << "Status     :" << setw(10) << right << "Rejected" << endl;
			status = false;
		}
		else
		{
			cout << "Status     :" << setw(10) << right << "Accepted" << endl;
			status = true;
			cost = determineShippingCost(weight);
		}
	}
	else
	{
		cout << "Status     :" << setw(10) << right << "Rejected" << endl;
	}



	//Display weight of package
	cout << "Weight     :" << setw(10) << right << weight << endl;



	//Display cost of shipping
	if (cost == -1)
	{
		cout << "Cost       :" << setw(10) << right << "-" << endl;
	}
	else
	{
		cout << "Cost       :" << setw(10) << right << setprecision(2) << fixed << cost << endl;
	}
	
	return status; //Return the status of the package; true for accepted; false for rejected
}
	