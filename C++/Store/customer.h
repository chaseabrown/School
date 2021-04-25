
#ifndef CUSTOMER_H
#define CUSTOMER_H
#include "product.h"
#include <string>
#include <list>

using namespace std;

class Customer {
public:
	int customerID;
	std::string customerName;
	bool credit;
	double balance;
	list<Product> productsPurchased;
    Customer(string name, int customerID, bool credit);
	string getName();
	void setName(string name);
	int getID();
	bool getCredit();
	void setCredit(bool hasCredit);
	double getBalance();
	void processPayment(double amount); 
	void processPurchase(double amount, Product product); 
	void listProductsPurchased(ostream& os); 
};

#endif