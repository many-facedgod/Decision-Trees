package decisiontrees;
/**
 * The super class of all attributes
 * @author Tanmaya
 */
public class Attribute {
    String name;
    int id;
    /**
     * Constructs an attribute with name n and ID i.
     * @param n Name of the attribute
     * @param i ID of the attribute
     */
    Attribute(String n, int i)
    {
        name=n;
        id=i;
    }
    
}
/**
 * The class abstracting a discrete valued attribute
 * @author Tanmaya
 */
class AttributeD extends Attribute
{
    int n_vals;
    String values[];
    /**
     * Constructs an Attribute object for a discrete valued attribute
     * @param n Name of the attribute
     * @param nv Number of different values it can take
     * @param v The array of names of the different values
     * @param i The ID of the attribute
     */
    AttributeD(String n, int nv, String[] v, int i)
    {
        super(n, i);
        n_vals=nv;
        values=v;
    }
}
/**
 * Class abstracting a continuous valued attribute
 * @author Tanmaya
 */
class AttributeC extends Attribute
{
    /**
     * Constructs an Attribute object for a continuous valued attribute 
     * @param n Name of the attribute
     * @param i The ID of the attribute
     */
    AttributeC(String n,  int i)
    {
        super(n, i);
            
    }
}
/**
 * Class abstracting the target attribute
 * @author Tanmaya
 */
class AttributeT extends Attribute
{
    String values[];
    /**
     * Constructs an Attribute object for the target attribute (Boolean)
     * @param n Name of the attribute
     * @param v The array of names of the two values of the attribute
     * @param i The ID of the attribute (assumed to be the greatest).
     */
    AttributeT(String n, String[] v, int i)
    {
        super(n, i);
        assert v.length==2;
        values=v;
    }
}