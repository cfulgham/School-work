//Christopher Fulgham
// Program status: complete
// This program receives Payroll information from user input, stores the information in arrays and then
// calculates the net pay, gross pay, tax, total net pay and total gross pay, displaying it on the screen.

#include "stdafx.h"
#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

// Declare employee information structure, that includes ID number, employee name, hourly pay rate, and employee type.
struct EmployInfo
{
	int ID;
	string name;
	float payRate;
	int type;
};

// Function prototype
void getEmployeeInfo(EmployInfo employeeMasterInfo[], int limit);
void getHoursWorked(EmployInfo employeeMasterInfo[], float hoursWorked[], int limit);

int main()
{
	const int NUMEMPLOYEES = 4;    //Constant for size of array 
	const float TAX = 0.15;        //Constant for tax rate 
	EmployInfo employeeMasterInfo[NUMEMPLOYEES];  //Array to hold employee data
	float hoursWorked[NUMEMPLOYEES];              //Array to hold employee hours
	float totalGrossPay = 0;                      //Gross Pay Accumulator
	float totalNetPay = 0;                        //Net Pay Accumulator
	float grossPay, tax, netPay, overtime;        //temporary value holders for calulations
	int index;                                    //Index for accessing arrays

												  //Call to functions to get input from user
	getEmployeeInfo(employeeMasterInfo, NUMEMPLOYEES);
	getHoursWorked(employeeMasterInfo, hoursWorked, NUMEMPLOYEES);

	//Calculate and display Payroll support
	cout << "Payroll Report" << endl << endl;
	cout << "ID Name                 Gross Pay      Tax  Net Pay\n";
	for (index = 0; index < NUMEMPLOYEES; index++)
	{
		grossPay = tax = netPay = overtime = 0;

		cout << employeeMasterInfo[index].ID << " ";
		cout << setw(20) << std::left << employeeMasterInfo[index].name << " ";

		//Check if employee worked overtime or is a manager
		if ((employeeMasterInfo[index].type == 0) && (hoursWorked[index] > 40.00))
		{
			overtime = hoursWorked[index] - 40;
			grossPay = (40 * employeeMasterInfo[index].payRate) + (overtime * employeeMasterInfo[index].payRate * 1.5);
			totalGrossPay += grossPay;
			cout << setw(9) << std::right << setprecision(2) << showpoint << fixed << grossPay << " ";

			tax = (grossPay * TAX);
			cout << setw(8) << std::right << setprecision(2) << showpoint << fixed << tax << " ";

			netPay = grossPay - tax;
			totalNetPay += netPay;
			cout << setw(8) << std::right << setprecision(2) << showpoint << fixed << netPay << endl;
		}
		else
		{
			grossPay = (hoursWorked[index] * employeeMasterInfo[index].payRate);
			totalGrossPay += grossPay;
			cout << setw(9) << std::right << setprecision(2) << showpoint << fixed << grossPay << " ";

			tax = (grossPay * TAX);
			cout << setw(8) << std::right << setprecision(2) << showpoint << fixed << tax << " ";

			netPay = grossPay - tax;
			totalNetPay += netPay;
			cout << setw(8) << std::right << setprecision(2) << showpoint << fixed << netPay << endl;
		};
	}

	cout << endl;

	//Display totals for all employees
	cout << "Total Gross Pay $ " << setprecision(2) << showpoint << fixed << totalGrossPay << endl;
	cout << "Total Net Pay   $ " << setprecision(2) << showpoint << fixed << totalNetPay << endl;

	return 0;
}


/************************************************************************
*                            getEmplyeeInfo                             *
*  This function can be used to fill an array of type EmployInfo. It    *
*  receives an array of type EmployInfo and an int representing the     *
*  size of the array                                                    *
*************************************************************************/
void getEmployeeInfo(EmployInfo employeeMasterInfo[], int limit)
{
	int index;

	for (index = 0; index < limit; index++)
	{
		cout << "Enter information for employee " << index + 1 << endl;
		//Input and validation for ID
		do
		{
			cout << "Employee ID: ";
			cin >> employeeMasterInfo[index].ID;
			if (employeeMasterInfo[index].ID < 0)
			{
				cout << "Input invalid, please enter a number > 0.\n";
			}
		} while (employeeMasterInfo[index].ID < 0);

		//Input and validation for employee names
		do
		{
			cout << "Employee name: ";
			cin.ignore();
			getline(cin, employeeMasterInfo[index].name);

			if (employeeMasterInfo[index].name.size() > 20)
			{
				cout << "Please limit names to 20 characters or less.\n";
			}
		} while (employeeMasterInfo[index].name.size() > 20);



		//Input and validation for hourly pay rate
		do
		{
			cout << "Pay rate: ";
			cin >> employeeMasterInfo[index].payRate;
			if (employeeMasterInfo[index].payRate < 0)
			{
				cout << "Input invalid, please enter a number > 0.\n";
			}
		} while (employeeMasterInfo[index].payRate < 0);

		//Input and validation for employee type
		do
		{
			cout << "Type: ";
			cin >> employeeMasterInfo[index].type;

			if ((employeeMasterInfo[index].type != 1) && (employeeMasterInfo[index].type != 0))
			{
				cout << "Input invalid, please enter a 1 or a 0.\n";
			}

		} while ((employeeMasterInfo[index].type != 1) && (employeeMasterInfo[index].type != 0));
		cout << endl;
	}
};


/************************************************************************
*                            getHoursWorked                             *
*  This function can be used to fill an array of floats to hold hours   *
*  worked by employees in an array of type EmployInfo. It receives an   *
*  array of type EmployInfo, a float array, and an int representing the *
*  size of the arrays.                                                  *
*************************************************************************/
void getHoursWorked(EmployInfo employeeMasterInfo[], float hoursWorked[], int limit)
{
	int index;

	cout << "Enter timecard information for each employee:\n";
	//Step through both arrays populating hoursWorked
	for (index = 0; index < limit; index++)
	{
		cout << "Hours worked for " << employeeMasterInfo[index].name << ": ";
		cin >> hoursWorked[index];
		//Input validation to ensure hours are a positive number
		while (hoursWorked[index] < 0)
		{
			cout << "Hours worked must be 0 or more, please enter new value.\n";
			cout << "Hours worked for " << employeeMasterInfo[index].name << ": ";
			cin >> hoursWorked[index];
		}
	}

};
