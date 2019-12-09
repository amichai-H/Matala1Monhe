import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

class PolynomTest {

    @Test
    void f() {
        Polynom p = new Polynom("x+x^2+x^3");
        double[] x_values = {3,0,-7,5.5,4.9,-18};
        double[] expected_y_values = {39,0,-301,202.125,146.559,-5526};
        for(int i=0; i<x_values.length; i++) {
            double actual_y = p.f(x_values[i]);
            assertEquals(actual_y, expected_y_values[i]);
        }
    }

    @Test
    void initFromString() {

    }

    @Test
    void add_polynom_able() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Polynom p1 = new Polynom("-2x^6-5");
        Polynom p0Ap1 = new Polynom("x^6+4x");

        Polynom p2 = new Polynom("0");
        Polynom p3 = new Polynom("15x-3");
        Polynom p2Ap3 = new Polynom("15x-3");

        Polynom p4 = new Polynom("-9+x^2");
        Polynom p5 = new Polynom("-x^2+4.5x^3");
        Polynom p4Ap5 = new Polynom("4.5x^3-9");

        Polynom_able[] p = {p0,p1,p2,p3,p4,p5};
        Polynom[] expected = {p0Ap1,p2Ap3,p4Ap5};

        for(int i=0; i<expected.length; i=i+2) {
            p[i].add(p[i+1]);
            assertEquals(p[i], expected[i]);
        }
    }

    @Test
    void add_monom() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Monom m0 = new Monom("2.5x");
        Polynom p0Am0 = new Polynom("3x^6+6.5x+5");

        Polynom p1 = new Polynom("0");
        Monom m1 = new Monom("-7x^8");
        Polynom p1Am1 = new Polynom("-7x^8");

        Polynom p2 = new Polynom("-9+x^2");
        Monom m2 = new Monom("-x^2");
        Polynom p2Am2 = new Polynom("-9");

        Polynom[] p = {p0,p1,p2};
        Monom[] m = {m0,m1,m2};
        Polynom[] expected = {p0Am0,p1Am1,p2Am2};

        for(int i=0; i<expected.length; i++) {
            p[i].add(m[i]);
            assertEquals(p[i], expected[i]);
        }
    }

    @Test
    void substract() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Polynom p1 = new Polynom("-2x^6-5");
        Polynom p0Sp1 = new Polynom("5x^6+4x+10");

        Polynom p2 = new Polynom("0");
        Polynom p3 = new Polynom("15x-3");
        Polynom p2Sp3 = new Polynom("-15x+3");

        Polynom p4 = new Polynom("-9+x^2");
        Polynom p5 = new Polynom("-x^2+4.5x^3");
        Polynom p4Sp5 = new Polynom("2x^2-4.5x^3-9");

        Polynom_able[] p = {p0,p1,p2,p3,p4,p5};
        Polynom[] expected = {p0Sp1,p2Sp3,p4Sp5};

        for(int i=0; i<expected.length; i=i+2) {
            p[i].substract(p[i+1]);
            assertEquals(p[i], expected[i]);
        }
    }

    @Test
    void multiply_polynom() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Polynom p1 = new Polynom("1");
        Polynom p0Mp1 = new Polynom("3x^6+5+4x");

        Polynom p2 = new Polynom("0");
        Polynom p3 = new Polynom("15x-3");
        Polynom p2Mp3 = new Polynom("0");

        Polynom p4 = new Polynom("-9+x^2");
        Polynom p5 = new Polynom("5x+2");
        Polynom p4Mp5 = new Polynom("-45x-18+5x^3+2x^2");

        Polynom_able[] p = {p0,p1,p2,p3,p4,p5};
        Polynom[] expected = {p0Mp1,p2Mp3,p4Mp5};

        for(int i=0; i<expected.length; i=i+2) {
            p[i].multiply(p[i+1]);
            assertEquals(p[i], expected[i]);
        }
    }

    @Test
    void equalsPolynom() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Polynom p1 = new Polynom("2x+2x+5-x+3x^6+x");
        //true

        Polynom p2 = new Polynom("5x+6+10x-9");
        Polynom p3 = new Polynom("15x-3+0");
        //true

        Polynom p4 = new Polynom("-9+x^2");
        Polynom p5 = new Polynom("-9+x^2+x^2");
        //false

        Polynom[] p = {p0,p1,p2,p3,p4,p5};
        boolean[] expected = {true,true,false};

        for(int i=0; i<expected.length; i=i+2) {
            boolean b = p[i].equalsPolynom(p[i+1]);
            assertEquals(b, expected[i]);
        }
    }

    @Test
    void isZero() {
        Polynom p1 = new Polynom("0"); //t
        Polynom p2 = new Polynom("0+2x-5x^1-2x"); //f
        Polynom p3 = new Polynom("17x^7-2+2-16x^7-x^7+5x-2x-3x"); //t

        Polynom[] p ={p1,p2,p3};
        boolean[] expected = {true,false,true};

        for(int i=0; i<p.length; i++) {
            boolean b = p[i].isZero();
            assertEquals(b, expected[i]);
        }
    }

    @Test
    void area() {
        Polynom p = new Polynom("x^2+3x");
        double expected = 22.5;
        double results = p.area(0,3,0.00001);
        double check = Math.abs(expected-results);
        boolean b = (check>=0) && (check <= 0.00001);

        assertEquals(b, true);

        p = new Polynom("x^2-3x+4x^3");
        expected = 5.0/6.0;
        results =p.area(-1,0.5,0.00001);
        check = Math.abs(expected-results);
        // b = b from the first check

        assertEquals(b, true);
    }

    @Test
    void root() {
        Polynom p = new Polynom ("x^2-6+5x");
        double expected = 1;
        double result = p.root(0, 2.7, 0.0001);
        double check = Math.abs(expected-result);
        boolean b = (check>=0) && (check <= 0.0001);

        assertEquals(b, true);
    }

    @Test
    void derivative() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Polynom p1 = new Polynom("18x^5+4");

        Polynom p2 = new Polynom("5x+6+10x-9");
        Polynom p3 = new Polynom("15");

        Polynom p4 = new Polynom("-9+5.5x^3");
        Polynom p5 = new Polynom("16.5x^2");

        Polynom[] p = {p0,p2,p4};
        Polynom[] expected = {p1,p3,p5};

        for(int i=0; i<expected.length; i++) {
            p[i].derivative();
            assertEquals(p[i], expected[i]);
        }
    }

    @Test
    void multiply_monom() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Monom m0 = new Monom("x");
        Polynom p0Mm0 = new Polynom("3x^7+5x+4x^2");

        Polynom p1 = new Polynom("0");
        Monom m1 = new Monom("-7x^8");
        Polynom p1Mm1 = new Polynom("0");

        Polynom[] p = {p0,p1};
        Monom[] m = {m0,m1};
        Polynom[] expected = {p0Mm0,p1Mm1};

        for(int i=0; i<expected.length; i++) {
            p[i].multiply(m[i]);
            assertEquals(p[i], expected[i]);
        }
    }

    @Test
    void testToString() {
        Polynom p = new Polynom("2x^4+4x-3");
        String expected = "2.0x^4+4.0x-3.0";

        assertEquals(expected, p.toString());

        p = new Polynom("0+1-1+2-2+3x-3x");
        expected = "0";

        assertEquals(expected, p.toString());

        p = new Polynom("2x-2x+3x^3");
        expected = "3.0x^3";

        assertEquals(expected, p.toString());
    }

    @Test
    void equals() {
        //Monom equals to Polynom

        Polynom p = new Polynom("2x^4");
        Monom m = new Monom("2x^4");

        assertEquals(p.equals(m), true);

        p = new Polynom("3x+5-5");
        m = new Monom("3x");

        assertEquals(p.equals(m), true);

        p = new Polynom("3x^5+2-1");
        m = new Monom("3x^5");

        assertEquals(p.equals(m), false);

        //CF equals to Polynom

        ComplexFunction cf = new ComplexFunction("");
    }

    @Test
    void copy() {

    }

    @Test
    void copyP() {

    }

}