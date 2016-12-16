package decisiontrees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * Main class
 * @author Tanmaya
 */
public class DecisionTrees {

    /**
     * @param args the command line arguments 
     */
    public static void main(String[] args)throws IOException {
        
       
        DataRef D=new DataRef();
        Attribute A[]=new Attribute[15];
        for(int i=0;i<14;i++)
        {
            if(D.attrRef[i][0].equals("<="))
            {
                A[i]=(Attribute)(new AttributeC(D.majorRef[i],i));
            }
            else
            {
                A[i]=(Attribute)(new AttributeD(D.majorRef[i], D.attrRef[i].length, D.attrRef[i], i));
            }
            
        }
        A[14]=(Attribute)(new AttributeT(D.majorRef[14],D.attrRef[14] ,14));
        List<int[]> list=new ArrayList<int[]>();
        inputHandle("adult.txt", list);
        Set<Attribute> s=new HashSet();
        for(int i=0;i<14;i++)s.add(A[i]);
        Data d=new Data(list, s);
        Node[]  forest=RandomForests.RandomForest(200,4,1000, d);
        int correct=0;
        list=new ArrayList<int[]>();
        inputHandle("test.txt", list);
        for(int i=0;i<list.size();i++)
        {
            int z[]=list.get(i);
            int r=RandomForests.run(forest, z);
            if(r==z[14]){correct++;}
        }
        double acc=(double)correct/(double)list.size();
        System.out.println(acc);
        
        
        
    }
    
    
    public static void inputHandle(String filename,List<int[]> data)throws IOException 
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line=null;
        int flag = 1;
        
        while( (line=br.readLine()) != null) 
        {
            flag = 1;
            StringTokenizer st = new StringTokenizer(line,",");
            int age=0;
            String workClass=null;
            int fnlwgt=0;
            String education=null;
            int educationNum=0;
            String maritalStatus=null;
            String occupation=null;
            String relationship = null;
            String race=null;
            String sex=null;
            int capitalGain=0;
            int capitalLoss=0;
            int hoursPerWeek=0;
            String nativeCountry=null;
            String income = null;
            int result=-1;
            while(st.hasMoreTokens())
            {
                age = Integer.parseInt((st.nextToken()).trim());
                workClass = st.nextToken().trim();
                if(workClass.equals("?"))
                        flag = 0;
                fnlwgt = Integer.parseInt(st.nextToken().trim());
                education = st.nextToken().trim();
                educationNum = Integer.parseInt(st.nextToken().trim());
                maritalStatus = st.nextToken().trim();
                occupation = st.nextToken().trim();
                if(occupation.equals("?"))
                        flag = 0;
                relationship = st.nextToken().trim();
                race = st.nextToken().trim();
                sex = st.nextToken().trim();
                capitalGain = Integer.parseInt(st.nextToken().trim());
                capitalLoss = Integer.parseInt(st.nextToken().trim());
                hoursPerWeek = Integer.parseInt(st.nextToken().trim());
                nativeCountry = st.nextToken().trim();
                if(nativeCountry.equals("?"))
                        flag = 0;
                income = st.nextToken().trim();
                result = income.equals("<=50K")?1:-1;
            }
            if(flag==1)
            {
                int arr[]=new int[15];
                arr[0]=age;
                if(workClass.equals("Private"))
                        arr[1] = 0;
                else if(workClass.equals("Self-emp-not-inc"))
                        arr[1] = 1;
                else if(workClass.equals("Self-emp-inc"))
                        arr[1] = 2;
                else if(workClass.equals("Federal-gov"))
                        arr[1] = 3;
                else if(workClass.equals("Local-gov"))
                        arr[1] = 4;
                else if(workClass.equals("State-gov"))
                        arr[1] = 5;
                else if(workClass.equals("Without-pay"))
                        arr[1] = 6;
                else if(workClass.equals("Never-worked"))
                        arr[1] = 7;


                arr[2]=fnlwgt;


                if(education.equals("Bachelors"))
                        arr[3]=0;
                else if(education.equals("Some-college"))
                        arr[3]=1;
                else if(education.equals("11th"))
                        arr[3]=2;
                else if(education.equals("HS-grad"))
                        arr[3]=3;
                else if(education.equals("Prof-school"))
                        arr[3]=4;
                else if(education.equals("Assoc-acdm"))
                        arr[3]=5;
                else if(education.equals("Assoc-voc"))
                        arr[3]=6;
                else if(education.equals("9th"))
                        arr[3]=7;
                else if(education.equals("7th-8th"))
                        arr[3]=8;
                else if(education.equals("12th"))
                        arr[3]=9;
                else if(education.equals("Masters"))
                        arr[3]=10;
                else if(education.equals("1st-4th"))
                        arr[3]=11;
                else if(education.equals("10th"))
                        arr[3]=12;
                else if(education.equals("Doctorate"))
                        arr[3]=13;
                else if(education.equals("5th-6th"))
                        arr[3]=14;
                else if(education.equals("Preschool"))
                        arr[3]=15;


                arr[4]=educationNum;


                if(maritalStatus.equals("Married-civ-spouse"))
                        arr[5]=0;
                else if(maritalStatus.equals("Divorced"))
                        arr[5]=1;
                else if(maritalStatus.equals("Never-married"))
                        arr[5]=2;
                else if(maritalStatus.equals("Separated"))
                        arr[5]=3;
                else if(maritalStatus.equals("Widowed"))
                        arr[5]=4;
                else if(maritalStatus.equals("Married-spouse-absent"))
                        arr[5]=5;
                else if(maritalStatus.equals("Married-AF-spouse"))
                        arr[5]=6;


                if(occupation.equals("Tech-support"))
                        arr[6]=0;
                else if(occupation.equals("Craft-repair"))
                        arr[6]=1;
                else if(occupation.equals("Other-service"))
                        arr[6]=2;
                else if(occupation.equals("Sales"))
                        arr[6]=3;
                else if(occupation.equals("Exec-managerial"))
                        arr[6]=4;
                else if(occupation.equals("Prof-specialty"))
                        arr[6]=5;
                else if(occupation.equals("Handlers-cleaners"))
                        arr[6]=6;
                else if(occupation.equals("Machine-op-inspct"))
                        arr[6]=7;
                else if(occupation.equals("Adm-clerical"))
                        arr[6]=8;
                else if(occupation.equals("Farming-fishing"))
                        arr[6]=9;
                else if(occupation.equals("Transport-moving"))
                        arr[6]=10;
                else if(occupation.equals("Priv-house-serv"))
                        arr[6]=11;
                else if(occupation.equals("Protective-serv"))
                        arr[6]=12;
                else if(occupation.equals("Armed-Forces"))
                        arr[6]=13;


                if(relationship.equals("Wife"))
                        arr[7]=0;
                else if(relationship.equals("Own-child"))
                        arr[7]=1;
                else if(relationship.equals("Husband"))
                        arr[7]=2;
                else if(relationship.equals("Not-in-family"))
                        arr[7]=3;
                else if(relationship.equals("Other-relative"))
                        arr[7]=4;
                else if(relationship.equals("Unmarried"))
                        arr[7]=5;


                if(race.equals("White"))
                        arr[8]=0;
                else if(race.equals("Asian-Pac-Islander"))
                        arr[8]=1;
                else if(race.equals("Amer-Indian-Eskimo"))
                        arr[8]=2;
                else if(race.equals("Other"))
                        arr[8]=3;
                else if(race.equals("Black"))
                        arr[8]=4;

                if(sex.equals("Male"))
                        arr[9]=0;
                else if(sex.equals("Female"))
                        arr[9]=1;


                arr[10]=capitalGain;


                arr[11]=capitalLoss;



                arr[12]=hoursPerWeek;

                if(nativeCountry.equals("United-States"))
                        arr[13]=0;
                else if(nativeCountry.equals("Cambodia"))
                        arr[13]=1;
                else if(nativeCountry.equals("England"))
                        arr[13]=2;
                else if(nativeCountry.equals("Puerto-Rico"))
                        arr[13]=3;
                else if(nativeCountry.equals("Canada"))
                        arr[13]=4;
                else if(nativeCountry.equals("Germany"))
                        arr[13]=5;
                else if(nativeCountry.equals("Outlying-US(Guam-USVI-etc)"))
                        arr[13]=6;
                else if(nativeCountry.equals("India"))
                        arr[13]=7;
                else if(nativeCountry.equals("Japan"))
                        arr[13]=8;
                else if(nativeCountry.equals("Greece"))
                        arr[13]=9;
                else if(nativeCountry.equals("South"))
                        arr[13]=10;
                else if(nativeCountry.equals("China"))
                        arr[13]=11;
                else if(nativeCountry.equals("Cuba"))
                        arr[13]=12;
                else if(nativeCountry.equals("Iran"))
                        arr[13]=13;
                else if(nativeCountry.equals("Honduras"))
                        arr[13]=14;
                else if(nativeCountry.equals("Philippines"))
                        arr[13]=15;
                else if(nativeCountry.equals("Italy"))
                        arr[13]=16;
                else if(nativeCountry.equals("Poland"))
                        arr[13]=17;
                else if(nativeCountry.equals("Jamaica"))
                        arr[13]=18;
                else if(nativeCountry.equals("Vietnam"))
                        arr[13]=19;
                else if(nativeCountry.equals("Mexico"))
                        arr[13]=20;
                else if(nativeCountry.equals("Portugal"))
                        arr[13]=21;
                else if(nativeCountry.equals("Ireland"))
                        arr[13]=22;
                else if(nativeCountry.equals("France"))
                        arr[13]=23;
                else if(nativeCountry.equals("Dominican-Republic"))
                        arr[13]=24;
                else if(nativeCountry.equals("Laos"))
                        arr[13]=25;
                else if(nativeCountry.equals("Ecuador"))
                        arr[13]=26;
                else if(nativeCountry.equals("Taiwan"))
                        arr[13]=27;
                else if(nativeCountry.equals("Haiti"))
                        arr[13]=28;
                else if(nativeCountry.equals("Columbia"))
                        arr[13]=29;
                else if(nativeCountry.equals("Hungary"))
                        arr[13]=30;
                else if(nativeCountry.equals("Guatemala"))
                        arr[13]=31;
                else if(nativeCountry.equals("Nicaragua"))
                        arr[13]=32;
                else if(nativeCountry.equals("Scotland"))
                        arr[13]=33;
                else if(nativeCountry.equals("Thailand"))
                        arr[13]=34;
                else if(nativeCountry.equals("Yugoslavia"))
                        arr[13]=35;
                else if(nativeCountry.equals("El-Salvador"))
                        arr[13]=36;
                else if(nativeCountry.equals("Trinadad&Tobago"))
                        arr[13]=37;
                else if(nativeCountry.equals("Peru"))
                        arr[13]=38;
                else if(nativeCountry.equals("Hong"))
                        arr[13]=39;
                else if(nativeCountry.equals("Holand-Netherlands"))
                        arr[13]=40;
                arr[14] = result;
                data.add(arr);
            }
            	 
        }       
         
        br.close();
    }
       
}
