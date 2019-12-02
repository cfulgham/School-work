// Christopher Fulgham
//Program Status: Complete (had to use strcpy_s() as complier would not let me use the old strcpy)
//This program calculates the average of a user provided number of grades. It prompts the user
//for the number of grades, dynamically allocates an array of that size, then prompts the user
//for the grades and prints out the grades and the average

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
	Employee(int initId = 0, char initName[0] = "",
		double initHourlyPay = 0.0,
		int initNumDeps = 0, int initType = 0);  // Constructor

	bool set(int newId, char newName[], double newHourlyPay,
		int newNumDeps, int newType);

	int getID();

	void getName(char nameReturn[]);

	double getHourlyPay();

	int getNumDeps();

	int getType();


};

// Function prototype
int getEmployeeInfo(Employee employeeMasterInfo[], int limit);
void calculatePayroll(Employee employeeMasterInfo[], int limit);

int main()
{

	const int NUMEMPLOYEES = 100;    //Constant for size of array 
	Employee employeeMasterInfo[NUMEMPLOYEES];  //Array to hold employee data	
	int count;
	
	
	count = getEmployeeInfo(employeeMasterInfo, NUMEMPLOYEES);  //Gets employee info from file
	calculatePayroll(employeeMasterInfo, count);                //calculates and saves payroll

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
		strcpy_s(name, 21, "");
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
		strcpy_s(name, 21, newName);
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


void Employee::getName(char nameReturn[])
{
	strcpy_s(nameReturn, 21, name);
	
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
int getEmployeeInfo(Employee employeeMasterInfo[], int limit)
{
	int index = 0;
	int newID, newNumDeps, newType, count = 0;
	char newName[21];
	double newHourlyPay;
	char sex;
	ifstream inputMaster;

	inputMaster.open("master10.txt"); // Open master employee information file

	cout << "Processing employee information." << endl;

	while (!inputMaster.eof())
	{

		inputMaster >> newID;
		inputMaster.get(newName, 21, '\n');
		inputMaster >> newHourlyPay;
		inputMaster >> newNumDeps;
		inputMaster >> newType;
		inputMaster >> sex;
		employeeMasterInfo[index].set(newID, newName, newHourlyPay, newNumDeps, newType);
		index++;
		count++;

	}

	cout << "Employee information processing complete." << endl;

	inputMaster.close();  //Close master employee information file
	return count - 1;     //Returns number of times loop ran minus one to indicate the number of elements of array filled
};

/*****************************************************************************
*                            calculatePayroll                                *
* This function is used to calculate the complete payroll for all employees. *
* It receives an array of type Employee and the size of the array. It uses   *
* the trans10.txt file to read employee IDs and hours. It then calculates    *
* the tax, gross, insurance costs of the employees and net pay, along with	 *
* the totals for gross pay and net pay. If an ID is not found or hours worked*
* is incorrect it will print the line to errorReport.txt.                    *
******************************************************************************/
void calculatePayroll(Employee employeeMasterInfo[], int limit)
{
	ofstream payrollReport, errorReport;
	ifstream inputTransaction;
	const float TAX = 0.15;							   //Constant for tax rate 
	const float DEPCOST = 20.0;						   //Constant for dependent fee
	float totalGrossPay = 0;                           //Gross Pay Accumulator
	float totalNetPay = 0;                             //Net Pay Accumulator
	float grossPay, tax, netPay, overtime, depend, 
		hoursWorked;								   //Temporary value holders for calulations
	int idTemp, index , transactions = 0;			   //Variables for holding id, array index and the number of transactions
	bool found;
	char nameTemp[21];

	payrollReport.open("payroll.txt");
	errorReport.open("errorReport.txt");
	inputTransaction.open("trans10.txt");

	

	//Calculate and save to file Payroll support
	payrollReport << "Payroll Report" << endl << endl;
	payrollReport << "ID  Name                 Gross Pay      Tax  Insurance  Net Pay\n";

	errorReport << "Error Report" << endl << endl;
	errorReport << "Transaction lines with error found:" << endl;
	
	//Input id and hours worked from file
	while (inputTransaction >> idTemp >> hoursWorked)
	{

		found = false;
		index = 0;
		//Search through Employee Information array to see if ID is found
		while (!found && index < limit && hoursWorked > 0)
		{
			//Process pay if ID is found
			if (idTemp == employeeMasterInfo[index].getID())
			{

				grossPay = tax = netPay = overtime = 0;

				payrollReport << setw(2) << std::right << employeeMasterInfo[index].getID() << " ";
				employeeMasterInfo[index].getName(nameTemp);
				payrollReport << setw(21) << std::left << nameTemp << " ";

				//Check if employee worked overtime or is a manager
		
				if ((employeeMasterInfo[index].getType() == 0) && (hoursWorked > 40.00))
				{
					//Calculate pay including overtime
					overtime = hoursWorked - 40;
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
					grossPay = (hoursWorked * employeeMasterInfo[index].getHourlyPay());
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
				found = true;
				transactions++;
				
			}
			index++;
		}
		//Print to error file is ID was not found or hours worked is negative
		if (!found && index == limit || hoursWorked < 0)
		{
			errorReport << setw(2) << idTemp << " " << setprecision(1) << fixed << showpoint << hoursWorked << endl;
		}

		
	}


	//Save to file totals for all employees
	payrollReport << "Total:" << setw(28) << setprecision(2) << showpoint << fixed << totalGrossPay << setw(29) << totalNetPay << "\n";
	//Save to Error Report number of transactions processed
	errorReport << "Transactions processed: " << transactions << endl;
	cout << "Errors during processing saved to file errorReport.txt.\n"; 

	payrollReport.close();
	errorReport.close();
	inputTransaction.close();


}
