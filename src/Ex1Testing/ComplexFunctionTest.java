import Ex1.ComplexFunction;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;
import org.junit.jupiter.api.Test;



import static org.junit.Assert.*;

class ComplexFunctionTest {

    @Test
    void plus() {
        ComplexFunction cf = new ComplexFunction("plus(x^5,2x^5)");
        cf.plus(new Polynom("4x"));
        ComplexFunction cf1=new ComplexFunction("4x+3x^5");
        assertEquals(cf1,cf);
    }

    @Test
    void mul() {
        Polynom p1 = new Polynom("3x^5");
        Polynom p2 = new Polynom("4x+9x+7x");
        ComplexFunction cf = new ComplexFunction(p1);
        cf.mul(p2);
        p1.multiply(p2);
        assertEquals(true,p1.equals(cf));
    }

    @Test
    void div() {
        ComplexFunction cf = new ComplexFunction("plus(x^5,2x^5)");
        cf.div(new Polynom("3x^5"));
        ComplexFunction cfExpected  = new ComplexFunction("1");
        assertEquals(cfExpected,cf);
    }

    @Test
    void max() {
        ComplexFunction cf = new ComplexFunction("-1");
        cf.max(new ComplexFunction("mul(x,x)"));
        ComplexFunction cfExpected  = new ComplexFunction("x^2");
        assertTrue(cf.equals(cfExpected));
    }

    @Test
    void min() {
        ComplexFunction cf = new ComplexFunction(new Polynom("-1"));
        cf.min(new ComplexFunction("mul(x,x)"));
        ComplexFunction cfExpected  = new ComplexFunction("-1");
        assertTrue(cf.equals(cfExpected));

    }

    @Test
    void comp() {
        ComplexFunction cf = new ComplexFunction("plus(x,x)");
        cf.comp(new ComplexFunction("min(-1,2)"));
        ComplexFunction cfExpected  = new ComplexFunction("-2");
        assertTrue(cf.equals(cfExpected));
    }

    @Test
    void left() {
        ComplexFunction cf = new ComplexFunction("plus(x,x)");
        cf.comp(new Polynom("4x+9x+7x"));
        ComplexFunction cfExpected  = new ComplexFunction("2x");
        assertTrue(cf.left().equals(cfExpected));
    }

    @Test
    void right() {
        ComplexFunction cf = new ComplexFunction("plus(x,x)");
        cf.comp(new ComplexFunction("mul(x,x)"));
        ComplexFunction cfExpected  = new ComplexFunction("x^2");
        assertTrue(cf.right().equals(cfExpected));
    }

    @Test
    void getOp() {
        ComplexFunction cf = new ComplexFunction("plus(x,x)");
        assertEquals(cf.getOp(), Operation.Plus);

    }

    @Test
    void f() {
        ComplexFunction cf = new ComplexFunction("comp(x,-1)");
        assertEquals(cf.f(3)==-1,true);

    }

    @Test
    void copy() {
        ComplexFunction cf = new ComplexFunction("plus(x,x)");
        ComplexFunction cfExpected = new ComplexFunction("plus(x,x)");
        ComplexFunction cfCopy = (ComplexFunction)cf.copy();

        cf.comp(new ComplexFunction("comp(x,2x)"));
        assertEquals(cfCopy,cfExpected);

    }

    @Test
    void initFromString() {

        ComplexFunction complexFunction = new ComplexFunction("Plus",new Polynom("3x^2"),new Polynom("2x^4"));
        function complexFunction1 = complexFunction.initFromString("plus(3.0x^5,div(3.0x,2.0x^2))");
        assertEquals(complexFunction1.toString(),"plus(3.0x^5,div(3.0x,2.0x^2))");
    }

    @Test
    public void toString1() {
        ComplexFunction complexFunction = new ComplexFunction("Plus",new Polynom("3x^2"),new Polynom("2x^4"));
        function complexFunction1 = complexFunction.initFromString("plus(3.0x^5,div(3.0x,2.0x^2))");
        assertEquals(complexFunction1.toString(),"plus(3.0x^5,div(3.0x,2.0x^2))");

    }

    /*public static void main(String[] args) {
        ComplexFunction CP = new ComplexFunction("(3x^2+5x)*(2x)");
        System.out.println(CP.toString());
    }*/
}