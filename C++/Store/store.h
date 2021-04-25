
#ifndef STORE_H
#define STORE_H

#include "product.h"
#include "customer.h"
#include <string>
#include <list>
#include <iostream>

using namespace std;

class Store {
public:
	list<Product> productList;
	list<Customer> customerList;
	string storeName;
	Store();
	Store(string name);
	string getName();
	void setName(string name);
	void addProduct(int productID, string productName); 
	void addCustomer(int customerID, string customerName);
};

#endif

