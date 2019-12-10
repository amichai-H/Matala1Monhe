import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import Ex1.function;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.*;

class PolynomTest {

    @Test
    void f() {
        Polynom p = new Polynom("x+x^2+x^3");
        double[] x_values = {3,0,-7,5.5,4.9,-18};
        double[] expected_y_values = {39,0,-301,202.125,146.559,-5526};
        for(int i=0; i<x_values.length; i++) {
            double actual_y = p.f(x_values[i]);
            assertTrue(Math.abs(actual_y-expected_y_values[i])<0.0001);
        }
    }

    @Test
    public void initFromString() {
        Polynom p = new Polynom("3x+4");

        function f = p.initFromString("3x+4");

        assertEquals(f,p);
    }

    @Test
    void add_polynom_able() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Polynom p1 = new Polynom("-2x^6-5");
        p0.add(p1);
        Polynom p0Ap1 = new Polynom("x^6+4x");
        assertEquals(p0Ap1,p0);

        Polynom p2 = new Polynom("0");
        Polynom p3 = new Polynom("15x-3");
        Polynom p2Ap3 = new Polynom("15x-3");
        p2.add(p3);
        assertEquals(p2Ap3, p2);

        Polynom p4 = new Polynom("-9+x^2");
        Polynom p5 = new Polynom("-x^2+4.5x^3");
        p4.add(p5);
        Polynom p4Ap5 = new Polynom("4.5x^3-9");
        assertEquals(p4Ap5, p4);



    }

    @Test
    void add_monom() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Monom m0 = new Monom("2.5x");
        Polynom p0Am0 = new Polynom("3x^6+6.5x+5");
        p0.add(m0);
        assertEquals(p0Am0,p0);

        Polynom p1 = new Polynom("0");
        Monom m1 = new Monom("-7x^8");
        Polynom p1Am1 = new Polynom("-7x^8");
        p1.add(m1);
        assertEquals(p1Am1,p1);


        Polynom p2 = new Polynom("-9+x^2");
        Monom m2 = new Monom("-x^2");
        Polynom p2Am2 = new Polynom("-9");
        p2.add(m2);
        assertEquals(p2Am2,p2);


    }

    @Test
    void substract() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Polynom p1 = new Polynom("-2x^6-5");
        Polynom p0Sp1 = new Polynom("5x^6+4x+10");
        p0.substract(p1);
        assertEquals(p0Sp1, p0);

        Polynom p2 = new Polynom("0");
        Polynom p3 = new Polynom("15x-3");
        Polynom p2Sp3 = new Polynom("-15x+3");
        p2.substract(p3);
        assertEquals(p2Sp3, p2);

        Polynom p4 = new Polynom("-9+x^2");
        Polynom p5 = new Polynom("-x^2+4.5x^3");
        Polynom p4Sp5 = new Polynom("2x^2-4.5x^3-9");
        p4.substract(p5);
        assertEquals(p4Sp5, p4);

        p4Sp5.substract(p4Sp5);
        assertEquals(new Polynom("0"),p4Sp5);


    }

    @Test
    void multiply_polynom() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Polynom p1 = new Polynom("1");
        Polynom p0Mp1 = new Polynom("3x^6+5+4x");
        p0.multiply(p1);
        assertEquals(p0Mp1,p0);

        Polynom p2 = new Polynom("0");
        Polynom p3 = new Polynom("15x-3");
        Polynom p2Mp3 = new Polynom("0");
        p2.multiply(p3);
        assertEquals(p2Mp3, p2);

        Polynom p4 = new Polynom("-9+x^2");
        Polynom p5 = new Polynom("5x+2");
        Polynom p4Mp5 = new Polynom("-45x-18+5x^3+2x^2");
        p4.multiply(p5);
        assertEquals(p4Mp5, p4);

        Polynom p6 = new Polynom("2x");
        for (int i = 0 ; i<3;i++){
            p6.multiply(p6);
        }
        assertEquals(p6, new Polynom("256x^8"));


    }

    @Test
    void equalsPolynom() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Polynom p1 = new Polynom("2x+2x+5-x+3x^6+x");
        boolean b = p0.equalsPolynom(p1);
        assertTrue(b);
        //true

        Polynom p2 = new Polynom("5x+6+10x-9");
        Polynom p3 = new Polynom("15x-3+0");
        b = p2.equalsPolynom(p3);
        assertTrue(b);
        //true

        Polynom p4 = new Polynom("-9+x^2");
        Polynom p5 = new Polynom("-9+x^2+x^2");
        b = p4.equalsPolynom(p5);
        assertFalse(b);
        //false

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
        double results = p.area(0,3,0.000001);
        double check = Math.abs(expected-results);
        boolean b = (check>=0) && (check <= 0.00001);
        assertTrue(b);

        p = new Polynom("x^2-3x+4x^3");
        expected = 5.0/6.0;
        results =p.area(-1,0.5,0.000001);
        check = Math.abs(expected-results);
        b = (check>=0) && (check <= 0.00001);

        assertTrue(b);
    }

    @Test
    void root() {
        Polynom p = new Polynom ("x^2-6+5x");
        double expected = 1;
        double result = p.root(0, 2.7, 0.0001);
        double check = Math.abs(expected-result);
        boolean b = (check>=0) && (check <= 0.0001);

        assertTrue(b);
    }

    @Test
    void derivative() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Polynom p1 = new Polynom("18x^5+4");
        Polynom_able p=p0.derivative();
        assertEquals(p1, p);

        Polynom p2 = new Polynom("5x+6+10x-9");
        Polynom p3 = new Polynom("15");
        p=p2.derivative();
        assertEquals(p3, p);

        Polynom p4 = new Polynom("-9+5.5x^3");
        Polynom p5 = new Polynom("16.5x^2");
        p=p4.derivative();
        assertEquals(p5, p);
    }

    @Test
    void multiply_monom() {
        Polynom p0 = new Polynom("3x^6+5+4x");
        Monom m0 = new Monom("x");
        Polynom p0Mm0 = new Polynom("3x^7+5x+4x^2");
        p0.multiply(m0);
        assertEquals(p0Mm0, p0);

        Polynom p1 = new Polynom("0");
        Monom m1 = new Monom("-7x^8");
        Polynom p1Mm1 = new Polynom("0");
        p1.multiply(m1);
        assertEquals(p1Mm1, p1);

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

        assertTrue(p.equals(m));

        p = new Polynom("3x+5-5");
        m = new Monom("3x");
        assertTrue(p.equals(m));


        p = new Polynom("3x^5+2-1");
        m = new Monom("3x^5");
        assertFalse(p.equals(m));


        //CF equals to Polynom

       // ComplexFunction cf = new ComplexFunction("");
    }

    @Test
    void copy() {
        Polynom p =new Polynom("3x+1");
        function f = p.copy();
        assertEquals(p,f);
        p.multiply(new Polynom("0"));
        assertNotEquals(p,f);
    }

    @Test
    void copyP() {
        Polynom p =new Polynom("3x+1");
        Polynom pcopy= p.copyP();
        assertEquals(p,pcopy);
        p.multiply(new Polynom("0"));
        assertNotEquals(p,pcopy);
    }

}