
#ifndef PRODUCT_H
#define PRODUCT_H
#include <string>
#include <iostream>

using namespace std;

class Product {
public:
	int productID;
	std::string productName;
	std::string description;
	int inventory;
	int numSold;
	double totalPaid;
    Product(int productID, string productName);
	Product operator<<(const Product::Product product)
	string getDescription();
	void setDescription(string description);
	string getName();
	int getID();
	int getNumberSold();
	double getTotalPaid();
	int getInventoryCount();
	void addShipment(int shipmentQuantity, double shipmentCost);
	void reduceInventory(int purchaseQuantity);
	double getPrice();
};

#endif