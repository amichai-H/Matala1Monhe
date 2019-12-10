
import Ex1.ComplexFunction;
import Ex1.Polynom;
import Ex1.function;


public class Main {
    public static void main(String[] args){
        ComplexFunction complexFunction = new ComplexFunction("Plus",new Polynom("3x^2"),new Polynom("2x^4"));
        ComplexFunction complexFunction2 = new ComplexFunction(complexFunction);
        System.out.println(complexFunction2);
        complexFunction.max(new Polynom("x"));
        System.out.println(complexFunction2);
        System.out.println(complexFunction.copy());
        ComplexFunction complexFunction1 = (ComplexFunction) complexFunction.copy();
        System.out.println(new Polynom() instanceof function);
        ComplexFunction r = new ComplexFunction("mul(div(mul(8,8),4x^2),div(10,5))");
        ComplexFunction s = new ComplexFunction(r.copy());
        System.out.println(s);
    }
}
