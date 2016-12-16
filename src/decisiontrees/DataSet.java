package decisiontrees;

/**
 * DataSet is an object which captures the information of one row in the Data.
 */
class DataSet{
	int age;
	String workClass;
	int fnlwgt;
	String education;
	int educationNum;
	String maritalStatus;
	String occupation;
	String relationship;
	String race;
	String sex;
	int capitalGain;
	int capitalLoss;
	int hoursPerWeek;
	String nativeCountry;
	int result;
	/**
	 * @param age : The age attribute in the dataset
	 * @param workClass : The work Class attribute in the dataset
	 * @param fnlwgt : The Fnlwgt attribute in the dataset
	 * @param education : The Education attribute in the dataset
	 * @param educationNum : The Education Number attribute in the dataset
	 * @param maritalStatus : The Marital Status attribute in the dataset
	 * @param occupation : The Occupation attribute in the dataset
	 * @param relationship : The Relationship attribute in the dataset
	 * @param race : The Race attribute in the dataset
	 * @param sex : The Sex attribute in the dataset
	 * @param capitalGain : The Capital Gain attribute in the dataset
	 * @param capitalLoss : The Capital Loss attribute in the dataset
	 * @param hoursPerWeek : The Hours Per Week attribute in the dataset
	 * @param nativeCountry : The Native - Country attribute in the dataset
	 * @param income : The income attribute in the dataset
	 */
	DataSet(int age,String workClass,int fnlwgt,String education,int educationNum,String maritalStatus,String occupation,String relationship,String race,String sex,int capitalGain,int capitalLoss,int hoursPerWeek,String nativeCountry,String income){
		this.age =  age;
		this.workClass = workClass;
		this.fnlwgt = fnlwgt;
		this.education = education;
		this.educationNum = educationNum;
		this.maritalStatus = maritalStatus;
		this.occupation = occupation;
		this.relationship = relationship;
		this.race = race;
		this.sex = sex;
		this.capitalGain = capitalGain;
		this.capitalLoss = capitalLoss;
		this.hoursPerWeek = hoursPerWeek;
		this.nativeCountry = nativeCountry;
		result = income.equals("<=50K")?1:-1;
	}
	
   @Override
   public String toString() {
	   /** Prints one row of the dataSet in the string format */
        return ("Age: "+this.age+" Work Class: "+ this.workClass +" fnlwgt: "+ this.fnlwgt +" Education : " + this.education
        		+" Education Num: "+this.educationNum+" Marital Status "+this.maritalStatus+" Occuptaion: "+this.occupation
        		+ " RelationShip Status: "+this.relationship+" Race: "+this.race+" Sex: "+this.sex
        		+ " Capital Gain: "+this.capitalGain+" Capital Loss: "+this.capitalLoss+" Hours Per Week: "+this.hoursPerWeek
        		+ " nativeCountry :"+this.nativeCountry+" Result: "+this.result );
   }
	
}