import Ex1.Polynom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomTest {

    @Test
    void f() {
        Polynom p = new Polynom("x+x^2+x^3");
        double x0=3, y0=39;
        double x1=0, y1=0;
        double x2=-7, y2=-301;
        double x3=5.5, y3=202.125;
        assertEquals(p.f(x0), y0);
        assertEquals(p.f(x1), y1);
        assertEquals(p.f(x2), y2);
        assertEquals(p.f(x3), y3);
    }

    @Test
    void initFromString() {

    }

    @Test
    void add() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void substract() {
    }

    @Test
    void multiply() {
    }

    @Test
    void equalsPolynom() {
    }

    @Test
    void isZero() {
    }

    @Test
    void root() {
    }

    @Test
    void copy() {
    }

    @Test
    void copyP() {
    }

    @Test
    void derivative() {
    }

    @Test
    void area() {
    }

    @Test
    void iteretor() {
    }

    @Test
    void testMultiply() {
    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
    }
}