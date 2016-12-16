/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontrees;

import java.util.Set;

/**
 * Class with a static method to construct a single decision tree
 * @author Tanmaya
 */
public class ID3 {
    
    /**
     * Constructs the decision tree based on the data D
     * @param D The training data
     * @return Root node of the constructed tree.
     */
    public static Node ID3(Data D)
    {
        
        if(D.all_pos())
        {
            return new NodeL(D, 1);
        }
        else if(D.all_neg())
        {
            return new NodeL(D, -1);
        }
        else if(D.set.isEmpty())
        {
            return new NodeL(D, D.common());
        }
        else
        {
            Attribute Z=D.getBest();
            if (Z==null)
            {
                return new NodeL(D,D.common());
            }
            NodeI n =new NodeI(D,Z);
            
            Data[] z=D.split(n.attribute);
            for (int i=0;i<z.length;i++)
            {
                if(z[i].list.isEmpty())
                {
                    n.children[i]=new NodeL(z[i], D.common());
                }
                else
                {
                    n.children[i]=ID3(z[i]);
                }
            }
            return n;
        }
    }
    /**
     * Gives the output of the decision tree given a data
     * @param tree Root of the tree to be run
     * @param data The integer array representing the data.
     * @return 1 for true, -1 for false.
     */
    public static int run(Node tree, int[] data)
    {
        Node n=tree;
        while(!(n instanceof NodeL))
        {
            
            NodeI N=(NodeI)n;
            if(N.attribute instanceof AttributeD)
            {
                AttributeD D=(AttributeD)N.attribute;
                n=N.children[data[D.id]];
            }
            else
            {
                assert N.attribute  instanceof AttributeC;
                AttributeC C=(AttributeC)N.attribute;
                double split=N.data.split[C.id];
                if(data[C.id]<=split)
                {
                    n=N.children[0];
                }
                else
                {
                    n=N.children[1];
                }
            }
        }
        NodeL Z=(NodeL)n;
        return Z.val;
    }
}
