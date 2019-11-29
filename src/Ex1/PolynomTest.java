package Ex1;

public class PolynomTest {
	public static void main(String[] args) {
		polynomTest();
		fTest();
		addPolynom_ableTest();
		addMonomTest();
		substractTest();
		multiplyTest();
		equalsTest();
		isZeroTest();
		rootTest();
		copyTest();
		derivativeTest();
		areaTest();
		toStringTest();
		Polynom p = new Polynom("8x^4-3x^3+2x^2-7x-5");
		System.out.println(p.f(0));
		System.out.println(p.root(0,6,0.0001));




	}





	public static void polynomTest() {
		int expexted = 0;
		int sum = 0;
		Polynom[] p = new Polynom[5];
		String[] s = new String[5];
		s[0] = "x";
		s[1] = "2*x^3+2";
		s[2] = "4x^3+2x+5x^4+2x^5";
		s[3] = "-x^3-2x^2-4x^5";
		s[4] = "x+x^2+x^3+x^4+X^5";
		for (int i = 0; i < p.length; i++) {
			try {
				p[i] = new Polynom(s[i]);
			} catch (Exception e) {
				sum++;
			}
		}
		myAssert(sum == expexted, "fail at " + sum + " attempt create a Polynom", "Polynom test1");
		expexted = 5;
		sum = 0;
		s[0]="3x+-x";
		s[1]="4x^2-+x";
		s[2]="4x+6x&^3";
		s[3]="3^";
		s[4]="1=0x";
		for (int i = 0 ; i < p.length;i++){
		    try{
		        p[i] = new Polynom(s[i]);
                System.out.println(p[i]+", "+ i);
            }catch (Exception e){
		        sum++;

            }
        }
        myAssert(sum == expexted, "fail at " + (expexted-sum) + " attempt create a Polynom", "Polynom test2");


    }
	private static void fTest(){
		Polynom p = new Polynom("x+x^2+x^3");
		double x = 3;
		double y = 39;
		myAssert(p.f(x)==y,"faile at f("+x+")="+p+" = "+p.f(x)+" need to be ="+y,"fTest1");
	}

	private static void addPolynom_ableTest(){
		Polynom p1 = new Polynom("0");
		Polynom p2 = new Polynom("x+x^2-x^3+x^4");
		Polynom p3 = new Polynom("x+x^2-x^3+x^4");
		p2.add(p1);
		myAssert(p2.equals(p3),"fail! at add 0","addPolynomAbleTest1");

		p3 = new Polynom("-x-x^2+x^3-x^4");
		p2.add(p3);
		myAssert(p2.equals(p1),"fail! at add the opposites of himself " ,"addPolynomAbleTest2");
	}
	private static void addMonomTest(){
		Monom m0 = new Monom("x");
		Monom m1 = new Monom("X");
		Monom m2 = new Monom("2x^4");
		Monom m3 = new Monom("-2x^4");
		Monom m4 = new Monom("-x^0");
		Polynom expected = new Polynom("-1+2x");
		Polynom p = new Polynom();
		p.add(m0);
		p.add(m1);
		p.add(m2);
		p.add(m3);
		p.add(m4);
		myAssert(p.equals(expected),"somthing wrong with add"+p,"addMonomTest");
	}
	public static void substractTest(){
		Polynom p = new Polynom("1+2x+3x^2+4x^3");
		Polynom p1 = new Polynom("1+2x-x^2+4x^3");
		Polynom expected = new Polynom("4x^2");
		p.substract(p1);
		myAssert(p.equals(expected),"fail at the Test ","substractTest");
	}
	private static void multiplyTest() {
		Polynom p = new Polynom("x^2-3x");
		Polynom p2 = new Polynom("x");
		Polynom expected = new Polynom("x^3-3x^2");
		p.multiply(p2);
		myAssert(p.equals(expected),"fail!! not like expected","multiplyTest");
	}

	private static void equalsTest() {
		Polynom p = new Polynom("x+3x+4x^2+5x^7-4x^8");
		Polynom copy = new Polynom("4x+4x^2+3x^7-4x^8+2x^7");
		myAssert(p.equals(copy),"fail","equalsTest1");
		p = new Polynom("x");
		copy = new Polynom("X");
		myAssert(p.equals(copy),"fail","equalsTest2");
	}
	private static void isZeroTest(){
		Polynom p = new Polynom("0");
		myAssert(p.isZero(),"fail","isZeroTest1");
		p = new Polynom("0+1-1");
		myAssert(p.isZero(),"fail","isZeroTest2");
		p = new Polynom("0+2x^2-1-x^2-x^2+1");
		myAssert(p.isZero(),"fail","isZeroTest3");


	}
	private static void rootTest(){
		Polynom p = new Polynom ("x^2-6+5x");
		try{
			myAssert(1-p.root(0, 2.7, 0.0001)<0.0001 && 1-p.root(0, 2.7, 0.0001)>0, "fail! didn't find the root "+ p.root(0,2.7,0.0001), "rootTest");
		}catch (Exception e){
			System.out.println("That didn't supposed to happen...");
		}
	}
	private static void copyTest(){
		Polynom p = new Polynom("2x^5+4x-x^3");
		Polynom_able p2 = p.copy();
		myAssert(p.equals(p2),"file, not the same","copyTest1");
		p2.add(p);
		p.add(p);
		myAssert(p.equals(p2),"file, not the same","copyTest2");
	}
	public static void derivativeTest(){
		Polynom p = new Polynom("x+2x^2+3x^3-4x^4");
		Polynom expected = new Polynom("1+4x+9x^2-16x^3");
		Polynom_able deriv = p.derivative();
		myAssert(deriv.equals(expected),"fail!!","derivativeTest");
	}
	public static void areaTest(){
		Polynom p = new Polynom("x^2+3x");
		double expected = 22.5;
		double results =p.area(0,3,0.00001);
		double chack = Math.abs(expected-results);

		myAssert(chack>0&&chack<=0.0001,"fail","areaTest1");

		p = new Polynom("x^2-3x+4x^3");
		expected = 5.0/6.0;
		results =p.area(-1,0.5,0.00001);
		chack = Math.abs(expected-results);

		myAssert(chack>0&&chack<=0.0001,"fail","areaTest2");

	}
	public static void toStringTest(){
		Polynom p = new Polynom("2x^4+4x-3");
		String expected = "2.0x^4+4.0x-3.0";
		myAssert(p.toString().equals(expected),"fail!!!","toStringTest1");
		p = new Polynom("0+1-1+2-2+3x-3x");
		expected = "0";
		myAssert(p.toString().equals(expected),"fail!!!","toStringTest2");
	}

	private static int myAssert(Boolean fals, String warning, String testName) {
		if (!fals) {
			System.out.println(testName + ": " + warning);
		} else System.out.println(testName + ": ALL GOOD");
		return 1;
	}

}
