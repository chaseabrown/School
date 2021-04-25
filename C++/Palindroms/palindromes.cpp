#include <iostream>
#include <string>
#include <vector>
#include "functions.h"
#include "functions.cpp"
using namespace std;

int main(int argc, char *argv[]){
    functions function;
    vector<string> inputs;
	inputs.resize(argc);
   	bool space = false;
 	bool cases = false;
	bool error = true; 
 	int counter = 0;
 	
 	for(int i=1;i<argc;i++){
		if(argv[i][0] != '-'){
			error = false;
		}
	}

 
 	if(error){
  		function.printUsageInfo(argv[0]);
    }else{
      	
    for(int i=1;i<argc;i++){
     	if(argv[i][0] == '-'){
     		if(argv[i][1] == 'c' || argv[i][1] == 'C'){
				cout << "Made it to c" << endl;
       			cases = true;
      		}else if(argv[i][1] == 's' || argv[i][1] == 'S'){
				cout << "Made it to s" << endl;
        		space = true;
       		}
       
      	}else{
      		inputs[counter] = argv[i];
       		counter++;
     	}
     }
    
     for(int i=1;i<counter;i++){
             string input = inputs[i];
             
             if(function.isPalindrome(input,cases,space)==true){
                 cout << input << " is a palindrome." << endl;
             }else{
                 cout << input << " is not a palindrome." << endl;
             }
         }
    }
    return 0;
}