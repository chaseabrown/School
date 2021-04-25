#include "functions.h"
#include <iostream>
#include <vector>
#include <string>
using namespace std;

functions::functions(){}

void functions::printUsageInfo(string executable){
    cout << "Usage: " << executable << endl;
    cout << "-c: case sensitivity turned on" << endl;
    cout << "-s: ignoring spaces turned off" << endl;
    return;
}

bool functions::isPalindrome(string input, bool caseSensitive, bool spaces){
    vector<string> word;
    word.resize(input.size());
    bool hasCoverted = false;
    string temp = "";
    
    
    if(input.size() == 0){
        return true;
    }
    
    if(!caseSensitive && !spaces){
        for(int i=0;i<word.size();i++){
            temp = tolower(input[i]);
            if(temp != " "){
                word[i] = temp;
            }
        }
        hasCoverted = true;
    }else{
        if(!caseSensitive){
            for(int i=0;i<word.size();i++){
                word[i] = tolower(input[i]);
            }
            hasCoverted = true;
        }else if(!spaces){
            for(int i=0;i<word.size();i++){
                temp = input[i];
                if(temp != " "){
                    word[i] = temp;
                }
            }
            hasCoverted = true;
        }
    }
    
    if(!hasCoverted){
        for(int i=0;i<word.size();i++){
            word[i] = input[i];
        }
    }
    temp = "";
    if(word[0] == word[word.size()-1]){
        for(int s=1;s<word.size()-1;s++){
            temp += word[s];
        }
        isPalindrome(temp,caseSensitive,spaces);
    }else{
        return false;
    }
    
} 