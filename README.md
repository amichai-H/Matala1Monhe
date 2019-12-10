# Matala1Monhe
Authors:
Hila Shoshan 
Amichai Hadad 
_____________________________________________________________________
# Monom class:
This class represents a simple "Monom" of shape a*x^b, where a is a real number and b is an integer (summed a none negative).
The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. The fields of the class are: _coefficient: double (a), _power: positive integer (b).
We will explain briefly what every method is:
1. public Monom(double a, int b)
Constructor that gets coefficient (a) and power (b) and initializing a new Monom.
2. public Monom(Monom ot)
Copy Constructor that performs deep copying from Monom ot, and initializing a new Monom.
3. public double get_coefficient()
returns the Monom's coefficient.
4. public int get_power()
returns the Monom's power.
5. public Monom derivative()
Method that returns a new Monom which representing the original Monom's derivative.
6. public double f(double x)
Calculate the value obtained for setting a given x in the Monom - f(x).
7. public boolean isZero()
A boolean method that checks if the Monom represents Zero-Monom.
8. public Monom(String s)
Constructor that initializing new Monom from a given string (s).
-you can use both 'x' and 'X';
-Representation of a power can only be by '^';
9. -you can choose to add '*' between the coefficient to 'x', or not;.
10. public void multipy(Monom d)
Method that multiplies a given Monom (d) with this Monom.
11. public String toString()
Method representing Monom like string: ax^b
when a = Monom's coefficient, b = Monom's power.
12. private void set_coefficient(double a)
Setting the coefficient (a) of the Monom.
13. private void set_power(int p)
Setting the power (p) of the Monom. Must be non-negative value!
14. private static Monom getNewZeroMonom()
Method that return new zero Monom: 0x^0.
15. private void stringBuilder(String s)
A side function that helps the constructor from string.
The method recognizes the Monom's coefficient and power from the given string (s).
The method also decides if the string is legal or not.
In case that the string is illegal, the method will throw an Error.
16. public String toStringToPolynom()
The method creates a string for the Polynom to fix the regular toString that prints a string without '+'.
It returns Monom string with sign like '-' or '+' (to help concatenate the Monoms later).
17. public boolean equals(Object m)
A Boolean method that states if 2 Monoms are equal.
2 Monoms are equal iff both coefficients and powers are equal.
# Polynom class:
This class represents a Polynom, in form of a1x^b1+a2x^b2+…+anx^bn, where: a_1, a_2 ... a_n are real numbers and b_1 b_2.. b_n are none negative integers (naturals).
We choose to represent the Polynom with an ArrayList of Monoms.
The class implements the interface Polynom_able, so contains methods as add Monom or Polynom, multiply Monom or Polynom, it also supports the following:
1. Riemann's Integral
2. Finding a numerical value between two values (currently support root only f(x)=0).
3. Derivative
We will explain briefly what every method is:
1. public Polynom()
A default constructor that initialize a Zero-Polynom (empty polynom, 0 Monoms).
2. public Polynom(String s)
Constructor that initialize Polynom from a given string (s) such as:
{"x", "3+1.4X^3-34x", "15*X+6-18x", "-3x^5"};
the string must be without spaces!
The final initialized Polynom will be without duplicates of Monom's powers, and without 0 Monoms.
3. public double f(double x)
Calculate the value obtained for setting a given x in the Polynom - f(x).
4. public void add(Polynom_able p1)
Method that adds Polynom p1 (is given by the user) to this Polynom.
5. public void add(Monom m1)
Method that adds Monom m1 (is given by the user) to this Polynom.
6. public void substract(Polynom_able p1)
Method that subtracts p1 from this Polynom.
7. public void multiply(Polynom_able p1)
Method that multiplies this Polynom by Polynom p1
*p1 =is Polynom_able type of variable, that is given to multiply.
8. public boolean equals(Polynom_able p1)
A boolean method that checks if a given Polynom p1 equals to this Polynom.
8. public boolean equals(Polynom_able p1)
A boolean method that checks if a given Polynom p1 equals to this Polynom.
9. public boolean isZero()
A boolean method that checks if this Polynom is a zero-Polynom.
10. public double root(double x0, double x1, double eps)
The method finds the root of the Polynom, if exist, in-epsilon accuracy.
If the root does not exist the function throw an Error "Err: root does not exist"
x0 = starting point, x1 = end point
eps is a positive value, representing the epsilon range the solution should be within.
11. public Polynom_able copy()
Method that copies this Polynom and create a new Polynom_able, and returns a new Polynom_able same as the original one.
12. public Polynom copyP()
same as 11, but returns Polynom (not Polynom_able type).
13. public Polynom_able derivative()
Method that calculates and returns the derivative of the Polynom;
14. public double area(double x0, double x1, double eps)
Method that calculate the area of the Polynom by x0 , x1 , epsilon
x0 = the x value of the left point on the Polynom's graph (starting point)
x1 = the x value of the right point on the Polynom's graph (end point)
eps = (positive) The required level of accuracy for the area.
15. public Iterator<Monom> iteretor()
We use the regular Iterator of ArrayList;
16. public void multiply(Monom m1)
Method that multiplies this Polynom by Monom m1
17. private void getRidOf0()
A nice private function that helps to solve a lot of bugs.
It works on the list that representing the Polynom and delete zero Monoms.
If the list is empty (the polynom equals to zero), the function add 1 zero to the list;
in addition, it sorts the list by the Monom Comparator;
18. private void addMonom(String s)
The Method adds a new Monom from a String.
19. private void runOnStringToFindsMonom(String s)
Private method that helps us to initialize a Polynom from a given string (s) by separating it to Monoms.
20. public String toString()
Method that represents the Polynom as a string (using a private method at Monom's class - toStringToPolynom()).
The method returns the Polynom as string: "a1x^b1+a2x^b2+...+anx^bn" (n=num of Monoms in the Polynom).

# Complex Function class:
This class represent a complex function ass function * function , function / function ,
Max(function,function) , Min(function,function) , function(function) , function + function , function - function

function : Monom or Polynom or Complex Function.

To create a complex function you need to rwite in string in the right syntax.
Example: "plus(2x,3x)" , "min(plus(3x,max(x^2,2x)),4x)"
to be more accurate we define it like that -> opertion(function,function)
Lets define opertion: mul, div, sub, plus, min, max, comp

# Function Gui
1. This class will open first axis graph and draw the function that you add with the method add.
To use the Draw function you can write in a text file function in JSON format "index":"function",
and use the method initFromFile.
2. Example:
3. "
{
"0":"-1.0x^4+2.4x^2+3.1","1":"0.1x^5-1.2x+5.0"
,"2":"Plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2x+5.0)"
,"3":"Plus(Divid(x+1.0,Times(Times(x+3.0,x-2.0),x-4.0)),2.0)"
,"4":"Divid(Plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2x+5.0),-1.0x^4+2.4x^2+3.1)"
} 
"

4. And you can Draw function From  text file
5. Exmple:
"
{
	"Width":1000,
	"Height":600,
	"Resolution":1000,
	"Range_X":[-10,10],
	"Range_Y":[-5,10]
}
"
