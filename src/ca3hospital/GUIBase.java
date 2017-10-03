package ca3hospital;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public abstract class GUIBase extends JFrame {

	IList list;
	
	protected JPanel contentPane;
	JLabel message;
	protected JTextField tfForename;
	protected JTextField tfSurname;
	protected JTextField tfPPS;
	protected JTextField tfAddress;
	protected JTextField tfPhone;
	JTextArea taSymptoms;
	JTextArea taNurseAssessment;
	JTextArea taTreatment;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	
	
	JLabel lblTitle;
	JLabel lblSubtitle;
	JLabel lblForename;
	JLabel lblSurname;
	JLabel lblPpsNo;
	JLabel lblAddress;
	JLabel lblPhone;
	JLabel lblAilment;
	JLabel lblNurses;
	JLabel lblAssessment;
	JLabel lblTreatmentPriority;
	JLabel lblDoctors;
	JLabel lblTreatment;
	JLabel lblSymptoms;
	
	Patient patient;
	Node node;
	protected JTextField tfPriority;

	
	public GUIBase(IList mylist) {
		this.list= mylist;
	
		setContent();				//chucking all JLabel, etc initialization into its own method, to clean up code abit. Leaving buttons!
		//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//closes entire appN if this line runs

		
		 btn1 = new JButton("Button 1");
		btn1.setBounds(20, 411, 93, 23);
		contentPane.add(btn1);

		 btn2 = new JButton("Button 2");
		//btn2.setEnabled(false);
		btn2.setBounds(161, 411, 89, 23);
		contentPane.add(btn2);		
	
		 btn3 = new JButton("Button 3");
		btn3.setBounds(298, 411, 89, 23);
		contentPane.add(btn3);
		btn3.setEnabled(false);					//by default on startup, we disable SUBMIT until we call a patient	
	}
	
	//--------------------- Start of SetContent()
	protected void setContent(){
		setBounds(100, 100, 454, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 lblTitle = new JLabel("BASE SCREEN");
		 lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(10, 11, 406, 42);
		contentPane.add(lblTitle);
		
		 lblSubtitle = new JLabel("PATIENT DETAILS:");
		lblSubtitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubtitle.setBounds(10, 53, 124, 20);
		contentPane.add(lblSubtitle);
		
		 lblForename = new JLabel("Forename:");
		lblForename.setBounds(20, 84, 73, 14);
		contentPane.add(lblForename);
		
		tfForename = new JTextField();
		tfForename.setBounds(103, 81, 86, 20);
		contentPane.add(tfForename);
		tfForename.setColumns(10);
		tfForename.setEditable(false);
		
		 lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(221, 84, 46, 14);
		contentPane.add(lblSurname);
		
		 lblPpsNo = new JLabel("PPS No:");
		lblPpsNo.setBounds(30, 118, 46, 14);
		contentPane.add(lblPpsNo);
		
		 lblAddress = new JLabel("Address:");
		lblAddress.setBounds(20, 153, 46, 14);
		contentPane.add(lblAddress);
		
		tfSurname = new JTextField();
		tfSurname.setEditable(false);
		tfSurname.setBounds(291, 81, 86, 20);
		contentPane.add(tfSurname);
		tfSurname.setColumns(10);
		
		tfPPS = new JTextField();
		tfPPS.setEditable(false);
		tfPPS.setBounds(103, 115, 86, 20);
		contentPane.add(tfPPS);
		tfPPS.setColumns(10);
		
		 lblPhone = new JLabel("Contact No. :");
		lblPhone.setBounds(214, 118, 65, 14);
		contentPane.add(lblPhone);
		
		taSymptoms = new JTextArea();
		taSymptoms.setBackground(new Color(192, 192, 192));
		taSymptoms.setEditable(false);
		taSymptoms.setBounds(89, 182, 288, 49);
		contentPane.add(taSymptoms);
		
		 lblAilment = new JLabel("Ailment/");
		lblAilment.setBounds(20, 187, 46, 14);
		contentPane.add(lblAilment);
		
		 lblSymptoms = new JLabel("Symptoms:");
		lblSymptoms.setBounds(20, 204, 56, 14);
		contentPane.add(lblSymptoms);
		
		tfAddress = new JTextField();
		tfAddress.setEditable(false);
		tfAddress.setBounds(103, 150, 274, 20);
		contentPane.add(tfAddress);
		tfAddress.setColumns(10);
		
		tfPhone = new JTextField();
		tfPhone.setEditable(false);
		tfPhone.setBounds(291, 115, 86, 20);
		contentPane.add(tfPhone);
		tfPhone.setColumns(10);
		
		 taNurseAssessment = new JTextArea();
		 taNurseAssessment.setBackground(new Color(192, 192, 192));
		 taNurseAssessment.setEditable(false);
		taNurseAssessment.setBounds(89, 252, 288, 49);
		contentPane.add(taNurseAssessment);
		
		 lblNurses = new JLabel("Nurse's");
		lblNurses.setBounds(20, 257, 46, 14);
		contentPane.add(lblNurses);
		
		 lblAssessment = new JLabel("Assessment:");
		lblAssessment.setBounds(10, 269, 66, 19);
		contentPane.add(lblAssessment);
		
		 lblTreatmentPriority = new JLabel("Treatment Priority:");
		lblTreatmentPriority.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTreatmentPriority.setBounds(20, 312, 106, 23);
		contentPane.add(lblTreatmentPriority);
		
		tfPriority = new JTextField();
		tfPriority.setForeground(Color.RED);
		tfPriority.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tfPriority.setEditable(false);
		tfPriority.setEnabled(false);
		tfPriority.setBounds(136, 313, 86, 20);
		contentPane.add(tfPriority);
		tfPriority.setColumns(10);
		
		 taTreatment = new JTextArea();
		 taTreatment.setEditable(false);
		 taTreatment.setBackground(new Color(192, 192, 192));
		taTreatment.setBounds(89, 339, 288, 42);
		contentPane.add(taTreatment);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		taSymptoms.setBorder(border);
		taNurseAssessment.setBorder(border);
		taTreatment.setBorder(border);
		
		 lblDoctors = new JLabel("Doctor's");
		lblDoctors.setBounds(20, 344, 56, 14);
		contentPane.add(lblDoctors);
		
		 lblTreatment = new JLabel("Treatment: ");
		lblTreatment.setBounds(10, 357, 66, 14);
		contentPane.add(lblTreatment);
		
		message = new JLabel("");
		message.setFont(new Font("Tahoma", Font.PLAIN, 10));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setForeground(Color.RED);
		message.setBounds(10, 386, 418, 14);
		contentPane.add(message);
	}
	//----------------------- end of setcontent()
	
	abstract boolean validatefields();

	abstract void submit();
	
	protected void setfields(Patient p){
		if(p==null){clearall();}
		else{
		tfForename.setText(p.forename);
		tfSurname.setText(p.surname);
		tfPPS.setText(p.PPS);
		tfAddress.setText(p.address);
		tfPhone.setText(p.phone);
		taSymptoms.setText(p.symptoms);
		taNurseAssessment.setText(p.assessment);
		tfPriority.setText(String.valueOf(p.getPriority()));
		
		message.setText(null);
		}
	}
	
	protected void clearall(){
		tfForename.setText(null);
		tfSurname.setText(null);
		tfPPS.setText(null);
		tfAddress.setText(null);
		tfPhone.setText(null);
		taSymptoms.setText(null);
		taNurseAssessment.setText(null);
		taTreatment.setText(null);
		
		message.setText(null);
		clearinputs();
	}
	abstract void clearinputs();

}
