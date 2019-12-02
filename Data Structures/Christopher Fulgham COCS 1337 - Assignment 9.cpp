//Christopher Fulgham
// Program status: complete
// This program receives Payroll information from file, stores the information in arrays and then
// calculates the net pay, gross pay, tax, total net pay and total gross pay, saving the report to a file.

#include "stdafx.h"
#include <iostream>
#include <iomanip>
#include <fstream>

using namespace std;

class Employee
{
private:
	int id;             // employee ID
	char name[21];        // employee name
	double hourlyPay;   // pay per hour
	int numDeps;        // number of dependents
	int type;           // employee type

public:
	Employee(int initId = 0, string initName = "",
		double initHourlyPay = 0.0,
		int initNumDeps = 0, int initType = 0);  // Constructor

	bool set(int newId, string newName, double newHourlyPay,
		int newNumDeps, int newType);

	int getID();

	string getName();

	double getHourlyPay();

	int getNumDeps();

	int getType();

};



// Function prototype
void getEmployeeInfo(Employee employeeMasterInfo[], int limit);
void getHoursWorked(float hoursWorked[], int limit);
void calculatePayroll(Employee employeeMasterInfo[], float hoursWorked[], int limit);

int main()
{

	const int NUMEMPLOYEES = 100;    //Constant for size of array 
	Employee employeeMasterInfo[NUMEMPLOYEES];  //Array to hold employee data	

	getEmployeeInfo(employeeMasterInfo, NUMEMPLOYEES);  //Gets employee info from file
	//getHoursWorked(hoursWorked, NUMEMPLOYEES);          //Gets employee's hours from file
	//calculatePayroll(employeeMasterInfo, hoursWorked, NUMEMPLOYEES); //calculates and saves payroll

	return 0;
}


Employee::Employee(int initId, char initName[],
	double initHourlyPay,
	int initNumDeps, int initType)
{
	bool status = set(initId, initName, initHourlyPay,
		initNumDeps, initType);

	if (!status)
	{
		id = 0;
		name = "";
		hourlyPay = 0.0;
		numDeps = 0;
		type = 0;
	}
}

bool Employee::set(int newId, char newName[], double newHourlyPay,
	int newNumDeps, int newType)
{
	bool status = false;

	if (newId > 0 && newHourlyPay > 0 && newNumDeps >= 0 &&
		newType >= 0 && newType <= 1)
	{
		status = true;
		id = newId;
		name = newName;
		hourlyPay = newHourlyPay;
		numDeps = newNumDeps;
		type = newType;
	}
	return status;
}

int Employee::getID()
{
	return id;
}

string Employee::getName()
{
	return name;
}

double Employee::getHourlyPay()
{
	return hourlyPay;
}

int Employee::getNumDeps()
{
	return numDeps;
}

int Employee::getType()
{
	return type;
}


/**************************************************************************
*                            getEmplyeeInfo                               *
*  This function can be used to fill an array of type EmployInfo. It      *
*  receives an array of type Employee and an int representing the         *
*  size of the array. It then fills the array from a file name master9.txt*
***************************************************************************/
void getEmployeeInfo(Employee employeeMasterInfo[], int limit)
{
	int index =  0;
	int newID, newNumDeps, newType;
	char newName[21];
	double newHourlyPay;
	ifstream inputMaster;

	inputMaster.open("master9.txt"); // Open master employee information file

	cout << "Processing employee information." << endl;

	while (!inputMaster.eof())
	{

		inputMaster >> newID;
		inputMaster.get(newName, '#');
		inputMaster >> newHourlyPay;
		inputMaster >> newNumDeps;
		inputMaster >> newType;
		employeeMasterInfo[index].set(newID, newName[21], newHourlyPay, newNumDeps, newType);

	}

	cout << "Employee information processing complete." << endl;

	inputMaster.close();  //Close master employee information file
};


/************************************************************************
*                            getHoursWorked                             *
*  This function can be used to fill an array of floats to hold hours   *
*  worked by employees in an array of type EmployInfo. It receives an   *
*  array of type EmployInfo, a float array, and an int representing the *
*  size of the arrays. The input comes from a file named trans9.txt     *
*************************************************************************/
void getHoursWorked(float hoursWorked[], int limit)
{
	ifstream inputTrans;
	int index;
	int id;

	inputTrans.open("trans9.txt");  //Open hours worked 

	cout << "Processing hours worked" << endl;

	//Step through both arrays populating hoursWorked
	for (index = 0; index < limit; index++)
	{

		inputTrans >> id;
		inputTrans >> hoursWorked[index];

	}

	cout << "Hours worked information processing complete." << endl;

	inputTrans.close();

};


/*****************************************************************************
*                            calculatePayroll                                *
* This function is used to calculate the complete payroll for all employees. *
* It receives an array of type Employee, an array of floats that represent   *
* hours worked and the size of the array. It then calculates the tax, gross, *
* insurance costs of the employees and net pay, along with the totals for    *
* gross pay and net pay.                                                     *
******************************************************************************/
void calculatePayroll(Employee employeeMasterInfo[], float hoursWorked[], int limit)
{
	ofstream payrollReport;
	const float TAX = 0.15;        //Constant for tax rate 
	const float DEPCOST = 20.0;   //Constant for dependent fee
	float totalGrossPay = 0;                      //Gross Pay Accumulator
	float totalNetPay = 0;                        //Net Pay Accumulator
	float grossPay, tax, netPay, overtime, depend;        // temporary value holders for calulations
	int index, transactions = 0;                       //Index for accessing arrays

	payrollReport.open("payroll.txt");

	//Calculate and save to file Payroll support
	payrollReport << "Payroll Report" << endl << endl;
	payrollReport << "ID  Name                 Gross Pay      Tax  Insurance  Net Pay\n";

	for (index = 0; index < limit; index++)
	{
		grossPay = tax = netPay = overtime = 0;

		if ((employeeMasterInfo[index].getID() == 0) || hoursWorked[index] <= 0)  //Check for any invalid information and display an error message
		{
			cout << "Error processing employee information from Master file or employee worked no hours, employee will not be paid.\n";
		}
		else
		{
			payrollReport << setw(2) << std::right << employeeMasterInfo[index].getID() << " ";
			payrollReport << setw(20) << std::left << employeeMasterInfo[index].getName() << " ";

			//Check if employee worked overtime or is a manager

			if ((employeeMasterInfo[index].getType() == 0) && (hoursWorked[index] > 40.00))
			{
				//Calculate pay including overtime
				overtime = hoursWorked[index] - 40;
				grossPay = (40 * employeeMasterInfo[index].getHourlyPay()) + (overtime * employeeMasterInfo[index].getHourlyPay() * 1.5);
				totalGrossPay += grossPay;
				payrollReport << setw(9) << std::right << setprecision(2) << showpoint << fixed << grossPay << " ";

				//Calculate tax
				tax = (grossPay * TAX);
				payrollReport << setw(8) << std::right << setprecision(2) << showpoint << fixed << tax << " ";

				//Calculate insurance costs
				depend = (employeeMasterInfo[index].getNumDeps() * DEPCOST);
				payrollReport << setw(10) << std::right << setprecision(2) << showpoint << fixed << depend << " ";

				//Calculate net pay
				netPay = grossPay - tax - depend;
				totalNetPay += netPay;
				payrollReport << setw(8) << std::right << setprecision(2) << showpoint << fixed << netPay << "\n";
			}
			else
			{
				//Calculate pay
				grossPay = (hoursWorked[index] * employeeMasterInfo[index].getHourlyPay());
				totalGrossPay += grossPay;
				payrollReport << setw(9) << std::right << setprecision(2) << showpoint << fixed << grossPay << " ";

				//Calculate tax
				tax = (grossPay * TAX);
				payrollReport << setw(8) << std::right << setprecision(2) << showpoint << fixed << tax << " ";

				//Calculate insurance costs
				depend = (employeeMasterInfo[index].getNumDeps() * DEPCOST);
				payrollReport << setw(10) << std::right << setprecision(2) << showpoint << fixed << depend << " ";

				//Calculate net pay
				netPay = grossPay - tax;
				totalNetPay += netPay;
				payrollReport << setw(8) << std::right << setprecision(2) << showpoint << fixed << netPay << "\n";
			};
			transactions++; //increment transactions if one was done
		}

	}


	//Save to file totals for all employees
	payrollReport << "Total:" << setw(28) << setprecision(2) << showpoint << fixed << totalGrossPay << setw(29) << totalNetPay << "\n";

	cout << "Number of transactions processed: " << transactions << endl; //Displays total number of transactions completed

	payrollReport.close();


}
