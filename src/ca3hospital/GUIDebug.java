package ca3hospital;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class GUIDebug extends JFrame {

	IList list;
	Node node;
	
	private JPanel contentPane;
	private JTextField tfForename;
	private JTextField tfSurname;
	private JTextField tfPPS;
	private JTextField tfAddress;
	private JTextField tfPhone;
	private JTextArea taSymptoms;
	private JTextField tfPriority;
	private JTextField tfStage;
	JTextArea taAssessment;
	JTextArea taTreatment;

	JButton btnPrev;
	JButton btnNext;
	

	public GUIDebug(IList mylist) {
		this.list= mylist;
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//closes entire appN if this line runs
		setBounds(100, 100, 453, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblForename = new JLabel("Forename:");
		lblForename.setBounds(20, 11, 73, 14);
		contentPane.add(lblForename);
		
		tfForename = new JTextField();
		tfForename.setBounds(103, 8, 86, 20);
		contentPane.add(tfForename);
		tfForename.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(20, 36, 46, 14);
		contentPane.add(lblSurname);
		
		JLabel lblPpsNo = new JLabel("PPS No:");
		lblPpsNo.setBounds(20, 74, 46, 14);
		contentPane.add(lblPpsNo);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(224, 11, 46, 14);
		contentPane.add(lblAddress);
		
		tfSurname = new JTextField();
		tfSurname.setBounds(103, 39, 86, 20);
		contentPane.add(tfSurname);
		tfSurname.setColumns(10);
		
		tfPPS = new JTextField();
		tfPPS.setBounds(103, 71, 86, 20);
		contentPane.add(tfPPS);
		tfPPS.setColumns(10);
		
		JLabel lblPhone = new JLabel("Contact No. :");
		lblPhone.setBounds(205, 42, 65, 14);
		contentPane.add(lblPhone);
		
		 taSymptoms = new JTextArea();
		taSymptoms.setBounds(89, 128, 288, 49);
		contentPane.add(taSymptoms);
		
		JLabel lblSymptoms = new JLabel("Symptoms:");
		lblSymptoms.setBounds(20, 133, 56, 14);
		contentPane.add(lblSymptoms);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(291, 8, 86, 20);
		contentPane.add(tfAddress);
		tfAddress.setColumns(10);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(291, 39, 86, 20);
		contentPane.add(tfPhone);
		tfPhone.setColumns(10);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.setBounds(312, 310, 89, 23);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("Submit button pressed in debug; does nothing");
				updatePatient();
			}
		});
		
		 btnNext = new JButton("Next");
		btnNext.setBounds(148, 310, 108, 23);
		contentPane.add(btnNext);
		btnNext.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(node!=null){		getNext();	}
				else{System.out.println("No entries at this time");	}
				
			}
		});
		
		JLabel lblAssessment = new JLabel("Assessment:");
		lblAssessment.setBounds(20, 205, 73, 14);
		contentPane.add(lblAssessment);
		
		 taAssessment = new JTextArea();
		taAssessment.setBounds(103, 188, 278, 49);
		contentPane.add(taAssessment);
		
		JLabel lblPriority = new JLabel("Priority:");
		lblPriority.setBounds(20, 99, 46, 14);
		contentPane.add(lblPriority);
		
		JLabel lblStage = new JLabel("Stage:");
		lblStage.setBounds(239, 99, 46, 14);
		contentPane.add(lblStage);
		
		tfPriority = new JTextField();
		tfPriority.setBounds(103, 102, 86, 20);
		contentPane.add(tfPriority);
		tfPriority.setColumns(10);
		
		tfStage = new JTextField();
		tfStage.setBounds(291, 97, 86, 20);
		contentPane.add(tfStage);
		tfStage.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Treatment:");
		lblNewLabel.setBounds(20, 259, 73, 14);
		contentPane.add(lblNewLabel);
		
		 taTreatment = new JTextArea();
		taTreatment.setBounds(103, 251, 278, 48);
		contentPane.add(taTreatment);
		
		 btnPrev = new JButton("Prev");
		btnPrev.setBounds(20, 310, 108, 23);
		contentPane.add(btnPrev);
		btnPrev.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(node!=null){		getPrev();	}
				else{System.out.println("No entries at this time");	}
				
			}
		});
	
	node=list.getNode(1);
	if(node!=null){
		setFields(node.getData());
		}
	else{System.out.println("No entries at this time");	}
	}
	
	private void getPrev(){
		node=node.getPrev();
		if (node==null){ btnPrev.setEnabled(false);
			node=list.getNode(1);
		}
		else{btnNext.setEnabled(true);		//prob overkill doing it every time (for OPPOSITE), but safer!
			setFields(node.getData());}
	}
	
	private void getNext(){
		node=node.getNext();
		if (node==null){ btnNext.setEnabled(false);
			node=list.getNode(list.getSize());
		}
		else{ btnPrev.setEnabled(true);
			setFields(node.getData());}
	}
	
	private void setFields(IData data){
		Patient temp = (Patient) data;
		
		tfForename.setText(temp.forename);
		tfSurname.setText(temp.surname);
		tfPPS.setText(temp.PPS);
		tfAddress.setText(temp.address);
		tfPhone.setText(temp.phone);
		tfPriority.setText(String.valueOf(temp.getPriority()));
		tfStage.setText(String.valueOf(temp.getStage()));
		taSymptoms.setText(temp.symptoms);
		taAssessment.setText(temp.assessment);
		taTreatment.setText(temp.treatment);
	}

	private void updatePatient(){
		Patient p = (Patient)node.getData();
		p.forename=tfForename.getText().toString();
		p.surname=tfSurname.getText().toString();
		p.address=tfAddress.getText().toString();
		p.PPS=tfPPS.getText().toString();
		p.phone=tfPhone.getText().toString();
		p.symptoms=taSymptoms.getText().toString();
		p.assessment=taAssessment.getText().toString();
		p.treatment=taTreatment.getText().toString();
		p.setPriority(Integer.parseInt(tfPriority.getText()));
		p.assessmentStage=Integer.parseInt(tfStage.getText().toString());
	}
	
}
