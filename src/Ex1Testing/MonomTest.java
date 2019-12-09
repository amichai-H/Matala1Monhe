import Ex1.Monom;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonomTest {
	/**
	@Test
	public static void main(String[] args) {

		myMonomTest();
		derivativeTest();
		fTest();
		isZeroTest();
		addTest();
		multipyTest();
		toStringTest();


	}
	 **/
	/*

	@Test
	public void isZeroTest(){
		Monom m = new Monom("0");
		myAssert(m.isZero(),m +"Err: is zero","isZeroTest");

	}
	@Test
	public void addTest(){
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
	@Test
	public void multipyTest() {
		Monom m = new Monom("2x^5");
		Monom m2 = new Monom("4x^2");
		Monom mMulM2 = new Monom("8x^7");
		m.multipy(m2);
		myAssert(mMulM2.equals(m), "fail!! ", "multipyTest");
	}

	@Test
	public void toStringTest(){
		Monom m = new Monom("3");
		Monom m1 = new Monom("3x^2");
		Monom m2 = new Monom("x^4");
		Monom mm = new Monom(m.toString());
		Monom mm1 = new Monom(m1.toString());
		Monom mm2 = new Monom(m2.toString());

		myAssert(m.equals(mm),"problem with string of: "+m,"toStringTest1");
		myAssert(m1.equals(mm1),"problem with string of: "+m1,"toStringTest2");
		myAssert(m2.equals(mm2),"problem with string of: "+m2,"toStringTest3");

	} */

	@Test
	void derivative() {
		Monom m0 = new Monom("3x^6");
		Monom m1 = new Monom("18x^5");

		Monom m2 = new Monom("-3");
		Monom m3 = new Monom("0");

		Monom m4 = new Monom("-5.5x");
		Monom m5 = new Monom("-5.5");

		Monom[] m = {m0,m2,m4};
		Monom[] expected = {m1,m3,m5};

		for(int i=0; i<expected.length; i++) {
			m[i].derivative();
			assertEquals(m[i], expected[i]);
		}
	}

	@Test
	void f() {
		Monom m = new Monom("-3.2x^4");
		double[] x_values = {0,1,6,-2};
		double[] expected_y_values = {0,-3.2,-4147.2,-51.2};
		for(int i=0; i<x_values.length; i++) {
			double actual_y = m.f(x_values[i]);
			assertEquals(actual_y, expected_y_values[i]);
		}
	}

	@Test
	void initFromString() {

	}

	@Test
	void copy() {
	}

	@Test
	void isZero() {
		Monom m0 = new Monom("0");
		Monom m1 = new Monom("0x");
		Monom m2 = new Monom("9x^0");
		Monom m3 = new Monom("0x^5");
		Monom [] m = {m0,m1,m2,m3};
		boolean [] expected = {true,true,false,true};
		for(int i=0; i<m.length; i++){
			boolean actual = m[i].isZero();
			assertEquals(actual, expected[i]);
		}
	}

	@Test
	void add() {
	}

	@Test
	void multipy() {
	}

	@Test
	void testToString() {
	}

	@Test
	void toStringToPolynom() {
	}

	@Test
	void equalsMonom() {
	}

	@Test
	void testEquals() {
	}
}

