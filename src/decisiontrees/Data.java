package decisiontrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/**
 * Class that abstracts the dataset.
 * @author Tanmaya
 */

public class Data {
    
    List<int[]> list;
    Set<Attribute> set;
    double[] split;
    /**
     * Constructs the data object given a list of individual datum and set of attributes remaining.
     * @param l List of the data
     * @param set Set of the attributes remaining
     */
    Data(List<int[]> l, Set<Attribute> set)
    {
        
        list=l;
        this.set=set;
        if(!l.isEmpty())
        {
            split=new double[l.get(0).length - 1];
        }
        else
        {
            split=null;
        }
    }
    
    private class ContTarget{
        int cont;
        int target;
        ContTarget(int c, int t)
        {
            cont=c;
            target=t;
        }
    }
    /**
     * Gets the entropy with respect to the target attribute and the dataset.
     * @return The entropy.
     */
    double getEntropy()
    {
        int pos=0;
        int neg=0;
        if(list.size()==0)return 0.0;
        for(Iterator<int[]> i=list.iterator(); i.hasNext(); )
        {
            int[] z=i.next();
            if(z[z.length-1]==1)
            {
                pos++;
            }
            else
            {
                neg++;
            }
            
        }
        int s=pos+neg;
        assert s==list.size();
        if(pos==0||neg==0)return 0.0;
        else
        {
            return -((double)pos*Math.log(pos/(double)s)/(double)s + (double)neg*Math.log(neg/(double)s)/(double)s);
        }
        
    }
    /**
     * Returns an array of data split according to a given attribute
     * @param a The attribute
     * @return An array containing the split data.
     */
    Data[] split(Attribute a)
    {
        assert !(a instanceof AttributeT);
        if(a instanceof AttributeD)
        {
            AttributeD A=(AttributeD)a;
            
            Set<Attribute> s2=new HashSet(set);
            s2.remove((Attribute)A);
            int id=A.id;
            int n=A.n_vals;
            Data[] arr=new Data[n];
            Object array[]=new Object[n];
            for (int i=0; i<n; i++)
            {
                array[i]=(Object)(new ArrayList<int[]>());
            }
            for(Iterator<int[]> i=list.iterator(); i.hasNext();)
            {
                int[] z=i.next();
                ((ArrayList<int []>)array[z[id]]).add(z);
            }
            for(int i=0;i<n;i++)
            {
                arr[i]=new Data((ArrayList<int []>)array[i],s2);
            }
            return arr;
        }
        else
        {
            AttributeC A=(AttributeC)a;
            return splitCont(A);
        }
    }
    /**
     * Returns an array of data split according to a continuous attribute
     * @param A The continuous attribute
     * @return The split data
     */
    Data[] splitCont(AttributeC A)
    {
        
        double s=split[A.id];
        
        int id=A.id;
        Data[] arr=new Data[2];
        
        Set<Attribute> s2=new HashSet(set);
        s2.remove((Attribute)A);
        Object array[]=new Object[2];
        for(int i=0;i<2;i++)
        {
            array[i]=(Object)(new ArrayList<int[]>());
        }
        for(Iterator<int[]> i=list.iterator();i.hasNext();)
        {
            int[] z=i.next();
            if(z[id]<=s)
            {
                ((ArrayList<int []>)array[0]).add(z);
            }
            else
            {
                ((ArrayList<int []>)array[1]).add(z);
            }
        }
        
        for(int i=0;i<2;i++)
        {
            arr[i]=new Data((ArrayList<int []>)array[i],s2);
        }
        return arr;
    }
    /**
     * Calculates the split value for a particular continuous attribute
     * @param A The continuous attribute
     */
    void getSplit(AttributeC A)
    {
        ContTarget z[]=new ContTarget[list.size()];
        int co=0;
        for(Iterator<int[]> i=list.iterator();i.hasNext();)
        {
            int[] p=i.next();
            z[co++]=new ContTarget(p[A.id], p[p.length-1]);
        }
        Arrays.sort(z, new Comparator<ContTarget>() {public int compare(ContTarget o1, ContTarget o2) {
        return Integer.compare(o1.cont, o2.cont);}});
        int prev1=z[0].cont;
        int prev2=z[0].target;
        double maxsplit=0.0;
        double minentropy=1.1;
        
        for(int i=1;i<z.length;i++)
        {
            int c=z[i].cont;
            int t=z[i].target;
            if(prev2==t){prev1=c; prev2=t;continue;}
            assert prev1>=c;
            split[A.id]=(double)(c+prev1)/2.0;
            Data[] arr=splitCont(A);
            double x=arr[0].list.size()*arr[0].getEntropy()/(double)list.size() + arr[1].list.size()*arr[1].getEntropy()/(double)list.size();
            if(x<minentropy){minentropy=x; maxsplit=split[A.id];}
            prev1=c;
            prev2=t;
        }
        assert maxsplit!=0.0;
        split[A.id]=maxsplit;
        
        
    }
    
    /**
     * Gets the attribute with the maximum information gain.
     * @return The attribute with the maximum information gain.
     */
    Attribute getBest()
    {
        Attribute best=null;
        double maxgain=0.0d;
        double ent=getEntropy();
        for(Iterator<Attribute> i=set.iterator();i.hasNext();)
        {
            Attribute x=i.next();
            double e=ent-attrEntropy(x);
            if(e>maxgain)
            {
                maxgain=e;
                best=x;
            }
        }
        if(maxgain==0.0d)return null;
        return best;
    }
    /**
     * Entropy of the data given the attribute value, i.e, H(d|x)
     * @param x The attribute
     * @return 
     */
    double attrEntropy(Attribute x)
    {
        int l=list.size();
        double e=0.0;
        Data[] z;
        if (x instanceof AttributeD)
        {
            z=split(x);
        }
        else
        {
            getSplit((AttributeC)x);
            z=splitCont((AttributeC)x);
        }
        for(int i=0;i<z.length;i++)
        {
            e=e+z[i].list.size()*z[i].getEntropy()/(double)l;
        }
        return e;
    }
    /**
     * Checks if all the values of the target attribute are positive
     * @return True if all values are positive. False otherwise.
     */
    boolean all_pos()
    {
        for(Iterator<int[]> i=list.iterator();i.hasNext();)
        {
            int[] z=i.next();
            if(z[z.length-1]!=1)return false;
        }
        return true;
    }
    /**
     * Checks if all the values of the target attribute are negative
     * @return True if all values are negative. False otherwise.
     */
    boolean all_neg()
    {
        for(Iterator<int[]> i=list.iterator();i.hasNext();)
        {
            int[] z=i.next();
            if(z[z.length-1]==1)return false;
        }
        return true;
    }
    /**
     * Returns the most common value of the target attribute
     * @return The most common value of the target attribute
     */
    int common()
    {
        int pos=0;
        int neg=0;
        for(Iterator<int[]> i=list.iterator();i.hasNext();)
        {
            int[] z=i.next();
            if(z[z.length-1]==1)pos++;
            else neg++;
        }
        if(pos>neg)return 1;
        else
            return -1;
    }
    
}
