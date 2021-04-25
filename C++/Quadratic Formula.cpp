#include <iostream>
#include <cmath>
using namespace std;

int main(void)
{
    double a, b, c;

    cin>>a;
    cin>>b;
    cin>>c;

	cout << a << "x^2 + " << b << "x + " << c << " = 0" << endl;

	if (a == 0){
		cout<<"Unable to determine root(s)." << endl;
				return 0;
	}
		
	double temp = (b*b)-(4*a*c);

	if(temp >= 0){
		double x[2];
		temp = sqrt(temp);
		x[0] = (-b+temp)/(2*a);
		x[1] = (-b-temp)/(2*a);

		cout<<"X = "<< x[1] << endl;
		cout<<"X = "<< x[0] << endl;
	}else{
		double r;
		double i;
		temp = temp*-1;
		temp = sqrt(temp);
		
		r = -b/(2*a);
		i = temp/(2*a);
		
		cout<<"X = "<< r << " - " << i << "i" << endl;
		cout<<"X = "<< r << " + " << i << "i" << endl;
	}
		
	return 0;
}