package Ex1;

import com.sun.source.tree.IfTree;

public class BinaryTreeFunction {
    public class Node {

        private Node _left,_right;
        private   boolean _isOperation;
        private Operation _operation;
        private function _value = null;
        private double results;

        private Node(Operation p){
            _isOperation = true;
            _operation = p;
        }
        private Node(function value) {
            _isOperation = false;
            _value = value.copy();
        }
        private Node(Node other){
            this._isOperation = other._isOperation;
            this._operation = other._operation;
            this._value = other._value.copy();
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

        public function getFunction() {
            return _value;
        }

        private void setResults(double results){
            this.results = results;
        }

        public Operation get_operation() {
            return _operation;
        }
    }
    private Node _root;
    public BinaryTreeFunction(){
    }

    public BinaryTreeFunction(Operation p){
        _root = new Node(p);
    }

    public BinaryTreeFunction(function p){
        _root = new Node(p);
    }
    public BinaryTreeFunction(Node p){
        _root = p;
    }

    public Node add(Operation p,boolean left,Node current){
        Node temp = new Node(p);

        if (left){
            current._left = temp;
        }
        else {
            current._right = temp;
        }
        return temp;
    }

    public Node add(function p,boolean left){
        Node temp = new Node(p);

        if (left){
            _root._left = temp;
        }
        else {
            _root._right = temp;
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
        recursiaCopy(temp._root,_root);
        return temp;
    }
    private void recursiaCopy(Node current,Node imgCurrent){
        if (imgCurrent == null) return;
        current = new Node(imgCurrent);
        recursiaCopy(current._left,imgCurrent._left);
        recursiaCopy(current._right,imgCurrent._right);
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

    private BinaryTreeFunction crateFunctionFromString(String s) {
        BinaryTreeFunction temp = new BinaryTreeFunction();
        temp.createBtree(s);
        return null;
    }
    private void createBtree(String s){
        int i = s.indexOf('(');
        Operation p = findOpFromString(s.substring(0,i));
        this._root = new Node(p);

    }
    private void recursBild(String s,Node current){
        if(s.charAt(0)=='(') {
            int index = findIndexMainComma(s);
            String left = s.substring(1,index);
            boolean isOp = ifIsOp(left);
            if (isOp){
                int openIndex = left.charAt('(');
                current._left = new Node(findOpFromString(left.substring(1,openIndex)));
                recursBild(s.substring(openIndex,index),current._left);
            }
            else { // left is function
                current._left = new Node(new Polynom(left));
            }
        }
        else //im after ','
        {
            int index = s.indexOf(')');
            String left = s.substring(1,index);
            boolean isOp = ifIsOp(left);
            if (isOp){
                int openIndex = left.charAt('(');
                current._right = new Node(findOpFromString(left.substring(1,openIndex)));
                recursBild(s.substring(openIndex,index),current._right);
            }
            else { // left is function
                current._right = new Node(new Polynom(left));
            }
        }
    }
}
