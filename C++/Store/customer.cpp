#include "customer.h"
#include <iostream>
#include <vector>
#include <string>
using namespace std;

int customerID;
string customerName;
bool credit;
double balance;
vector<Product> productsPurchased;

Customer::Customer(string name, int customerID, bool credit){
	customerName = name;
	this->customerID = customerID;
	this->credit = credit;
 	balance = 0;
}
string Customer::getName(){
	return customerName;
}
void Customer::setName(string name){
	customerName = name;
}	
int Customer::getID(){
	return customerID;
}
bool Customer::getCredit(){
	return credit;
}
void Customer::setCredit(bool hasCredit){
	credit = hasCredit;
}
double Customer::getBalance(){
	return balance;
}
void Customer::processPayment(double amount){
	if (amount < 0){
		throw myDataFormatException("Amount Cannot be Negative");
	} 
	balance += amount;
}
void Customer::processPurchase(double amount, Product product){
	if(credit){
		balance -= amount;
		productsPurchased.push_back(product);
	}else{
		if (balance >= amount) {
			balance -= amount;
			productsPurchased.push_back(product);
		}else{
			throw myDataFormatException("Balance cannot be negative if Credit is false");
		}
	}
	
}
void Customer::listProductsPurchased(ostream& os){
	
}