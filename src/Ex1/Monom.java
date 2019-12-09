
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{

	public static boolean DBUG = false;

	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}

	/**
	 * Constructor that gets coefficient and power and initializing a new Monom.
	 * @param a = coefficient
	 * @param b = power
	 */
	public Monom(double a, int b){
		if (DBUG){
			System.out.println("** new Monom ("+a+", "+b+")");
		}
		this.set_coefficient(a);
		this.set_power(b);
	}

	/**
	 * Copy Constructor performs deep copying and initializing a new Monom.
	 * @param ot is the Monom we want to copy from.
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
		if (DBUG){
			System.out.println("new Monom("+ot+")// copy");
		}
	}

	/**
	 * @return the Monom's coefficient.
	 */
	public double get_coefficient() {
		return this._coefficient;
	}

	/**
	 * @return the Monom's power.
	 */
	public int get_power() {
		return this._power;
	}

	/**
	 * Method that calculate the Monom's derivative.
	 * @return a new Monom representing the original Monom's derivative.
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}

	/**
	 * Calculate the value obtained for setting a given x in the Monom - f(x).
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	}

	@Override
	public function initFromString(String s) {
		return null;
	} //********************

	@Override
	public function copy() {
		return null;
	} //*********************

	/**
	 * A boolean method checking if the Monom represents Zero-Monom.
	 * @return true if the Monom is a zero Monom, false if not.
	 */
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************

	/**
	 * Constructor initializing new Monom from a string.
	 * you can use both 'x' and 'X';
	 * Representation of a power can only be by '^';
	 * you can choose to add '*' between the coefficient to 'x', or not;
	 * @param s = a given string representing the Monom.
	 */
	public Monom(String s) {
		stringBuilder(s);
		if (DBUG){
			System.out.println("***create new monom by String: " +s);
		}
	}

	/**
	 * Method adding a given Monom to this Monom.
	 * @param m  = the given Monom to add.
	 */
	public void add(Monom m) {
		if (this.get_power() != m.get_power()){
			throw new RuntimeException("ERR:the power of the monoms is different");
		}
		else {
			this.set_coefficient(this.get_coefficient() + m.get_coefficient());
			if (DBUG){
				System.out.println("added Monom: "+this+" and "+m );
			}
		}
	}

	/**
	 * Method to multipy a given Monom with this Monom.
	 * @param d = the given Monom to multipy.
	 */
	public void multipy(Monom d) {
		if (DBUG){
			System.out.println("befor mulyt: "+ this+ "by "+ d );
		}
		set_coefficient(this.get_coefficient()*d.get_coefficient());
		set_power(this.get_power()+d.get_power());
		if (DBUG){
			System.out.println("multy result: " + this );
		}
	}

	/**
	 * Method representing Monom like string: ax^b
	 * when a = Monom's coefficient, b = Monom's power.
	 */
	public String toString() {
		if (this.get_coefficient() == 0) {
			return "0";
		}
		if (this.get_power() == 0){
			return this.get_coefficient()+"";
		}
		if (this.get_coefficient() == 1 && this.get_power() == 1){
			return "x";
		}
		if (this.get_coefficient() == 1){
			return "x^"+this.get_power();
		}
		if (this.get_power() == 1){
			return this.get_coefficient() + "x";
		}
		return this.get_coefficient()+"x^"+this.get_power();
	}

	//****************** Private Methods and Data *****************

	/**
	 * Setting the coefficient of the Monom.
	 * @param a = the value of coefficient.
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}

	/**
	 * Setting the power of the Monom.
	 * @param p = the value of power - must be non-negative!
	 */
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}

	/**
	 * @return new zero monom : a = 0 , b = 0
	 */
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}

	private double _coefficient; 
	private int _power;

	//***************** Private Function ***********************

	/**
	 * A side function that helps the constructor from string.
	 * The method recognizes the Monom's coefficient and power from the given string.
	 * The method also decides if the string is legal or not.
	 * In case that the string is illegal the method will throw an Error.
	 * @param s = the given string.
	 */
	private void stringBuilder(String s){
			if(!(s == null)&&(s.equals("-")||s.equals("+")))
				throw new RuntimeException("invalid syntax of Monom");
            String a = "";
            String b = "";
            boolean flagZero = false;
            boolean one = false;
            if (s == null || s.length() == 0){
                throw new RuntimeException("invalid syntax of Monom");
            }
            if(s.charAt(0) == '0') {
                if(s.length() == 1 || s.charAt(1) == 'x' || s.charAt(1) == 'X') {
                    this.set_coefficient(0);
                    flagZero = true;
                }
            }
            if(s.charAt(0) == 'x' || s.charAt(0) == 'X') {
            	one = true;
				this.set_coefficient(1);
			}

            int i = 0;
            while(i < s.length() && s.charAt(i) != 'x' && s.charAt(i) != 'X') {
                a+=s.charAt(i);
                i++;
            }
            if(i == s.length())
                this.set_power(0);
            else {
                if(i == s.length()-1)
                    this.set_power(1);
                else {
                    if(s.charAt(i+1) != '^') {
						throw new RuntimeException("invalid syntax of Monom");
					}
                    else {
                        int j = i+2;
                        while(j < s.length()) {
                            b+=s.charAt(j);
                            j++;
                        }
                    }
                }
            }
            //set_coefficient (using String a)
            if(!a.isEmpty()) {
                if(a.charAt(a.length()-1) == '*') a = a.substring(0, a.length() - 1);
                if(a.length() == 1 && a.charAt(0) == '-') this.set_coefficient(-1);
                else {
                    try {
                        this.set_coefficient(Double.parseDouble(a));
                    } catch (Exception e) {
                        throw new RuntimeException("invalid syntax of Monom");

                    }
                }
            }
            //set_power (using String b)
            if(!b.isEmpty()) {
                try {
                    int p = Integer.parseInt(b);
                    this.set_power(p);
                } catch (Exception e) {
                    throw new RuntimeException("invalid syntax of Monom");

                }
            }
            if (one){
            	this._coefficient = 1;
			}
            if (flagZero) {
				this.set_power(0);
			}
        }

	/**
	 * The method creates a string for the Polynom to fix the regular toString that prints a string without '+'.
	 * @return Monom string with sign like '-' or '+'.
	 */
	public String toStringToPolynom() {
		if (this.get_coefficient() >= 0) {
			return "+" + this.toString();
		} else return toString();
	}

	public boolean equalsMonom(Monom m) {
		if(m == null && this == null) return true;
		else if (m == null)
			return false;
		else
			return Math.abs(((Monom) m)._coefficient - this._coefficient) <= EPSILON && ((Monom) m)._power == this._power;
	}
@Override
	public boolean equals(Object obj) {
		if(obj instanceof Monom) return equalsMonom((Monom)obj);
		if(obj instanceof Polynom) return ((Polynom) obj).equals(this);
		if(obj instanceof ComplexFunction) return ((ComplexFunction)obj).equals(this);
		return false;
	}

}
