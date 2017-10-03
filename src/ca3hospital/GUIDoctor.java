package ca3hospital;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

public class GUIDoctor extends GUIBase{

	public GUIDoctor(IList mylist) {
		super(mylist);
		lblTitle.setText("DOCTOR SCREEN");
		taTreatment.setBackground(Color.WHITE);
		taTreatment.setEditable(true);
	
		
		addWindowListener(new WindowAdapter() {				// Listen for when the window closes!
		    public void windowClosing(WindowEvent e) {		
		    	System.out.println("DETECTED: Doctor Window is closing!");				//if window is closed w/o submitting
		    	
		    	if(patient!=null){
		    		list.add(patient);			
		    	}										    	
		    }
		});
			
		btn1.setText("Clear");
		btn1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				taTreatment.setText(null);
			}
		});	
		
		btn2.setText("Next Patient");
		btn2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				node=list.getHighestPriority();		
				if(node==null){
					btn3.setEnabled(false);
					clearall();
					message.setText("No Patients awaiting nurse at this time. Please refresh later. (Updated "+
								new SimpleDateFormat("HH:mm:ss,  dd/MMM/yy").format(System.currentTimeMillis()) +")");
	
					patient=null;			
					node=null;				// remove links to any previous patients (due to our WindowListener)
				}
				else{
					btn3.setEnabled(true);			// else if patient found, then enable SUBMIT button
					btn2.setEnabled(false);					//also disable NEXT button (so we dont keep calling from list)
					
					patient= (Patient)node.getData();	//actually grab patient object from node
					list.findRemoveNode(node);					//remove from list so multiple doctors dont call same patient
					setfields(patient);				//populate gui form with patient details
				}
			}
		});	
	
		btn3.setText("Submit");
		btn3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(validatefields()){
					submit();
					clearall();				//set gui form fields blank
					
					patient=null;				//remove links to patient because of windowListener code
					node=null;
					btn3.setEnabled(false);
					btn2.setEnabled(true);
				}
				else {	message.setText("Error! Empty fields detected, or unsuitable text");	}
			}
		});		
		
	}

	@Override
	boolean validatefields() {
		if (taTreatment.getText().isEmpty()==false ){		return true;	}		//check if doctor has inputted treatment details
		else {return false;}	
		}

	@Override
	void submit() {
		patient.setDoctorTreatment(taTreatment.getText().toString());
		patient.assessmentStage++;
		
		list.add(patient);
	}

	@Override
	void clearinputs() {
		taTreatment.setText(null);				//if doctor makes mistake and wants to delete their input form progress	
	}

}
