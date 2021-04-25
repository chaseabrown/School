
#ifndef FUNCTIONS_H
#define FUNCTIONS_H

#include <string>
#include <iostream>

class functions {
public:
    functions();
    void printUsageInfo(std::string executable);
    bool isPalindrome(std::string input, bool caseSensitive, bool spaces);
};

#endif