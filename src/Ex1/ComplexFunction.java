package Ex1;


public class ComplexFunction implements complex_function {
    private BinaryTreeFunction _bTF;
    public ComplexFunction(Polynom p) {
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
        return _bTF.getLF();
    }

    @Override
    public function right() {
        return _bTF.getRF();
    }

    @Override
    public Operation getOp() {
        return _bTF.getOP();
    }

    @Override
    public double f(double x) {
        return _bTF.caculate(x);
    }

    public function copy() {
        return null;
    }

    public function initFromString(String s1) {
        return null;
    }

    private Operation findOpFromString(String s){
        switch (s){
            case "Plus": return Operation.Plus;
            case "Times": return Operation.Times;
            case "Divid": return Operation.Divid;
            case "Max": return Operation.Max;
            case "Min": return Operation.Min;
            case "Comp": return Operation.Comp;
            case "None": return Operation.None;
            case "Error": return Operation.Error;
            default:
                throw new RuntimeException("ERR: no action was found");
        }
    }
}
