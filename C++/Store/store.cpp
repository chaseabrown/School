#include <iostream>

using namespace std;
int main(int argc, char *argv[]) {
	
	list<Product> productList;
	list<Customer> customerList;
	string storeName;
	
	Store::Store(){
		storeName = "";
	}
	Store::Store(string name){
		storeName = name;
	}
	string Store::getName(){
		return storeName;
	}
	void Store::setName(string name){
		storeName = name;
	}
	void Store::addProduct(int productID, string productName){
		Product product = new Product(productID, productName);
		productList.push_back(product);
	} 
	void Store::addCustomer(int customerID, string customerName){
		Customer customer = new Customer(customerID, customerName);
		customerList.push_back(customer);
	}
}