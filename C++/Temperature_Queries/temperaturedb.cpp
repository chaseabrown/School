#include "temperaturedb.h"
#include <string>
#include <sstream>
#include <fstream>
using namespace std;

void TemperatureDatabase::loadData(const string& filename) {
	string line;
	LinkedList list();
	ifstream myfile (filename);
	if (myfile.is_open()){
		while ( getline (myfile,line) ){
	    	int location;
			int year;
			int month;
			double temperature;
	
			istringstream is(line);
			is >> location;
			is >> year;
			is >> month;
			is >> temperature;
			list.insert(location,year,month,temperature);
	    }
		myfile.close();
	}

	  else cout << "Unable to open file"; 
}

void TemperatureDatabase::performQuery(const string& filename) {
	// Implement this function
}


