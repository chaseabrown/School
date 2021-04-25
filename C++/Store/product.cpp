#include "product.h"
#include <iostream>
#include <string>
using namespace std;

int productID;
string productName;
string description;
int inventory;
int numSold;
double totalPaid;

Product::Product(int productID, string productName){
	this->productID = productID;
	this->productName = productName;
 	inventory = 0;
 	numSold = 0;
 	totalPaid = 0;
}
Product::Product operator<<(const Product::Product product){
	
}
string Product::getDescription(){
	return description;
}
void Product::setDescription(string description){
	this->description = description;
}
string Product::getName(){
	return productName;
}
int Product::getID(){
	return productID;
}
int Product::getNumberSold(){
	return numSold;
}
double Product::getTotalPaid(){
	return totalPaid;
}
int Product::getInventoryCount(){
	return inventory;
}
void Product::addShipment(int shipmentQuantity, double shipmentCost){
	if (shipmentQuantity < 0 || shipmentCost < 0){
			throw myDataFormatException("Shipment quantity and shipment cost cannot be negative");
	} 	
	inventory += shipmentQuantity;
	totalPaid += shipmentCost;
}
void Product::reduceInventory(int purchaseQuantity){
	if (purchaseQuantity < 0){
			throw myDataFormatException("Purchase Quantity Cannot be Negative");
	} 
	inventory -= purchaseQuantity;
}
double Product::getPrice(){
	double price = totalPaid/static_cast<double>(inventory+numSold)*1.25
	return price;
} 



