#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;

int findMin(int x, int y, int z){
	int min = y;
	if(min>z){
		min = z;
	}
	if(min>x){
		min = x;
	}
	return min;
}

int findShortest(vector<int> counter){
	int min = counter[0];
	int minPos = 0;
	for (int i=1;i<counter.size();i++) {
		if(min > counter[i]){
			min = counter[i];
			minPos = i;
		}
	}
	return minPos;
}

int colorPath(const vector< vector<int> >& heightMap, vector< vector<int> >& r, vector< vector<int> >& g, vector< vector<int> >& b, int color_r, int color_g, int color_b, int start_row){
	return 0;
}

int colorPath(const vector< vector<int> >& heightMap, vector< vector<int> >& r, vector< vector<int> >& g, vector< vector<int> >& b, int color_r, int color_g, int color_b, int start_row, int start_col){
	return 0;
}

void paintPath(vector< vector<int> >& input, vector< vector<string> >& output, vector<int>& counter, int startRow, int row, int col){
	int upDif = 0;
	int forDif = 0;
	int downDif = 0;
	int currentRow = startRow;
	string colorCode = "19 254 253 ";
	output.at(currentRow).at(0) = colorCode;
		
		
	//------------------------------North-------------------------------
	for (int i=1;i<col;i++) {
	//Calculating differences
		if(currentRow == 0){
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			downDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow+1).at(i));
		}else if (currentRow == row-1) {
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			upDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow-1).at(i));
		}else {
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			upDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow-1).at(i));
			downDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow+1).at(i));
		}	
	//Painting Path
		
		if(currentRow == 0){
			if(forDif <= downDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow++;
				counter[startRow] += downDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}else if (currentRow == row-1){
			if(forDif <= upDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow--;
				counter[startRow] += upDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}else{
			if(findMin(upDif, forDif, downDif) == forDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else if(findMin(upDif, forDif, downDif) == downDif){
				currentRow++;
				counter[startRow] += downDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow--;
				counter[startRow] += upDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}
	}
	
	
	//------------------------------South-------------------------------
	for (int i=1;i<col;i++) {
	//Calculating differences
		if(currentRow == 0){
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			downDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow+1).at(i));
		}else if (currentRow == row-1) {
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			upDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow-1).at(i));
		}else {
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			upDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow-1).at(i));
			downDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow+1).at(i));
		}	
	//Painting Path
		
		if(currentRow == 0){
			if(forDif <= downDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow++;
				counter[startRow] += downDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}else if (currentRow == row-1){
			if(forDif <= upDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow--;
				counter[startRow] += upDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}else{
			if(findMin(upDif, forDif, downDif) == forDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else if(findMin(upDif, forDif, downDif) == downDif){
				currentRow++;
				counter[startRow] += downDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow--;
				counter[startRow] += upDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}
	}
	
	
	//------------------------------East-------------------------------
	for (int i=1;i<col;i++) {
	//Calculating differences
		if(currentRow == 0){
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			downDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow+1).at(i));
		}else if (currentRow == row-1) {
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			upDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow-1).at(i));
		}else {
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			upDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow-1).at(i));
			downDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow+1).at(i));
		}	
	//Painting Path
		
		if(currentRow == 0){
			if(forDif <= downDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow++;
				counter[startRow] += downDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}else if (currentRow == row-1){
			if(forDif <= upDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow--;
				counter[startRow] += upDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}else{
			if(findMin(upDif, forDif, downDif) == forDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else if(findMin(upDif, forDif, downDif) == downDif){
				currentRow++;
				counter[startRow] += downDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow--;
				counter[startRow] += upDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}
	}
	
	
	
	//------------------------------West-------------------------------
	for (int i=1;i<col;i++) {
	//Calculating differences
		if(currentRow == 0){
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			downDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow+1).at(i));
		}else if (currentRow == row-1) {
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			upDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow-1).at(i));
		}else {
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			upDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow-1).at(i));
			downDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow+1).at(i));
		}	
	//Painting Path
		
		if(currentRow == 0){
			if(forDif <= downDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow++;
				counter[startRow] += downDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}else if (currentRow == row-1){
			if(forDif <= upDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow--;
				counter[startRow] += upDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}else{
			if(findMin(upDif, forDif, downDif) == forDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else if(findMin(upDif, forDif, downDif) == downDif){
				currentRow++;
				counter[startRow] += downDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow--;
				counter[startRow] += upDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}
	}
	
	
}

void paintPath(vector< vector<int> >& input, vector< vector<string> >& output, vector<int>& counter, int startRow, int row, int col, string colorCode){
	int upDif = 0;
	int forDif = 0;
	int downDif = 0;
	int currentRow = startRow;
	output.at(currentRow).at(0) = colorCode;
	
	for (int i=1;i<col;i++) {
	//Calculating differences
		if(currentRow == 0){
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			downDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow+1).at(i));
		}else if (currentRow == row-1) {
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			upDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow-1).at(i));
		}else {
			forDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow).at(i));
			upDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow-1).at(i));
			downDif = abs(input.at(currentRow).at(i-1)-input.at(currentRow+1).at(i));
		}	
	//Painting Path
		
		if(currentRow == 0){
			if(forDif <= downDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow++;
				counter[startRow] += downDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}else if (currentRow == row-1){
			if(forDif <= upDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow--;
				counter[startRow] += upDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}else{
			if(findMin(upDif, forDif, downDif) == forDif){
				counter[startRow] += forDif;
				output.at(currentRow).at(i) = colorCode;
			}else if(findMin(upDif, forDif, downDif) == downDif){
				currentRow++;
				counter[startRow] += downDif;
				output.at(currentRow).at(i) = colorCode;
			}else{
				currentRow--;
				counter[startRow] += upDif;
				output.at(currentRow).at(i) = colorCode;
			}
		}
	}
}


int main(int argc, char *argv[]) {
	//Variables
	int col = 0;
	int row = 0;
	int max = 0;
	int min = 0;
	int currentRow = 0;
	double scale = 0.0;
	string fileName = "";
	vector< vector<int> > vInput;
	vector< vector<string> > vOutput;
	vector<int> vCounter;
	bool userInput = true;

	//Program Mode
	if(userInput){
		cin >> row;
		cin >> col;
		cin >> fileName;
	}else{
		row = 480;
		col = 844;
		fileName = "files/map-input-844-480.dat";
	}
	
	//Vector Sizes
	vCounter.resize(row);
	vInput.resize(row);
	for (int i = 0; i < row; ++i){
	    vInput.at(i).resize(col);
	}
	
	vOutput.resize(row);
	for (int i = 0; i < row; ++i){
		vOutput.at(i).resize(col);
	}
	
	
	//Read From File >> Vector
	ifstream myfile (fileName);
	if (myfile.is_open()){
		for(int i=0;i<row;i++){
			for(int s=0;s<col;s++){
				myfile >> vInput.at(i).at(s);
			}
		}
	    myfile.close();
	}else cout << "Unable to open input file"; 
	

	//Paint Paths
	for (int i=0;i<row;i++){
		paintPath(vInput, vOutput, vCounter, i, row, col, "252 25 63 ");
	}
	currentRow = findShortest(vCounter);
	paintPath(vInput, vOutput, vCounter, currentRow, row, col, "31 253 13 ");

	
	//Scale Vectors
	max = vInput[0][0];
	min = vInput[0][0];
	for (int i=0;i<row;i++) {
		for (int s=0;s<col;s++) {
			if(vInput[i][s] > max){
				max = vInput.at(i).at(s);
			} 
			if(vInput[i][s]<min){
				min = vInput.at(i).at(s);
			}
		}
	}
	
	scale = (max-min)/255.0;
	
	for (int i=0;i<row;i++) {
		for (int s=0;s<col;s++) {
			if(vOutput.at(i).at(s) == ""){
				int temp = ((vInput.at(i).at(s)-min)/scale) + .5;
				if(temp > 255){
					temp = 255;
				}
				vInput.at(i).at(s) = temp;
				string strTemp = std::to_string(vInput.at(i).at(s));
				vOutput.at(i).at(s) = strTemp + " " + strTemp + " " + strTemp + " ";
			}
		}
	}
	
	
	//Output Map
	ofstream outputFile;
	outputFile.open(fileName + ".ppm");
	if (outputFile.is_open()){
		
		outputFile << "P3" << endl;
		outputFile << col << " " << row << endl;
		outputFile << "255" << endl;
		
		for(int i=0;i<row;i++){
			for(int s=0;s<col;s++){
				outputFile << vOutput.at(i).at(s) << " ";
				if(s%20==0){
					outputFile << endl;
				}
			}
		}
	    outputFile.close();
	}else cout << "Unable to open output file"; 
		
	return 0;

}