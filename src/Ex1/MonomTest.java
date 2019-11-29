package Ex1;
import java.util.ArrayList;
/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
*****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest {
	public static void main(String[] args) {
		myMonomTest();
		derivativeTest();
		fTest();
		isZeroTest();
		addTest();
		multipyTest();
		toStringTest();



	}


	public static void myMonomTest(){
		int failSum = 0;
		String s[] = new String[5];
			s[0] = "a";
			s[1] = "x^-3";
			s[2] = "76^5";
			s[3] = "3x+2x";
			s[4] = "--34";
			Monom[] m = new Monom[5];
			for (int i= 0; i<s.length;i++){
				try {
					m[i] = new Monom(s[i]);
				}catch (Exception e){
						failSum++;
				}
			}
			myAssert(failSum == 5,"somthing wrong with the input","myMonomTest");


	}

	public static void derivativeTest(){
		Monom m = new Monom("-4x^4");
		Monom mDer = new Monom("-16x^3");
		myAssert(mDer.equals(m.derivative()),"derivativeTest FAIL " + m +" derivative: " +m.derivative(),"derivativeTest");

	}
	public static void fTest() {
		Monom m = new Monom("3x^4");
		double y = 3;
		double x = 1;
		myAssert(m.f(x)== y,"fail at f("+x+")="+ m +"="+m.f(x)+" need tp be : "+y,"fTest");

	}
	public static void isZeroTest(){
		Monom m = new Monom("0");
		myAssert(m.isZero(),m +"Err: is zero","isZeroTest");

	}
	public static void addTest(){
		Monom m1 = new Monom("3*x^2");
		Monom m2 = new Monom("4x^2");
		Monom m1p2 = new Monom("7x^2");
		m1.add(m2);
		myAssert(m1.equals(m1p2),"problem with add function","addTest1");

		try{
			Monom m3 = new Monom("x^5");
			Monom m4  = new Monom("x^3");
			m3.add(m4);
			myAssert(false,"the system added an unadded monom","addTest2");
		} catch (Exception e){
			myAssert(true,"","addTest2");

		}

	}
	public static void multipyTest(){
		Monom m = new Monom("2x^5");
		Monom m2 = new Monom("4x^2");
		Monom mMulM2 = new Monom("8x^7");
		m.multipy(m2);
		myAssert(mMulM2.equals(m),"fail!! ","multipyTest");
	}
	public static void toStringTest(){
		Monom m = new Monom("3");
		Monom m1 = new Monom("3x^2");
		Monom m2 = new Monom("x^4");
		Monom mm = new Monom(m.toString());
		Monom mm1 = new Monom(m1.toString());
		Monom mm2 = new Monom(m2.toString());

		myAssert(m.equals(mm),"problem with string of: "+m,"toStringTest1");
		myAssert(m1.equals(mm1),"problem with string of: "+m1,"toStringTest2");
		myAssert(m2.equals(mm2),"problem with string of: "+m2,"toStringTest3");

	}

	private static int myAssert(Boolean fals, String warning, String testName){
		if (!fals){
			System.out.println(testName+": "+warning);
		}
		else System.out.println(testName+": ALL GOOD");
		return 1;
	}


}

