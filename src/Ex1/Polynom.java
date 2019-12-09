package Ex1;

import java.util.ArrayList;
import java.util.Iterator;



/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 *
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

    public static boolean DBUG = false;

    private ArrayList<Monom> myList;

	/**
	 * A default constructor that initialize a Zero-Polynom (empty polynom, 0 Monoms)
	 */
	public Polynom() {
		myList = new ArrayList<>();
	}
	/**
	 * Constructor that initialize Polynom from a given string such as:
	 * {"x", "3+1.4X^3-34x", "15*X+6-18x", "-3x^5"};
	 * the string must be without spaces!
	 * The final initialized Polynom will be without duplicates of Monom's powers, and without 0 Monoms.
	 * @param s: is the given string represents a Polynom.
	 */
	public Polynom(String s) {

	    if (DBUG){
            System.out.println("DBUG polynom status: " + "try to create a new polynom with " + s);
        }
		myList = new ArrayList<>();
		runOnStringToFindsMonom(s);

        if (DBUG){
            System.out.println("DBUG polynom status: " + "success to create polynom with: " + s);
        }
		this.getRidOf0();
	}
	/**
	 * Calculate the value obtained for setting a given x in the Polynom - f(x).
	 */
	@Override
	public double f(double x) {

		double sum = 0;
		for (int i = 0; i<myList.size();i++){
			sum = sum + myList.get(i).f(x);
		}
        if (DBUG){
            System.out.println("DBUG polynom status: " + "the f("+x+") = "+sum+" of the polynom: "+this);
        }
		return sum;
	}

	@Override
	public function initFromString(String s) {
		return new Polynom(s);
	}

	/**
	 * Method that adds Polynom p1 to this Polynom.
	 * @param p1 = the given Polynom the user wants to add.
	 */
	@Override
	public void add(Polynom_able p1) {
        			if (DBUG){
            System.out.println("DBUG polynom status: " + "try to add "+ p1+" with "+this);
        	}
        Polynom_able copy = (Polynom_able) p1.copy();
	    Iterator<Monom> iterator = copy.iteretor();
	    while (iterator.hasNext()){
	    	Monom m1 = iterator.next();
	    			if (DBUG){
                System.out.println("DBUG polynom status: " + "try to add "+ m1+" with "+this);
            }
	        this.add(m1);
        }
        			if (DBUG){
            System.out.println("DBUG polynom status: " + "results of add polynom: "+this);
        }
        			getRidOf0();

	}
	/**
	 * Method that adds Monom m1 to this Polynom.
	 * @param m1 = the given Monom the user wants to add.
	 */
	@Override
	public void add(Monom m1) {
	    getRidOf0();
        boolean inside = false;
			for (int i = 0; i<myList.size()&&!inside;i++){
				if (myList.get(i).get_power()==m1.get_power()){

                    		if (DBUG){
                        System.out.println("DBUG polynom status: " + " in add Monom to polynom found to add "+ m1+" to "+ myList.get(i));
                    }
                    myList.get(i).add(m1);


					inside = true;
				}
			}


        if (!inside) {
                			if (DBUG){
                    System.out.println("DBUG polynom status: " + " in add Monom to polynom not found a Monom to add so its been added in '+' to the " + this);
                }
				myList.add(m1);
				Monom_Comperator monom_comperator = new Monom_Comperator();
				myList.sort(monom_comperator);
                			if (DBUG){
                    System.out.println("DBUG polynom status: " + " polynom lokk like after sdded the Monom: " + this);
                }
			}
	}
	/**
	 * Method that subtracts p1 from this Polynom.
	 * @param p1 = The polynom that the user wants to subtract.
	 */
	@Override
	public void substract(Polynom_able p1) {
        if (DBUG){
            System.out.println("DBUG polynom status: " + "substrate "+ this+ " by "+ p1);
        }
		if (p1==this){
			p1 = (Polynom_able) p1.copy();

		}
        if (DBUG){
            System.out.println("DBUG polynom status: " + " befor mult by -1 : " +p1  );
        }
		p1.multiply(Monom.MINUS1);
        if (DBUG){
            System.out.println("DBUG polynom status: " + "after mult by -1: " + p1);
        }
		this.add(p1);

        getRidOf0();
		if (this.myList.isEmpty()){
			this.addMonom("0");
		}
        if (DBUG){
            System.out.println("DBUG polynom status: " + " after subtract: " + this );
        }
        getRidOf0();
	}
	/**
	 * Method that multiplies this Polynom by Polynom p1
	 * @param p1 = Polynom_able type of variable, that is given to multiply.
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Polynom temp = this.copyP();
		Polynom final_poly = new Polynom();
		boolean first = true;
		Iterator<Monom> iterator = p1.iteretor();
		while (iterator.hasNext()){
			temp.multiply(iterator.next());
			if (first){
				final_poly = temp.copyP();
				first = false;
			}
			else {
				final_poly.add(temp);
			}
			temp = this.copyP();
		}
		this.myList = final_poly.myList;
		getRidOf0();
	}

	/**
	 * A boolean method that checks if a given Polynom p1 equals to this Polynom.
	 * @param p1 = the given Polynom to compare to.
	 * @return true if this Polynom equals to p1, false else.
	 */
	public boolean equalsPolynom(Polynom p1) {
		getRidOf0();
		if(myList.size() != p1.myList.size()) return false;
		for(int i=0; i<myList.size(); i++) {
			if(!(myList.get(i).equalsMonom(p1.myList.get(i)))) return false;
		}
		return true;
	}

	/**
	 * A boolean method that checks if this Polynom is a zero-Polynom.
	 * @return true if it is, false else.
	 */
	@Override
	public boolean isZero() {
		return this.toString().equals("0");
	}

	/**
	 * The method find the root of the Polynom if exist
	 * If the root does not exist the function throw an Error "Err: root does not exist"
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps 0 (positive) representing the epsilon range the solution should be within.
	 * @return root of the polynom in-epsilon accuracy
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		if (Math.abs(f(x0))<eps)
			return x0;

		if (Math.abs(f(x1))<eps)
			return x1;

		while ( f(x0)*f(x1) < 0 && (Math.abs(f(x0)) > Math.abs(eps) || Math.abs(f(x1)) > Math.abs(eps)) ){

			double mid = (x0+x1)/2.0;

			if (Math.abs(f(mid))<eps)
				return mid;

			if (f(x0)*f(mid) < 0){
				x1 = mid;
			}
			else x0 = mid;

		}
		throw new RuntimeException("Err: root does not exist");
	}
	/**
	 * Method that copies this Polynom and create a new Polynom_able
	 * @return a new Polynom_able same as the original one.
	 */
	@Override
	public Polynom_able copy() {
		Polynom newP = new Polynom();

		for (int i = 0; i < this.myList.size(); i++) {
			Monom temp = new Monom(this.myList.get(i));
			newP.add(temp);
		}

		return newP;
	}
	/**
	 * Method that copies this Polynom and create a new Polynom
	 * @return a new Polynom same as the original one.
	 */
	public Polynom copyP() {
		Polynom newP =  new Polynom();

		for (int i = 0 ; i<this.myList.size();i++){
			Monom temp = new Monom(this.myList.get(i));
			newP.add(temp);
		}

		return newP;
	}

	/**
	 * Method that calculate the derivative of the Polynom;
	 * @return derivative of this Polynom
	 */
	@Override
	public Polynom_able derivative() {
		Polynom newP =  new Polynom();

		for (int i = 0 ; i<this.myList.size();i++){
			Monom temp = new Monom(this.myList.get(i));
			newP.add(temp.derivative());
		}
		return newP;
	}
	/**
	 * Method that calculate the area of the Polynom by x0 , x1 , epsilon
	 * @param x0 = the x value of the left point on the Polynom's graph (starting point)
	 * @param x1 = the x value of the right point on the Polynom's graph (end point)
	 * @param eps = (positive) The required level of accuracy for the area
	 * @return derivative of this Polynom
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		if (x0>x1)
			return 0;

		double tempX = x0;
		double sum = 0;
		while (tempX<x1){
			tempX = tempX+eps;
			double tempY = f(tempX);
			sum = Math.max(sum+((tempX-x0)*(tempY)),sum);
			x0 = tempX;
		}
		return sum;
	}

	/**
	 * We use the regular Iterator of ArrayList;
	 * @return this ArrayList.Iterator
	 */
	@Override
	public Iterator<Monom> iteretor() {
		return myList.iterator();
	}
	/**
	 * Method that multiplies this Polynom by Monom m1
	 * @param m1 = Monom type of variable, that is given to multiply.
	 */
	@Override
	public void multiply(Monom m1) {

        for (int i = 0 ; i<this.myList.size();i++){
            this.myList.get(i).multipy(m1);
        }

	}

	/**
	 * nice function help to solve a lot of bugs.
	 * the private function works on the list that representing the Polynom and delete zero Monoms
	 * If the list is empty (the polynom equals to zero), the function add 1 zero to the list;
	 * in addition sort the list by the Monom Comparator;
	 */
	private void getRidOf0(){
	    for (int i = 0;i<this.myList.size();i++){
	        if (myList.get(i).isZero()){
	            myList.remove(i);
            }
        }
        Monom_Comperator monom_comperator = new Monom_Comperator();
	    myList.sort(monom_comperator);

	    if (myList==null || myList.size()==0){
	    	myList.add(new Monom("0"));
		}
    }

	/**
	 * The Method add a new Monom from a String
	 * @param s = the Monom we want to add, as a string
	 */
	private void addMonom(String s){
		this.add(new Monom(s));
	}

	/**
	 * Private method that helps us to initialize a Polynom from a given string by separating it to Monoms.
	 * @param s = the given string (in the constructor).
	 */
	private void runOnStringToFindsMonom(String s){
		if (s == null || s.length() == 0)
			throw new RuntimeException("input is Empty");
		String temp = "";
		s=s.toLowerCase();
		char c = s.charAt(0);
		boolean minus;
		boolean plus = c=='+';
		boolean isPower = c == '^';
		boolean first = true;

		if (isPower)
			throw new RuntimeException("input is not correct we find '^' at the start");
		int i = 0;
		if(plus)
			i = 1;

		for (; i<s.length(); i++){
			c = s.charAt(i);
			minus = c == '-';
			plus = c == '+';
            isPower = c == '^';

			if (isPower&&i+1<s.length()&&s.charAt(i+1)=='-'){
			    throw new RuntimeException("Eror: power cabt be negetive");

            }
			else if (plus&&first){
                    throw new RuntimeException("Wrong syntax of polynom");
			}
			else if (minus&&first){
				temp = temp+c;
			}
			else if (plus){
				addMonom(temp);
				temp = "";

			}
			else if(minus) {
                addMonom(temp);
                temp =  ""+c;

			}
			else
				temp = temp+c;

			first = false;
		}
		addMonom(temp);
		getRidOf0();
	}

	/**
	 * Method that represents the Polynom as a string (using a private method at Monom's class - toStringToPolynom()).
	 * @return the Polynom as string: "a1x^b1+a2x^b2+...+anx^bn" (n=num of Monoms in the Polynom).
	 */
	@Override
	public String toString(){
		getRidOf0();
        if (DBUG){
            System.out.println("DBUG polynom status: " + " tostring");
        }if (DBUG){
            System.out.println("DBUG polynom status: " + myList.toString());
        }
	    if (myList.size() == 0 ){
	        return "";
        }
	    String temp = myList.get(0).toString();
	    for (int i = 1;i<myList.size();i++){
	        Monom m = myList.get(i);
	        temp =temp + m.toStringToPolynom();
        }
		return temp;
	}
	@Override
	public boolean equals(Object obj){
		getRidOf0();
		if(obj instanceof Polynom) return this.equalsPolynom((Polynom)(obj));
		if(obj instanceof Monom) {
			if(this.myList.size()!=1) return false;
			Monom m = new Monom(myList.get(0));
			return m.equalsMonom((Monom)obj);
		}
		if(obj instanceof ComplexFunction) return ((ComplexFunction)obj).equals(this);
		return false;
	}




}
