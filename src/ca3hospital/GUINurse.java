package ca3hospital;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GUINurse extends GUIBase{

	JSpinner spinner;
	
	public GUINurse(IList mylist) {
		super(mylist);
		lblTitle.setText("NURSE SCREEN");
		tfPriority.setVisible(false);
		taNurseAssessment.setBackground(Color.WHITE);
		taNurseAssessment.setEditable(true);
		
		lblTreatmentPriority.setVisible(false);
		lblNurses.setVisible(false);
		lblAssessment.setVisible(false);
		lblDoctors.setVisible(false);
		lblTreatment.setVisible(false);
		taTreatment.setVisible(false);
	

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(5, 1, 10, 1));
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinner.setBounds(250, 312, 46, 20);
		contentPane.add(spinner);
		
		
		addWindowListener(new WindowAdapter() {				
		    public void windowClosing(WindowEvent e) {					    	
		    	if(patient!=null){
		    		list.add(patient);			//clever addition: when calling patient, take OUT of list. will put back in EITHER
		    	}								// upon submit, OR, when user closes this window without saving	    	
		    }
		});
		
		btn1.setText("Clear");
		btn1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearinputs();
				}
		});	
		
		btn2.setText("Next Patient");
		btn2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				node=list.callNext(2);
				if(node==null){
					btn3.setEnabled(false);
					clearall();
					message.setText("No Patients awaiting nurse at this time. Please refresh later. (Updated "+
							new SimpleDateFormat("HH:mm:ss,  dd/MMM/yy").format(System.currentTimeMillis()) +")");
					patient=null;			//just to ensure we dont have links to a patient in this gui
				}
				else{
					btn3.setEnabled(true);
					
					patient= (Patient)node.getData();
					list.findRemoveNode(node);					//when we find patient, remove from list so nobody else calls them too
					setfields(patient);
					btn2.setEnabled(false);				//found & removed bug- could keep pressing "next" which would overwrite
				}										//	& therefore remove patients from list!
			}
		});
	
		btn3.setText("Submit");
		btn3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Submit button from nurse");
				if(validatefields()){
					submit();
					list.add(patient);				//add patient back into list when nurse finished
					patient=null;				// set patient & node = null so we dont try to add them twice in event of window close
					node=null;
					
					clearall();				
					btn3.setEnabled(false);
					btn2.setEnabled(true);
					
				}
				else {	message.setText("Error! Empty fields detected, or unsuitable text");	}
			}
		});		
		
		
	}

	@Override
	boolean validatefields() {		//dont even need to validate priority because spinner prevents user from selecting outside range
		if (taNurseAssessment.getText().isEmpty()==false ){		return true;	}
		else {return false;}
		}

	@Override
	void submit() {
		patient.setNurseAssessment(taNurseAssessment.getText());
		patient.setPriority((int) spinner.getValue());
		patient.assessmentStage++;	
	}

	@Override
	void clearinputs() {
		taNurseAssessment.setText(null);
		spinner.setValue(5);			//original value
		}

}
