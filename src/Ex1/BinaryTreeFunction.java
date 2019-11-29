package Ex1;

public class BinaryTreeFunction {
    private class Node {

        private Node _left,_right;
        private   boolean _isOperation;
        private Operation _operation;
        private Polynom _value;
        private double results;

        private Node(Operation p){
            _isOperation = true;
            _operation = p;
        }
        private Node(Polynom value) {
            _isOperation = false;
            _value = value;
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
        private void setResults(double results){
            this.results = results;
        }
    }
    private Node _root;

    public BinaryTreeFunction(Operation p){
        _root = new Node(p);
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

    public Node add(Polynom value, boolean left, Node current){
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
                break;
            case Error:
                throw new RuntimeException("ERR : no right opertion");
                default:
        }
        throw new RuntimeException("ERR : no right opertion");
    }

}
