package Ex1;


public class ComplexFunction implements complex_function {
    private BinaryTreeFunction _bTF = new BinaryTreeFunction();
    private   ComplexFunction(){

    }
    public ComplexFunction(BinaryTreeFunction b){
        _bTF = b;
    }

    /**
     * ComplexFunction(String), you can add String in syntax like that:
     * Operation(function,function)
     * Operation's options: plus, mul, div, max, min, comp
     * function's options: Complex Function, Polynom , Monom
     * @param s example "mul(x,x)"
     */
    public ComplexFunction(String  s){
        try {
            _bTF = ((ComplexFunction) initFromString(s))._bTF;
        }catch (Exception e){
            _bTF = new BinaryTreeFunction(initFromString(s));
        }
    }
    public ComplexFunction(function p){
        _bTF = new BinaryTreeFunction(p);
    }

    /**
     * ComplexFunction(String operation, function, function),
     * initilize a ComplexFunction with two functions and operation.
     * it's look like that: operation(function,function)
     * @param operation options: plus, mul, div, max, min, comp
     * @param p1 is a Complex Function, Polynom , Monom
     * @param p2 is a Complex Function, Polynom , Monom
     */
    public ComplexFunction(String operation, function p1, function p2) {
        operation = fixText(operation);
        _bTF = new BinaryTreeFunction(findOpFromString(operation));
        _bTF.add(p1,true);
        _bTF.add(p2,false);
    }
    public ComplexFunction(String operation, function p, ComplexFunction cf) {
        operation = fixText(operation);
        _bTF = new BinaryTreeFunction(findOpFromString(operation));
        _bTF.add(p,true);
        _bTF.add(cf._bTF,false);

    }
    public ComplexFunction(String operation, ComplexFunction cf,function p) {
        operation = fixText(operation);
        _bTF = new BinaryTreeFunction(findOpFromString(operation));
        _bTF.add(p,false);
        _bTF.add(cf._bTF,true);
    }
    public ComplexFunction(Operation operation, function p1, function p2) {

        _bTF = new BinaryTreeFunction(findOpFromString(operation.toString()));
        _bTF.add(p1,true);
        _bTF.add(p2,false);
    }
    public ComplexFunction(Operation operation, function p, ComplexFunction cf) {
        _bTF = new BinaryTreeFunction(findOpFromString(operation.toString()));
        _bTF.add(p,true);
        _bTF.add(cf._bTF,false);

    }
    public ComplexFunction(Operation operation, ComplexFunction cf,function p) {
        _bTF = new BinaryTreeFunction(findOpFromString(operation.toString()));
        _bTF.add(p,false);
        _bTF.add(cf._bTF,true);
    }

    /**
     * plus adds the function to this ComplexFunction (this ComplexFunction + function).
     * @param f1 the complex_function which will be added to this complex_function.
     */
    @Override
    public void plus(function f1) {
        _bTF = ((ComplexFunction) initFromString(fixText("plus("+this.toString()+","+f1+")")))._bTF;
    }

    /**
     *
     * @param f1 the complex_function which will be multiply be this complex_function.
     */
    @Override
    public void mul(function f1) {
        _bTF = ((ComplexFunction) initFromString(fixText("mul("+this.toString()+","+f1+")")))._bTF;
    }

    /**
     *
     * @param f1 the complex_function which will be divid this complex_function.
     */
    @Override
    public void div(function f1) {
        _bTF = ((ComplexFunction) initFromString(fixText("div("+this.toString()+","+f1+")")))._bTF;
    }

    /**
     *
     * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
     */
    @Override
    public void max(function f1) {
        _bTF = ((ComplexFunction) initFromString(fixText("max("+this.toString()+","+f1+")")))._bTF;
    }

    /**
     *
     * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
     */
    @Override
    public void min(function f1) {
        _bTF = ((ComplexFunction) initFromString(fixText("min("+this.toString()+","+f1+")")))._bTF;
    }

    /**
     * This method wrap the f1 complex_function with this function: this.f(f1(x))
     * @param f1 function
     */
    @Override
    public void comp(function f1) {
        _bTF = ((ComplexFunction) initFromString(fixText("comp("+this.toString()+","+f1+")")))._bTF;
    }

    /**
     *
     * @return the function in left
     */
    @Override
    public function left() {
        if (_bTF.getOP()==Operation.None){
            return _bTF.get_function();
        }
        return _bTF.getLF();
    }

    /**
     *
     * @return the function on right or null;
     */
    @Override
    public function right() {
        return _bTF.getRF();
    }

    /**
     *
     * @return Op in tha Complex Function
     */
    @Override
    public Operation getOp() {
        return _bTF.getOP();
    }

    /**
     *  Calculate the value obtained for setting a given x in the Complex function
     * @param x
     * @return
     */
    @Override
    public double f(double x) {
        return _bTF.f(x);
    }

    /**
     *
     * @return copy of this complex function
     */
    public function copy() {
        return new ComplexFunction(this._bTF.copy());
    }

    /**
     * ComplexFunction(String), you can add String in syntax like that:
     * Operation(function,function)
     * Operation's options: plus, mul, div, max, min, comp
     * function's options: Complex Function, Polynom , Monom
     * @param s1
     * @return function
     */
    public function initFromString(String s1) {
        boolean tryPoly;
        Polynom p = new Polynom();
        try {
            p = new Polynom(s1);
            tryPoly = true;
        }catch (Exception e){
            tryPoly = false;
        }
        if (tryPoly){
            return p;
        }

       if (!stringCheck(s1)) throw new RuntimeException("Err not a String");
       s1 = s1.replaceAll(" ","");
       s1=fixText(s1);

     return new ComplexFunction(_bTF.createFunctionFromString(s1));
    }

    private Operation findOpFromString(String s){
        s=fixText(s);
        switch (s){
            case "Plus": return Operation.Plus;
            case "Mul": return Operation.Times;
            case "Div": return Operation.Divid;
            case "Max": return Operation.Max;
            case "Min": return Operation.Min;
            case "Comp": return Operation.Comp;
            case "None": return Operation.None;
            case "Error": return Operation.Error;
            default:
                throw new RuntimeException("ERR: no action was found");
        }
    }
    private boolean stringCheck(String s){
        if (s == null){
            return false;
        }
        boolean ok = true,commaOk = true;
        boolean opener;
        boolean comma;
        boolean bracket;
        int notNegative = 0,numOfOpener = 0,numOfBracket = 0, numOfComma = 0;
        for (int i = 0; i<s.length()&&ok;i++){
            opener=s.charAt(i)=='(';
            comma=s.charAt(i)==',';
            bracket=s.charAt(i)==')';

            if (opener) numOfOpener++;
            if (comma) numOfComma++;
            if (bracket) numOfBracket++;

            if (comma){
                commaOk = numOfOpener>=numOfComma&&numOfBracket<=numOfComma;
            }

            notNegative = numOfOpener - numOfBracket;

            ok = (notNegative>=0)&&commaOk;

        }
        return ok;

    }

    /**
     *
     * @return the function in the right syntax
     */
    @Override
    public String toString() {
        return _bTF.toString();
    }

    private String fixText(String s){
        s = s.replaceAll("div","Div");
        s = s.replaceAll("divid","Div");
        s = s.replaceAll("Divid","Div");
        s = s.replaceAll("plus","Plus");
        s = s.replaceAll("mul","Mul");
        s = s.replaceAll("Times","Mul");
        s = s.replaceAll("times","Mul");
        s = s.replaceAll("comp","Comp");
        s = s.replaceAll("max","Max");
        s = s.replaceAll("none","None");
        s = s.replaceAll("min","Min");
        return s;
    }

    /**
     * psudo equals
     * this method return if the functions equlas
     * more of the problem in this method at https://github.com/amichai-H/Matala1Monhe/issues/1
     * @param obj
     * @return true if this function = function(obj) and false if !=
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof function) {
            double x = -2.5;
            while(x <= 2.5) {

                if(!(Math.abs(this.f(x)-((function) obj).f(x))<=0.00001)){

                    return false;
                }
                x += 0.1;
            }
            for (int i = 0;i<32;i++) {
                double x1 = round((Math.random() * 1000));
                double myFX = round(this.f(x1));
                double oFX =round(((function) obj).f(x1));

                double minusFX = round(this.f(x1*-1));
                double oMinusFX = round(((function) obj).f(x1*-1));

                double lessThanEpsilon1 = Math.abs(myFX-oFX);
                double lessThanEpsilon2 = Math.abs(minusFX-oMinusFX);

                if (lessThanEpsilon1>0.00001||lessThanEpsilon2>0.00001)
                    return false;
            }
            return true;
        }

        return false;
    }
    private static double round (double d) {
        d = (double) ((int) (d * 10000000)) / (10000000);
        return d;
    }
}
