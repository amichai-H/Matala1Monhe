package Ex1;


public class BinaryTreeFunction {
    public class Node {

        private Node _left,_right;
        private   boolean _isOperation = false;
        private Operation _operation = Operation.None;
        private function _value = null;
        private double results;

        private Node(){

        }

        private Node(Operation p) {
            if (p == Operation.None) _isOperation = false;
            _operation = p;
        }
        private Node(function value) {
            _isOperation = false;
            _value = value.copy();
        }
        private Node(Node other){
            if(!(other == null)) {
                this._isOperation = other._isOperation;
                this._operation = other._operation;
                if (other._value != null)
                    this._value = other._value.copy();
            }
        }
        private boolean hasLeft(){
            return this._left!= null;
        }
        private boolean hasright(){
            return this._right!= null;
        }
        private boolean isLeaf(){
            return !this.hasright() && !this.hasLeft();
        }
        private double getResults(double x){
            if (this.isLeaf()) return _value.f(x);
            else return results;
        }

        public void set_operation(Operation _operation) {
            if (_operation!=Operation.None)
                _isOperation = true;
            this._operation = _operation;
        }

        public function getFunction() {
            return _value;
        }

        private void setResults(double results){
            this.results = results;
        }

        public Operation get_operation() {
            return _operation;
        }

        @Override
        public String toString() {
            if (_isOperation) return _operation+"";
            else return _value+"";
        }
    }
    private Node _root = new Node();
    public BinaryTreeFunction(){
    }

    public BinaryTreeFunction(Operation p){
        _root.set_operation(p);
        _root._left = new Node();
        _root._right = new Node();
    }

    public BinaryTreeFunction(function p){
        _root = new Node(p);
    }
    public BinaryTreeFunction(Node p){
        _root =new Node(p);
    }

    public Node add(Operation p,boolean left,Node current){
        Node temp = new Node();
        temp.set_operation(p);

        if (left){
            current._left = new Node(temp);
    }
        else {
            current._right = new Node(temp);
        }
        return temp;
    }

    public Node add(function p,boolean left){
        Node temp = new Node();
        temp._value = p;

        if (left){
            _root._left = new Node(temp);
        }
        else {
            _root._right =new Node(temp);
        }
        return temp;
    }

    public void add(BinaryTreeFunction b,boolean left){

        if (left){
            _root._left = b._root;
        }
        else {
            _root._right = b._root;
        }
    }

    public Node add(function value, boolean left, Node current){
        Node temp = new Node(value);

        if (left){
            current._left = temp;
        }
        else {
            current._right = temp;
        }
        return temp;
    }

    public double caculate(double x){
        if (_root == null) return 0;
        Node temp = recursiveCalculate(_root,x);
        return temp.getResults(x);
    }

    private Node recursiveCalculate(Node current, double x){
        if (current.isLeaf()) return current;
        if (current._isOperation){
            Node right = recursiveCalculate(current._right, x);
            Node left = recursiveCalculate(current._left, x);
            current.setResults(calculateNow(left,right,current._operation,x));
        }
        return current;
    }

    private double calculateNow(Node left, Node right, Operation p , double x){
        switch (p){
            case Comp:
                return left.getResults(right.getResults(x));
            case Max:
                return Math.max(left.getResults(x),right.getResults(x));
            case Min:
                return Math.min(left.getResults(x),right.getResults(x));
            case None:
                break;
            case Plus:
                return left.getResults(x) + right.getResults(x);
            case Divid:
                return left.getResults(x) / right.getResults(x);
            case Times:
                return left.getResults(x) * right.getResults(x);
            case Error:
                throw new RuntimeException("ERR : no right opertion");
                default:
        }
        throw new RuntimeException("ERR : no right opertion");
    }

    public BinaryTreeFunction getLF() {
        BinaryTreeFunction temp = new BinaryTreeFunction(_root._left);
        return temp;
    }
    public BinaryTreeFunction getRF() {
        BinaryTreeFunction temp = new BinaryTreeFunction(_root._right);
        return temp;
    }
    public Operation getOP() {
        return _root.get_operation();
    }

    public BinaryTreeFunction copy(){
        BinaryTreeFunction temp = new BinaryTreeFunction();
        if (_root == null) return temp;
        else {

            temp._root= recursiaCopy(temp._root,_root);
        }
        return temp;
    }
    private Node recursiaCopy(Node nodeCopy,Node node){
        if (node == null) return null;
        else {
            nodeCopy = new Node(_root);
            nodeCopy._left = recursiaCopy(nodeCopy._left, node._left);
            nodeCopy._right = recursiaCopy(nodeCopy._right, node._right);
        }
        return nodeCopy;



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

    public BinaryTreeFunction createFunctionFromString(String s) {
        BinaryTreeFunction temp = new BinaryTreeFunction();
        temp.createBtree(s);
        return temp;
    }
    private void createBtree(String s){
        _root = new Node(Operation.None);
        recursBild(s,_root);


    }
    private void recursBild(String s,Node current){
        if(ifIsOp(s)){
            current.set_operation( returnOp(s));
            int comma = findIndexMainComma(s);
            String left = returnLeftStringOp(s.substring(0,comma));
            String right = s.substring(comma+1);
            current._left=new Node(Operation.None);
            current._right = new Node(Operation.None);
            recursBild(left,current._left);
            recursBild(right,current._right);
        }
        else {
            current._value = new Polynom(clearFunction(s));

        }

    }
    private int findIndexMainComma(String s){
        int sumOfOpen = 0 ,sumOfComma = 0;
        boolean opener;
        boolean comma;
        boolean bracket;
        for (int i=0;i<s.length();i++){
            opener = s.charAt(i)=='(';
            comma = s.charAt(i)==',';
            bracket = s.charAt(i) == ')';

            if (opener) sumOfOpen++;
            if (bracket) sumOfOpen--;
            if (comma){
                sumOfComma++;
                if (sumOfComma==sumOfOpen) return i;
                break;
            }
        }
        throw new RuntimeException("ERR FIX ME!!!");
    }

    private boolean ifIsOp(String s){
        if(s.charAt(0)=='P'||s.charAt(0)=='D'||s.charAt(0)=='C'||s.charAt(0)=='M'||s.charAt(0)=='N'||s.charAt(0)=='E'){
            return true;
        }
        else {
            return false;
        }
    }

    private String returnLeftStringOp(String s){
        int index = s.indexOf('(');
        if (index==-1) throw new RuntimeException("Err : problem at insertOpReturnLeft ");
        return s.substring(index+1);
    }

    private Operation returnOp(String s){
        int index = s.indexOf('(');
        if (index==-1) throw new RuntimeException("Err : problem at Op");
        String temp = s.substring(0,index);
        return findOpFromString(temp);
    }

    private String clearFunction(String s){
        int index = s.indexOf(')');
        if (index!=-1){
            return s.substring(0,index);
        }
        else return s;
    }
    @Override
    public String toString() {
        return printInOrder(_root);
    }

    private String printInOrder(Node current){
        if (current == null) return "";
        if (current._isOperation){
            return current._operation+"("+printInOrder(current._left)+","+printInOrder(current._right)+")";
        }
        else return current._value+"";

    }
}
