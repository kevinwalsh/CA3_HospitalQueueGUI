
package ca3hospital;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

	public static void main(String[] args) {		
		createguis();
	}

	private static void createguis(){
		
		DoubleLinkedList mylist = new DoubleLinkedList();			//double-linked list conforms to IList interface
	//	addsampledata(mylist);
		
		IDataStorage db= new Database();
		db.Connect("CA3_Hospital");
		db.Load(mylist);
		
		
		GUIMain mygui= new GUIMain(mylist);
		mygui.addWindowListener(new WindowAdapter() {				
		    public void windowClosing(WindowEvent e) {		
		    	System.out.println("DETECTED: Window is closing!");

		    	if(db!=null){
			    	db.SaveSession(mylist);
				    	}
		    }
		});
		mygui.setVisible(true);
	}
	
	private static void addsampledata(DoubleLinkedList mylist){
		mylist.add(new Patient ("A","Doe","pps12345","1 main st","12345","headache, blocked nose"));
		mylist.add(new Patient ("B","Doe","pps12345","1 main st","12345","headache, blocked nose"));
		mylist.add(new Patient ("C","Doe","pps12345","1 main st","12345","headache, blocked nose"));
		mylist.add(new Patient ("D","Doe","pps12345","1 main st","12345","headache, blocked nose"));
		mylist.add(new Patient ("E","Doe","pps12345","1 main st","12345","headache, blocked nose"));

	}
	
	private static void addDatabase(DoubleLinkedList mylist){
		
	}
	
	
}
