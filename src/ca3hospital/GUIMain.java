package ca3hospital;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GUIMain extends JFrame {

	private JPanel contentPane;

	IList list;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IList list= new DoubleLinkedList ();				//create new list/queue
					GUIMain frame = new GUIMain(list);					// set up main gui screen and pass in list
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIMain(IList list) {
		this.list = list;
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblSelect = new JLabel("PLEASE SELECT USER TYPE:");
		lblSelect.setBounds(31, 80, 163, 23);
		contentPane.add(lblSelect);
		
		JButton btnReceptionist = new JButton("Receptionist");
		btnReceptionist.setBounds(204, 80, 112, 23);
		contentPane.add(btnReceptionist);
		btnReceptionist.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GUIReceptionist guireceptionist = new GUIReceptionist(list);
				guireceptionist.setVisible(true);
			}
		});
		
		JButton btnNurse = new JButton("Nurse");
		btnNurse.setBounds(204, 128, 112, 23);
		contentPane.add(btnNurse);
		btnNurse.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Launching Nurse Window");
				GUINurse guinurse = new GUINurse(list);
				guinurse.setVisible(true);

			}
		});
		
		JButton btnDoctor = new JButton("Doctor");
		btnDoctor.setBounds(204, 176, 112, 23);
		contentPane.add(btnDoctor);
		btnDoctor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Launching Doctor Window");
				GUIDoctor guidoctor = new GUIDoctor(list);
				guidoctor.setVisible(true);
			}
		});
		
/*		JButton btnDebug = new JButton("DEBUG");
		btnDebug.setBounds(10, 228, 112, 23);
		contentPane.add(btnDebug);
		btnDebug.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Launching Debug Window");
				GUIDebug guidebug = new GUIDebug(list);
				guidebug.setVisible(true);			}
		});
	*/														//OPTIONAL debug screen for viewing all patient records currently in list	
		
	}
}
