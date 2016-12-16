
package decisiontrees;
/**
 * Class representing a node of the decision tree.
 * @author Tanmaya
 */
public class Node
{
    Data data;
    /**
     * Constructs a node given the data d.
     * @param d The data for that node.
     */
    Node(Data d)
    {
        data=d;
    }
}

/**
 * Class representing the internal node of the tree.
 * @author Tanmaya
 */
class NodeI extends Node
{
    Attribute attribute;
    Node children[];
    /**
     * Constructs an internal for the tree.
     * @param d The data.
     * @param A The attribute to be considered.
     */
    NodeI(Data d, Attribute A)
    {
        super(d);
        attribute=A;
        if(A instanceof AttributeC)
            children=new Node[2];
        else
        {
            children=new Node[((AttributeD)A).n_vals];
        }
    }
}
/**
 * Class representing a leaf node of the tree.
 * @author Tanmaya
 */
class NodeL extends Node
{
    int val;
    /**
     * Constructs a leaf node for the tree.
     * @param d The data remaining.
     * @param v The Predicted value.
     */
    NodeL(Data d, int v)
    {
        super(d);
        assert val==1||val==-1;
        val=v;
    }
}