package Ex1;


import java.util.Stack;

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
            if (other._right!=null) {
                this._right = new Node(other._right);
            }
            if (other._left!=null) {
                this._left = new Node(other._left);
            }
        }
        private double getResults(double x){
            if (_operation == Operation.None) return _value.f(x);
            if (_operation == Operation.Comp){
                return _left.getResults(_right.getResults(x));
            }
            if (_operation == Operation.Plus) return _left.getResults(x) + _right.getResults(x);
            if (_operation == Operation.Max) return Math.max( _left.getResults(x) ,_right.getResults(x));
            if (_operation == Operation.Min) return Math.min( _left.getResults(x) ,_right.getResults(x));
            if (_operation == Operation.Divid){
                if (_right.getResults(x)==0){
                    Functions_GUI.divByZero = true;
                    return 0;
                }
                return  _left.getResults(x) /_right.getResults(x);
            }
            if (_operation == Operation.Times)  return  _left.getResults(x) *_right.getResults(x);
            throw new RuntimeException("ERR : no right opertion");




        }

        public void set_operation(Operation _operation) {
            if (_operation!=Operation.None)
                _isOperation = true;
            this._operation = _operation;
        }

        public function getFunction() {
            return _value;
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

    private BinaryTreeFunction(Node p){
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

    public void add(function p,boolean left){
        Node temp = new Node();
        temp._value = p;

        if (left){
            _root._left = new Node(temp);
        }
        else {
            _root._right =new Node(temp);
        }
    }

    public void add(BinaryTreeFunction b,boolean left){

        if (left){
            _root._left = b._root;
        }
        else {
            _root._right = b._root;
        }
    }

    public void add(function value, boolean left, Node current){
        Node temp = new Node(value);

        if (left){
            current._left = temp;
        }
        else {
            current._right = temp;
        }
    }

    public function getLF() {
        if (_root._operation==Operation.None){
            return this._root._value;
        }

        BinaryTreeFunction binaryTreeFunction = new BinaryTreeFunction(_root._left);
       return new ComplexFunction( binaryTreeFunction);
    }
    public function getRF() {
        BinaryTreeFunction temp = new BinaryTreeFunction(_root._right);
        return new ComplexFunction(temp);
    }
    public Operation getOP() {
        return _root.get_operation();
    }

    public BinaryTreeFunction copy(){
        BinaryTreeFunction temp = new BinaryTreeFunction();
        if (_root != null){
            temp._root= recursCopy(_root);
        }
        return temp;
    }

    private Node recursCopy(Node node){
        Node nodeCopy = new Node();
        if (node != null){
            nodeCopy = new Node(node);
            nodeCopy._left = recursCopy(node._left);
            nodeCopy._right = recursCopy(node._right);
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
        Stack<Character> bracket = new Stack<Character>();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '(')
                bracket.push(s.charAt(i));
            if(s.charAt(i) == ',') {
                if(bracket.size() == 1)
                    return i;
            }
            if(s.charAt(i) == ')')
                bracket.pop();
        }
        throw new RuntimeException("String is illegal");
    }

    private boolean ifIsOp(String s){
        if (s.charAt(0)=='x'||s.charAt(0)=='X'){
            return false;
        }
        if((s.charAt(0)>'a'&&s.charAt(0)<'z')||(s.charAt(0)>'A'&&s.charAt(0)<'Z')){

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
        String s = printInOrder(_root);
        s = s.replaceAll("Divid","div");
        s = s.replaceAll("Plus","plus");
        s = s.replaceAll("Times","mul");
        s = s.replaceAll("Comp","comp");
        s = s.replaceAll("Max","max");
        s = s.replaceAll("Min","min");
        return s;
    }

    private String printInOrder(Node current){
        if (current == null) return "";
        if (current._isOperation){
            return current._operation+"("+printInOrder(current._left)+","+printInOrder(current._right)+")";
        }
        else return current._value+"";

    }


        public function get_function() {
        return _root.getFunction();
    }

    public double f(double x) {
        if (_root == null) throw new RuntimeException("NULL!!! at F(x)");
        return _root.getResults(x);
    }

}
