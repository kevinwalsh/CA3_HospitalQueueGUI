package ca3hospital;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class GUIReceptionist extends GUIBase{

	public GUIReceptionist(IList mylist) {
		super(mylist);
		lblTitle.setText("RECEPTIONIST SCREEN");
		tfForename.setEditable(true);
		tfSurname.setEditable(true);
		tfPPS.setEditable(true);
		tfPhone.setEditable(true);
		tfAddress.setEditable(true);
		taSymptoms.setEditable(true);
		taSymptoms.setBackground(Color.WHITE);
		
		lblTreatmentPriority.setVisible(false);
		lblNurses.setVisible(false);
		lblAssessment.setVisible(false);
		lblDoctors.setVisible(false);
		lblTreatment.setVisible(false);
		
		taNurseAssessment.setVisible(false);
		tfPriority.setVisible(false);
		taTreatment.setVisible(false);
		
	
		btn1.setText("Clear");
		btn1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearall();
			}
		});	
		
		btn2.setVisible(false);
	
		btn3.setEnabled(true);
		btn3.setText("Submit");
		btn3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(validatefields()==true){									// check if all fields have been filled in
					if(	duplicatePK(tfPPS.getText().toUpperCase())==false){		//quickly check that the PPS primary key isnt already in list
					submit();
					}
					else	{	message.setText("Error! Duplicate Primary Keys! (PPS Number)");	}
				}
				else {	message.setText("Error! Empty fields detected, or unsuitable text");	}
			}
		});		
		
	}

	@Override
	boolean validatefields() {
		if (												
				tfForename.getText().isEmpty()==false &&			//return true if all are empty, i.e. all "isempty"s =false
				tfSurname.getText().isEmpty()==false &&
				tfPPS.getText().isEmpty()==false &&
				tfAddress.getText().isEmpty()==false &&
				tfPhone.getText().isEmpty()==false &&
				
				taSymptoms.getText().isEmpty()==false){
			return true;
		}
		else {return false;}
		}
	
	private Boolean duplicatePK(String pk){			//consult list for a unique PK
		if(list.checkForPK(pk)){	return true;	}				//false =list already contains PK! Cannot complete!
					else {	return false; 	}						//true = did NOT find a clashing PK; i.e. all good!
	}

	@Override
	void submit() {		
		list.add(new Patient(tfForename.getText(),tfSurname.getText(),tfPPS.getText().toUpperCase(),tfAddress.getText(),tfPhone.getText(),
				taSymptoms.getText()));
		clearall();
		
	}

	@Override
	void clearinputs() {
		//taTreatment.setText(null);			//dont need anything in here; already done via base class's clearall
	}
	
	
}
