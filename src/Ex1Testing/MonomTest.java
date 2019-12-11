import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.*;


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

	@Test
	void derivative() {
		Monom m = new Monom("3x^6");
		Monom mD = new Monom("18x^5");
		assertEquals(m.derivative(), mD);

		m = new Monom("-3");
		mD = new Monom("0");
		assertEquals(m.derivative(), mD);

		m = new Monom("-5.5x");
		mD = new Monom("-5.5");
		assertEquals(m.derivative(), mD);
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
		Monom m = new Monom("7x^10");

		function f = m.initFromString("7x^10");

		assertEquals(m,f);
	}

	@Test
	void copy() {
		Monom m = new Monom("3x^2");
		function f = m.copy();
		assertEquals(m,f);

		m.multipy(new Monom("0"));
		assertNotEquals(m,f);
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
		Monom m1 = new Monom("4x^2");
		Monom m2 = new Monom("-x^2");
		Monom mA = new Monom("3x^2");
		m1.add(m2);
		assertEquals(m1, mA);

		m1 = new Monom("5.5x^4");
		m2 = new Monom("-5.5x^4");
		mA = new Monom("0");
		m1.add(m2);
		assertEquals(m1,mA);

		m1 = new Monom("1.2x^0");
		m2 = new Monom("0.6");
		mA = new Monom("1.8");
		m1.add(m2);
		assertEquals(m1, mA);
	}

	@Test
	void multipy() {
		Monom m1 = new Monom("8x^1");
		Monom m2 = new Monom("2x^2");
		Monom mM = new Monom("16x^3");
		m1.multipy(m2);
		assertEquals(m1,mM);

		m1 = new Monom("-2.5x^0");
		m2 = new Monom("x^9");
		mM = new Monom("-2.5x^9");
		m1.multipy(m2);
		assertEquals(m1,mM);

		m1 = new Monom("-0.5");
		m2 = new Monom("2x^5");
		mM = new Monom("-x^5");
		m1.multipy(m2);
		assertEquals(m1,mM);
	}

	@Test
	void testToString() {
		Monom m = new Monom("2x^4");
		String expected = "2.0x^4";
		assertEquals(expected, m.toString());

		m = new Monom("-1x^7");
		expected = "-1.0x^7";
		assertEquals(expected, m.toString());

		m = new Monom("-9x^0");
		expected = "-9.0";
		assertEquals(expected, m.toString());
	}

	@Test
	void equalsMonom() {
		Monom m1 = new Monom("x^3");
		Monom m2 = new Monom("1.0x^3");
		boolean b = m1.equalsMonom(m2);
		assertTrue(b);
		//true

		m1 = new Monom("-x^0");
		m2 = new Monom("-1");
		b = m1.equalsMonom(m2);
		assertTrue(b);
		//true

		m1 = new Monom("7x^2");
		m2 = new Monom("7.0x^1");
		b = m1.equalsMonom(m2);
		assertFalse(b);
		//false
	}

	@Test
	void testEquals() {
		//Polynom equals to Monom

		Monom m = new Monom("2x^4");
		Polynom p = new Polynom("2x^4");

		assertTrue(m.equals(p));

		//CF equals to Monom

		ComplexFunction cf = new ComplexFunction("Plus(2x^3,x^3)");
		m = new Monom("3x^3");
		assertTrue(cf.equals(m));

		cf = new ComplexFunction("Times(Plus(9x^2,-4x^2-5x^2),2x^3+4)");
		m = new Monom("0");
		assertTrue(cf.equals(m));

		cf = new ComplexFunction("Plus(Divid(3x^3,x^3),8)");
		m = new Monom("3x^3");
		assertFalse(cf.equals(m));
	}
}

