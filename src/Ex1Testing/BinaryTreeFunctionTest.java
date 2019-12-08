import Ex1.BinaryTreeFunction;
import Ex1.Operation;
import Ex1.Polynom;
import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class BinaryTreeFunctionTest {
   // @BeforeEach
    public void builder(){

    }

    @Test
    public void add() {
        Polynom polynomTest1= new Polynom("3x^5+3x-8");
        Polynom polynomTest2 = new Polynom("5+x");
        BinaryTreeFunction binaryTreeFunction = new BinaryTreeFunction( polynomTest1);
        System.out.println(binaryTreeFunction);
        BinaryTreeFunction temp = new BinaryTreeFunction(Operation.Plus);
        temp.add(polynomTest2,true);
        temp.add(binaryTreeFunction,false);
        System.out.println(temp);
    }

    @Test
    public void add1() {
    }

    @Test
    public void add2() {
    }

    @Test
    public void add3() {
    }

    @Test
    public void caculate() {
    }

    @Test
    public void getLF() {
    }

    @Test
    public void getRF() {
    }

    @Test
    public void getOP() {
    }

    @Test
    public void copy() {
    }

    @Test
    public void createFunctionFromString() {
    }

    @Test
    public void toString1() {
    }
}