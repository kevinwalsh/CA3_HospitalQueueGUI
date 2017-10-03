package ca3hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database implements IDataStorage{

	Connection c=null;
	Statement stmt=null;
	
	
	@Override
	public void Connect(String connDetails) {
		 try {
		      Class.forName("org.sqlite.JDBC");		
		      c = DriverManager.getConnection("jdbc:sqlite:HospitalDB.db");
		      
		      stmt = c.createStatement();
			String createTable = "CREATE TABLE IF NOT EXISTS PATIENTS" + 
						"(pps VARCHAR (50) PRIMARY KEY," + 					
						"forename VARCHAR (50) ," +			 
						"surname VARCHAR (50) , " + 		
						"address VARCHAR (200) ," +
						"phone VARCHAR (50) , " + 
						"symptoms VARCHAR (200) ," +
						"assessment VARCHAR (200) ," +
						"treatment VARCHAR (200) ," +
						"priority INTEGER ,"+
						"assessmentStage INTEGER"+			//remember, omit comma on last entry!
						")";
				      
				stmt.executeUpdate(createTable);		      
		    } 
		 
		 catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Opened database connection successfully");	
	}
	
	
	@Override
	public void Load(IList list) {
		int entries=0;
		int duplicates=0;
		try{
			stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM PATIENTS;" );
		      while ( rs.next() ) {						//while loop will run once for each entry found in patients table
		         String pps = rs.getString("pps");
		         String  forename = rs.getString("forename");
		         String  surname = rs.getString("surname");
		         String  address = rs.getString("address");
		         String phone = rs.getString("phone");
		         String symptoms = rs.getString("symptoms");
		         String assessment = rs.getString("assessment");
		         String treatment = rs.getString("treatment");
		         int priority  = rs.getInt("priority");
		         int assessmentStage  = rs.getInt("assessmentStage");
			        
		        if(list.checkForPK(pps)==false){		//just realised this is redundant IF we load from DB on startup (since list is empty)
		        										//BUT idea is to ensure an entry is not already in list and mistakenly added twice
		        	Patient p = new Patient(forename,surname,pps,address,phone,symptoms);
		        	p.assessment=assessment;
		        	p.treatment=treatment;
		        	p.setPriority(priority);
		        	p.assessmentStage=assessmentStage;
		        	list.add(p);
		        	}
		    
		        entries++;
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
				System.out.println("load/select function completed successfully");
				System.out.println("Entries found: "+entries+", of which "+duplicates+" were duplicates");   //should be 0 duplicates 
	}

	public int CheckForPK(IData object){
		Patient p = (Patient) object;
		ResultSet rs;
		int rowCount=-1;
		try{
		  Class.forName("org.sqlite.JDBC");			
		   c = DriverManager.getConnection("jdbc:sqlite:HospitalDB.db");
		      
			stmt = c.createStatement();
		       rs = stmt.executeQuery("SELECT COUNT(*) FROM PATIENTS WHERE pps ='"+p.PPS+"'");
		      rs.next();
		      rowCount = rs.getInt(1);				
		      rs.close();
		      stmt.close();
		    } catch(Exception e){
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		
			System.out.println("Rowcount matching pps= "+rowCount);
		    return rowCount;
	}
	
	@Override
	public void SaveSession(IList list){			//save/ update all patient objects to Database upon window close
		Node n = list.getNode(1);
		while(n!=null){
			if(CheckForPK(n.getData())>0){						//check for existing entries
				if(n.getData().getStage()<4){					// if between 1 & 3, patient is still awaiting part of their treatment
				Update(n.getData());
				}
				else{	Delete(n.getData().getPK());	}		// if stage >3, patient is finished & should be removed if still in DB
			}
			else{
				Save(n.getData());				// No records found in DB matching this PK -> new entry	
			}
			n=n.getNext();
		}
	}
	

	@Override
	public void Save(IData object) {		//	for ease, assume we Know/ expect object to be of type Patient.	
		Patient p = (Patient)object;
		try{
			Class.forName("org.sqlite.JDBC");			
		     c = DriverManager.getConnection("jdbc:sqlite:HospitalDB.db");
			    
			
			stmt = c.createStatement();
				String insert = String.format("INSERT INTO PATIENTS " +
						"(pps,forename,surname,address,phone,symptoms,assessment,treatment,priority,assessmentStage)"+
						 "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s',%d,%d)",  p.PPS,p.forename,p.surname,p.address,p.phone,
						 p.symptoms,p.assessment,p.treatment,p.getPriority(),p.getStage());
				stmt.executeUpdate(insert);
		}
		catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
	}
	
	public void Update(IData object){
		Patient p = (Patient)object;
		try{
				stmt = c.createStatement();
				String update = String.format("UPDATE PATIENTS SET " +
						"forename='%s',surname='%s',address='%s',phone='%s',symptoms='%s',"+
						 "assessment='%s',treatment='%s',priority=%d,assessmentStage=%d"+
						" WHERE pps='%s'", p.forename,p.surname,p.address,p.phone,
						 p.symptoms,p.assessment,p.treatment,p.getPriority(),p.getStage(), p.PPS);
						
				stmt.executeUpdate(update);
		}
		catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}


	@Override
	public void Delete(String pk) {		
		try{
			Class.forName("org.sqlite.JDBC");			
		     c = DriverManager.getConnection("jdbc:sqlite:HospitalDB.db");
			
			stmt = c.createStatement();
				String update = String.format("DELETE FROM PATIENTS WHERE pps='%s' ",pk);
				stmt.executeUpdate(update);
		}
		catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}	
}
