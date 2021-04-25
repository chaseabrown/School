#include <iostream>
#include <string>
#include "linkedlist.h"
using namespace std;

LinkedList::LinkedList() {
	// Implement this function
}

LinkedList::~LinkedList() {
	// Implement this function
}

LinkedList::LinkedList(const LinkedList& source) {
	// Implement this function
}

LinkedList& LinkedList::operator=(const LinkedList& source) {
	// Implement this function
}

void LinkedList::insert(int location, int year, int month, double temperature) {
	// Implement this function
}

void LinkedList::clear() {
	// Implement this function
}

void LinkedList::print() const {
	/* Do not modify this function */
	print(cout);
}

void LinkedList::print(ostream& os) const {
	/* Do not modify this function */
	os << *this;
}

ostream& operator<<(ostream& os, const LinkedList& ll) {
	// Implement this function
}
