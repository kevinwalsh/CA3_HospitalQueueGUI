package ca3hospital;

public interface IData {

	public int getPriority();		//treatment urgency
	public int getStage();		//for multi-stage validation (awaiting nurse, doctor, etc)
	public String getPK();			//assuming our data PK will always be a string... safe to assume for this project

}
