#ifndef NODE
#define NODE

// Include the header file for the data struct/class if you use one.
// For example, if you have a data class in data.h, put
//  #include "data.h"
// below.

struct Node {
	// Add your member variable for the data fields here.
	// You can use an extra struct/class for storing the data. In that case, put
	// your definition of the data class in a separate header file, for example:
	// data.h

	// Pointer to the next node in the linked list
	Node* next;

	// Default constructor
	Node() : next(nullptr) {
		// Initialize your data members properly inside the function body
	}

	Node(/*FIXME */) : next(nullptr) {
		// Initialize your data members properly inside the function body
	}

  // The function below is written. Do not modify it
	virtual ~Node() {}
};

#endif
