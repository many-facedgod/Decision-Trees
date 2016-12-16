
package decisiontrees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
/**
 * Class having methods to generate a random forest
 * @author Tanmaya
 */
public class RandomForests {
    
    static Random r=new Random();  
    
    /**
     * Method to sample random data.
     * @param l The data list
     * @param k The number of samples required
     * @return The list of sampled data
     */
    public static List<int[]> getRandom(List<int[]> l, int k)
    {
        
        assert k<=l.size();
        List<int[]> list=new ArrayList<int[]>();
        for(int i=0;i<k;i++)
        {
            list.add(l.get(r.nextInt(l.size())));
        }
        return list;
        
    }
    /**
     * Generates a random forest given the Data D
     * @param num_data Number of data instances to be sampled for each tree.
     * @param num_attributes Number of attributes to be sampled at each node.
     * @param num_trees Number of trees in the forest
     * @param D The complete training data
     * @return Array of root nodes of the trees in the forest
     */
    public static Node[] RandomForest(int num_data, int num_attributes, int num_trees, Data D)
    {
        Node forest[]=new Node[num_trees];
        Set<Attribute> full=new HashSet<Attribute>(D.set);
        for(int i=0;i<num_trees;i++)
        {
            System.out.println("Creating tree "+i);
            
            List<int[]> list =getRandom(D.list, num_data);
            Data z=new Data(list, new HashSet<Attribute>(full));
            forest[i]=ID3(z, num_attributes, new HashSet<Attribute>(full));
        }
        return forest;
    }
    /**
     * Samples random attributes
     * @param s The set of attributes
     * @param num The number of attributes required
     * @return The set of the sampled attributes.
     */
    public static Set<Attribute> getRandomAtt(Set<Attribute> s, int num)
    {
        if(s.size()<=num)return new HashSet<Attribute>(s);
        List<Attribute> l=new ArrayList<Attribute>(s);
        List <Attribute> l2=new ArrayList<Attribute>();
        for(int i=0;i<num;i++)
        {
            l2.add(l.get(r.nextInt(l.size())));
        }
        return new HashSet(l2);
        
    }
    /**
     * A modified ID3 to accommodate variable number of attributes
     * @param D The data
     * @param num Number of attributes to be sampled per node
     * @param GSet The global set of all attributes.
     * @return The root node of the constructed tree
     */
    public static Node ID3(Data D, int num, Set<Attribute> GSet)
    {
        D.set=getRandomAtt(GSet, num);
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
            NodeI n =new NodeI(D, Z);            
            Data[] z=D.split(n.attribute);            
            Set<Attribute> s2=new HashSet<Attribute>(GSet);
            s2.remove(Z);
            for (int i=0;i<z.length;i++)
            {
                if(z[i].list.isEmpty())
                {
                    n.children[i]=new NodeL(z[i], D.common());
                }
                else
                {
                    n.children[i]=ID3(z[i], num, s2);
                }
            }
            return n;
        }
    }
    /**
     * Runs an instance of the Random forest
     * @param forest The array containing the roots of all the trees.
     * @param data The data instance to be run on the forest.
     * @return The predicted value
     */
    public static int run(Node[] forest, int[] data)
    {
        int pos=0;
        int neg=0;
        for(int j=0;j<forest.length;j++)
        {
            Node n=forest[j];
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
            int res=((NodeL)n).val;
            if(res==1)pos++;
            else neg++;
        }
        if(pos>neg)return 1;
        else return -1;
    }
}
