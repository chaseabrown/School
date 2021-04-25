#include <iostream>
#include <vector>
#include <cstdlib>

using namespace std;
int main(int argc, char *argv[]) {
	int numDigets = 0;
	int code = 0;
	int guess = 0;
	int bulls = 0;
	int cows = 0;
	vector<bool> vBeenUsed(0);
	vector<int> vCode(0);
	vector<int> vGuess(0);
	
	
	do {
		cout << "Enter number of digits in code (3, 4 or 5): " << endl;
		cin >> numDigets;
		if(numDigets>=3&&numDigets<=5){
			vCode.resize(numDigets);
			vBeenUsed.resize(numDigets);
			for (int i=0;i<numDigets;i++) {
				int temp = rand() % 10;
				while(true){
					if(!vBeenUsed[temp]){
						vBeenUsed[temp] = true;
						break;
					}else{
						temp = rand() % 10;
					}
				}
				vCode[i] = temp;
			}
			break;
		}else if(numDigets == 0){
			cout << "Enter code: ";
			cin >> code;
			cout << "Enter number of digits in code: " << endl;
			cin >> numDigets;
			vCode.resize(numDigets);
			for (int i=numDigets-1;i>=0;i--) {
				vCode[i] = code % 10;
				code /= 10; 
			}
			break;
		}
	} while (true);
	
	cout << "Number to guess: ";
	for (int i=0;i<numDigets;i++) {
		cout << vCode[i];
	}
	cout << endl;
	
	while (bulls!=numDigets) {
		bulls = 0;
		cows = 0;
		bool acceptable = false;
		do {
			bool testAgain = true;
			cout << "Enter guess: " << endl;
			cin >> guess;
			
			vGuess.resize(numDigets);
			for (int i=numDigets-1;i>=0;i--) {
				vGuess[i] = guess % 10;
				guess /= 10; 
			}
			if(guess > 0){
				testAgain = false;
				cout << "You can only enter " << numDigets << " digits." << endl;
			}
			for (int i=0;i<numDigets;i++) {
				for (int s=0;s<numDigets;s++) {
					if(i!=s && vGuess[i] == vGuess[s] && testAgain){
						cout << "Each number must be different." << endl;
						testAgain = false;
						break;
					}
				}
			}
			if(testAgain){
				acceptable = true;
			}
		} while (!acceptable);
		
		
		
	
		for (int i=0;i<numDigets;i++) {
			for (int s=0;s<numDigets;s++) {
				if(i==s){
					if(vCode[i] == vGuess[s]){
						bulls++;
					}
				}else {
					if(vCode[i] == vGuess[s]){
						cows++;
					}
				}
			}
		}
		
		if(bulls!=numDigets){
			cout << bulls << " bulls" << endl;
			cout << cows << " cows" << endl;
		}
		
	}
	
	cout << bulls << " bulls - ";
	for (int i=0;i<numDigets;i++) {
		cout << vCode[i];
	}
	cout << " is correct!";
	
	return 0;
}