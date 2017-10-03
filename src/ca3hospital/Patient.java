package ca3hospital;

public class Patient implements IData{					

	String forename;
	String surname;
	String PPS;
	String address;
	String phone;
	String symptoms;
	String assessment;
	String treatment;
	private int priority;
	int assessmentStage;						// to mark which stage of hospital visit a patient is awaiting
	
	
	public Patient(String fn,String sn,String p,String addr,String ph,String symp){
		this.forename=fn;
		this.surname=sn;
		this.PPS=p.toUpperCase();
		this.address=addr;
		this.phone=ph;
		this.symptoms=symp;
		
		//AUTO-GENERATED,	 (not declared by user)
		this.priority=-1;
		this.assessmentStage=2;				//1 = rec, 2= nurse, 3= doc.	Assume we skip stage 1 through simple patient creation?
		this.assessment="";
		this.treatment="";				//used to be null, but save & reload from db puts in null as text, WHICH can be submitted
	}

	public String getPK(){
		return this.PPS;					//my primary key
	}
	
	public int getPriority(){
		return this.priority;
	}
	
	public int getStage(){					//assessment stage
		return this.assessmentStage;
	}
	
	public void setPriority(int p){
		if(p>0 && p<=10){
			this.priority=p;
		}
	}
	
	public void setNurseAssessment(String s){
		this.assessment=s;
	}
	public void setDoctorTreatment(String s){
		this.treatment=s;
	}

}


