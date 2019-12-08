package Ex1;


public class ComplexFunction implements complex_function {
    private BinaryTreeFunction _bTF = new BinaryTreeFunction();
    private   ComplexFunction(){

    }
    private ComplexFunction(BinaryTreeFunction b){
        _bTF = b;
    }
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
    public ComplexFunction(String operation, function p1, function p2) {

        _bTF = new BinaryTreeFunction(findOpFromString(operation));
        _bTF.add(p1,true);
        _bTF.add(p2,false);
    }

    public ComplexFunction(String operation, function p, ComplexFunction cf) {
        _bTF = new BinaryTreeFunction(findOpFromString(operation));
        _bTF.add(p,true);
        _bTF.add(cf._bTF,false);

    }
    public ComplexFunction(String operation, ComplexFunction cf,function p) {
        _bTF = new BinaryTreeFunction(findOpFromString(operation));
        _bTF.add(p,false);
        _bTF.add(cf._bTF,true);
    }

    private void operationAdd(Operation operation, function f1){
        BinaryTreeFunction temp = new BinaryTreeFunction(operation);
        temp.add(f1, false);
        temp.add(this._bTF, true);
        _bTF = temp;
    }

    @Override
    public void plus(function f1) {
       operationAdd(Operation.Plus,f1);
    }

    @Override
    public void mul(function f1) {
        operationAdd(Operation.Times,f1);
    }

    @Override
    public void div(function f1) {
        operationAdd(Operation.Divid,f1);
    }

    @Override
    public void max(function f1) {
        operationAdd(Operation.Max,f1);
    }

    @Override
    public void min(function f1) {
        operationAdd(Operation.Min,f1);
    }

    @Override
    public void comp(function f1) {
        operationAdd(Operation.Comp,f1);
    }

    @Override
    public function left() {
        ComplexFunction temp = new ComplexFunction();
        temp._bTF = _bTF.getLF();
        if (temp._bTF.getOP()==Operation.None){
            return _bTF.get_function();
        }
        return temp;
    }

    @Override
    public function right() {
        ComplexFunction temp = new ComplexFunction();
        temp._bTF = _bTF.getRF();
        return temp;
    }

    @Override
    public Operation getOp() {
        return _bTF.getOP();
    }

    @Override
    public double f(double x) {
        return _bTF.calculate(x);
    }

    public function copy() {
        return new ComplexFunction(this._bTF.copy());
    }

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
    public boolean equals(Object obj) {
        if(obj instanceof function) {
            double x = -2.5;
            while(x <= 2.5) {
                if(Math.abs(this.f(x)-((function) obj).f(x))<=0.00001) return false;
                x += 0.1;
            }
            for (int i = 0;i<32;i++) {
                double x1 = (Math.random() * 1000000);
                if (Math.abs(this.f(x1)-((function) obj).f(x1))<0.00001||Math.abs(this.f(x1*-1)-((function) obj).f(x1*-1))<0.000001);
                    return false;
            }
            return true;
        }
        return false;
    }
}
