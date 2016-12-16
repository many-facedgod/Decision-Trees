package decisiontrees;

/**
 * A class which stores the original values of the attributes.
 * Used as a reference class to get back the original string of the attribute based on the value as in the matrix
 * @author Sandeep, Snehal
 */
public class DataRef {
	public String majorRef[] = new String[15];
	public String attrRef[][] = new String[15][];
	DataRef(){
		majorRef[0] = "age";
		majorRef[1] = "workClass";
		majorRef[2] = "fnlwgt";
		majorRef[3] = "education";
		majorRef[4] = "educationNum";
		majorRef[5] = "maritalStatus";
		majorRef[6] = "occupation";
		majorRef[7] = "relationship";
		majorRef[8] = "race";
		majorRef[9] = "sex";
		majorRef[10] = "capitalGain";
		majorRef[11] = "capitalLoss";
		majorRef[12] = "hoursPerWeek";
		majorRef[13] = "nativeCountry";
		majorRef[14] = "result";
		
		attrRef[0] = new String[2];
		attrRef[0][0] = "<=";
		attrRef[0][1] = ">";
		
		attrRef[1] = new String[8];
		attrRef[1][0] = "Private";
		attrRef[1][1] = "Self-emp-not-inc";
		attrRef[1][2] = "Self-emp-inc";
		attrRef[1][3] = "Federal-gov";
		attrRef[1][4] = "Local-gov";
		attrRef[1][5] = "State-gov";
		attrRef[1][6] = "Without-pay";
		attrRef[1][7] = "Never-worked";
		
		attrRef[2] = new String[2];
		attrRef[2][0] = "<=";
		attrRef[2][1] = ">";
		
		attrRef[3] = new String[16];
		attrRef[3][0] = "Bachelors";
		attrRef[3][1] = "Some-college";
		attrRef[3][2] = "11th";
		attrRef[3][3] = "HS-grad";
		attrRef[3][4] = "Prof-school";
		attrRef[3][5] = "Assoc-acdm";
		attrRef[3][6] = "Assoc-voc";
		attrRef[3][7] = "9th";
		attrRef[3][8] = "7th-8th";
		attrRef[3][9] = "12th";
		attrRef[3][10] = "Masters";
		attrRef[3][11] = "1st-4th";
		attrRef[3][12] = "10th";
		attrRef[3][13] = "Doctorate";
		attrRef[3][14] = "5th-6th";
		attrRef[3][15] = "Preschool";
		
		attrRef[4] = new String[2];
		attrRef[4][0] = "<=";
		attrRef[4][1] = ">";
		
		attrRef[5] = new String[7];
		attrRef[5][0] = "Married-civ-spouse";
		attrRef[5][1] = "Divorced";
		attrRef[5][2] = "Never-married";
		attrRef[5][3] = "Separated";
		attrRef[5][4] = "Widowed";
		attrRef[5][5] = "Married-spouse-absent";
		attrRef[5][6] = "Married-AF-spouse";
				
		attrRef[6] = new String[14];
		attrRef[6][0] = "Tech-support";
		attrRef[6][1] = "Craft-repair";
		attrRef[6][2] = "Other-service";
		attrRef[6][3] = "Sales";
		attrRef[6][4] = "Exec-managerial";
		attrRef[6][5] = "Prof-specialty";
		attrRef[6][6] = "Handlers-cleaners";
		attrRef[6][7] = "Machine-op-inspct";
		attrRef[6][8] = "Adm-clerical";
		attrRef[6][9] = "Farming-fishing";
		attrRef[6][10] = "Transport-moving";
		attrRef[6][11] = "Priv-house-serv";
		attrRef[6][12] = "Protective-serv";
		attrRef[6][13] = "Armed-Forces";
		
		attrRef[7] = new String[6];
		attrRef[7][0] = "Wife";
		attrRef[7][1] = "Own-child";
		attrRef[7][2] = "Husband";
		attrRef[7][3] = "Not-in-family";
		attrRef[7][4] = "Other-relative";
		attrRef[7][5] = "Unmarried";
		
		attrRef[8] = new String[5];
		attrRef[8][0] = "White";
		attrRef[8][1] = "Asian-Pac-Islander";
		attrRef[8][2] = "Amer-Indian-Eskimo";
		attrRef[8][3] = "Other";
		attrRef[8][4] = "Black";
		
		attrRef[9] = new String[2];
		attrRef[9][0] = "Male";
		attrRef[9][1] = "Female";
		
		attrRef[10] = new String[2];
		attrRef[10][0] = "<=";
		attrRef[10][1] = ">";
		
		attrRef[11] = new String[2];
		attrRef[11][0] = "<=";
		attrRef[11][1] = ">";
		
		attrRef[12] = new String[2];
		attrRef[12][0] = "<=";
		attrRef[12][1] = ">";
		
		attrRef[13] = new String[41];
		attrRef[13][0]  = "United-States" ;
		attrRef[13][1]  = "Cambodia" ;
		attrRef[13][2]  = "England" ;
		attrRef[13][3]  = "Puerto-Rico" ;
		attrRef[13][4]  = "Canada" ;
		attrRef[13][5]  = "Germany" ;
		attrRef[13][6]  = "Outlying-US(Guam-USVI-etc)" ;
		attrRef[13][7]  = "India" ;
		attrRef[13][8]  = "Japan" ;
		attrRef[13][9]  = "Greece" ;
		attrRef[13][10]  = "South" ;
		attrRef[13][11]  = "China" ;
		attrRef[13][12]  = "Cuba" ;
		attrRef[13][13]  = "Iran" ;
		attrRef[13][14]  = "Honduras" ;
		attrRef[13][15]  = "Philippines" ;
		attrRef[13][16]  = "Italy" ;
		attrRef[13][17]  = "Poland" ;
		attrRef[13][18]  = "Jamaica" ;
		attrRef[13][19]  = "Vietnam" ;
		attrRef[13][20]  = "Mexico" ;
		attrRef[13][21]  = "Portugal" ;
		attrRef[13][22]  = "Ireland" ;
		attrRef[13][23]  = "France" ;
		attrRef[13][24]  = "Dominican-Republic" ;
		attrRef[13][25]  = "Laos" ;
		attrRef[13][26]  = "Ecuador" ;
		attrRef[13][27]  = "Taiwan" ;
		attrRef[13][28]  = "Haiti" ;
		attrRef[13][29]  = "Columbia" ;
		attrRef[13][30]  = "Hungary" ;
		attrRef[13][31]  = "Guatemala" ;
		attrRef[13][32]  = "Nicaragua" ;
		attrRef[13][33]  = "Scotland" ;
		attrRef[13][34]  = "Thailand" ;
		attrRef[13][35]  = "Yugoslavia" ;
		attrRef[13][36]  = "El-Salvador" ;
		attrRef[13][37]  = "Trinadad&Tobago" ;
		attrRef[13][38]  = "Peru" ;
		attrRef[13][39]  = "Hong" ;
		attrRef[13][40]  = "Holand-Netherlands" ;
		
		attrRef[14] = new String[2];
		attrRef[14][0] = "<=50K";
		attrRef[14][1] = ">50K";
	}
}