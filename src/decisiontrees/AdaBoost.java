package decisiontrees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/**
 * Class implementing AdaBoost
 * @author Tanmaya
 */
public class AdaBoost {
    
    
    public static List<int[]> getRandom(double[] prob, List<int[]> l, int n)
    {
        assert l.size()==prob.length;
        double c_prob[]=new double[prob.length];
        c_prob[0]=prob[0];
        for(int i=1;i<prob.length;i++)c_prob[i]=prob[i]+c_prob[i-1];
        List<int[]> list=new ArrayList<int[]>();
        for(int i=0;i<n;i++)
        {
            double x=Math.random();
            for(int j=0;j<l.size();j++)
            {
                if(x<=c_prob[j])
                {
                    list.add(l.get(j));
                    break;
                }
            }
        }
        return list;
    }
    
    
    public static TreeWeight[] AdaBoost(int num_data, int num_trees, Data D)
    {
        double[] alphas=new double[num_trees];
        double[] weights=new double[D.list.size()];
        TreeWeight[] T=new TreeWeight[num_trees];
        for(int i=0;i<D.list.size();i++)
        {
            weights[i]=1.0/(double)D.list.size();
        }
        for(int i=0;i<num_trees;i++)
        {
            System.out.println(i);
            int[] mark=new int[D.list.size()];
            List<int[]> list=getRandom(weights, D.list, num_data);
            Data d=new Data(list, new HashSet<Attribute>(D.set));
            Node n=ID3.ID3(d);
            
            double err=0;
            
            for(int j=0;j<D.list.size();j++)
            {
                int[] z=D.list.get(j);
                int r=ID3.run(n, z);
                
                if(z[z.length-1]==r)continue;
                else
                {
                    mark[j]=1;
                    err=err+weights[j];
                }
            }
            
            alphas[i]=0.5*Math.log((1-err)/err);
            double sum_W=0.0;
            for(int j=0;j<weights.length;j++)
            {
                if(mark[j]==1)
                {
                    weights[j]=weights[j]*Math.exp(alphas[i]);
                }
                else
                {
                    weights[j]=weights[j]*Math.exp(-alphas[i]);
                }
                sum_W+=weights[j];
            }
            for(int j=0;j<weights.length;j++)
            {
                weights[j]=weights[j]/sum_W;
            }
            T[i]=new TreeWeight(n, alphas[i]);
        }
        return T;
    }
    
    public static int run(TreeWeight[] T, int[] data)
    {
        double sum=0.0;
        for(int i=0;i<T.length;i++)
        {
            int r=ID3.run(T[i].N, data);
            sum+=T[i].weight*r;
        }
        if(sum>0)return 1;
        else
            return -1;
    }
    
}
