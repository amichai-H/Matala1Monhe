import Ex1.ComplexFunction;
import Ex1.Polynom;
import Ex1.function;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexFunctionTest {

    @Test
    public void plus() {
        ComplexFunction cf = new ComplexFunction(new Polynom("3x^5"));
        cf.plus(new Polynom("4x+9x+7x"));
        System.out.println(cf);
    }

    @Test
    public void mul() {
        Polynom p1 = new Polynom("3x^5");
        Polynom p2 = new Polynom("4x+9x+7x");
        ComplexFunction cf = new ComplexFunction(p1);
        cf.mul(p2);
        p1.multiply(p2);
        System.out.println(cf);
        System.out.println(p1);
        assertEquals(true,p1.equals(cf));
    }

    @Test
    public void div() {
        ComplexFunction cf = new ComplexFunction(new Polynom("3x^5"));
        cf.div(new Polynom("4x+9x+7x"));
        System.out.println(cf);
    }

    @Test
    public void max() {
        ComplexFunction cf = new ComplexFunction(new Polynom("3x^5"));
        cf.max(new Polynom("4x+9x+7x"));
        System.out.println(cf);
    }

    @Test
    public void min() {
        ComplexFunction cf = new ComplexFunction(new Polynom("3x^5"));
        cf.min(new Polynom("4x+9x+7x"));
        System.out.println(cf);
    }

    @Test
    public void comp() {
        ComplexFunction cf = new ComplexFunction(new Polynom("3x^5"));
        cf.comp(new Polynom("4x+9x+7x"));
        System.out.println(cf);
    }

    @Test
    public void left() {
        ComplexFunction cf = new ComplexFunction(new Polynom("3x^5"));
        cf.comp(new Polynom("4x+9x+7x"));
        System.out.println(cf.left());
    }

    @Test
    public void right() {
        ComplexFunction cf = new ComplexFunction(new Polynom("3x^5"));
        cf.comp(new Polynom("4x+9x+7x"));
        System.out.println(cf.right());
    }

    @Test
    public void getOp() {
        ComplexFunction cf = new ComplexFunction(new Polynom("3x^5"));
        cf.comp(new Polynom("4x+9x+7x"));
        System.out.println(cf.getOp());
    }

    @Test
    public void f() {
        ComplexFunction cf = new ComplexFunction(new Polynom("3x^5"));
        cf.comp(new Polynom("4x+9x+7x"));
        System.out.println(cf.f(5.8));

    }

    @Test
    public void copy() {
        System.out.println("********** copy **********");
        ComplexFunction cf = new ComplexFunction(new Polynom("3x^5"));
        ComplexFunction cfCopy = (ComplexFunction)cf.copy();
        cf.comp(new Polynom("4x+9x+7x"));
        System.out.println(cfCopy);

    }

    @Test
    public void initFromString() {
        System.out.println("********** initFromString **********");

        ComplexFunction complexFunction = new ComplexFunction("Plus",new Polynom("3x^2"),new Polynom("2x^4"));
        function complexFunction1 = complexFunction.initFromString("Plus(3.0x^5,Div(3.0x,2.0x^2))");
        System.out.println(complexFunction1);
        System.out.println(complexFunction1.f(3));
        assertEquals(complexFunction1.toString(),"Plus(3.0x^5,Divid(3.0x,2.0x^2))");
    }

    @Test
    public void toString1() {

    }

    /*public static void main(String[] args) {
        ComplexFunction CP = new ComplexFunction("(3x^2+5x)*(2x)");
        System.out.println(CP.toString());
    }*/
}