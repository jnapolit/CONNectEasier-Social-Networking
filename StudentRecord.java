import java.io.*;
import java.util.Scanner;


public class StudentRecord implements java.io.Serializable
{
	private String name;
	
	private String email;
	
	private String password; 
	
	private DLL events; 		
	
	private OrdArr friends; 
	
	private DLL timeline; 
	
	private String classyear; 
	
	
	public StudentRecord( )
	{
		name = "";
		email = ""; 
		password = ""; 
		events = new DLL(); 
		friends = new OrdArr(100);  
		timeline= new DLL(); 
		classyear= ""; 
		
	}
	
/**
*All the methods to access and change the above fields, excluding the array fields 
*/
	
	public String getName()
	{
		return name; 
	}
	
	public void setName(String s)
	{
		name = s; 
	}
	
	public String getEmail()
	{
		return email; 
	}
	
	public void setEmail(String s)
	{
		email = s; 
	}
	
	public String getPassword()
	{
		return password; 
	}
	
	public void setPassword(String s)
	{
		password = s; 
	}
	
	public String getClassyear()
	{
		return classyear; 
	}
	
	public void setClassyear(String s)
	{
		classyear = s; 
	}
	
	public DLL getTimeline()
	{
		return timeline; 
	}
	
	public void setTimeline(DLL l)
	{
		timeline = l; 
	}
	
	public DLL getEvents()
	{
		return events; 
	}
	
	public void setEvents(DLL l)
	{
		events = l; 
	}
	
	public OrdArr getFriends()
	{
		return friends; 
	}
	
	public void setFriends(OrdArr a)
	{
		friends = a; 
	}
	
	
	
	/*
	public void insertFile ()
	{
		HashTable table = new HashTable(10); 
		//this try-catch statement is needed around this file input code
		//because the FileInputStream may throw a FileNotFoundException
		try {
			Scanner lineScanner = new Scanner(new FileInputStream("userdata.txt"));
			
			while (lineScanner.hasNext()) { //while more of the input file is still available for reading
				
				String name = lineScanner.nextLine();  //reads an entire line of input				
				setName(name);				
				
				String email = lineScanner.nextLine();
				setEmail(email); 
				
				String pass = lineScanner.nextLine();
				setPassword(pass); 				
				
				String year = lineScanner.nextLine();
				setClassyear(year);
				
				String status = lineScanner.nextLine();				
				
				String events = lineScanner.nextLine(); //read the entire line of event data
				//now create a secondary scanner to actually scan through this list of events
				// to break them up into individual events
				Scanner eventsScanner = new Scanner(events);
				DLL eventsList = new DLL(); //will store the individual events for now
				//on this line of data, events are in quotes and delimited by commas, 
				// so we tell the scanner to look for a quotation mark followed by a comma (",)
				// to delimit each event
				eventsScanner.useDelimiter("\","); //need the backslash in front of special characters like "
				String e; //will hold each individual event
				while (eventsScanner.hasNext()){
					e = eventsScanner.next();
					e = e.substring(1, e.length()); //cut off the leading quotation mark of each event
					eventsList.add(e);
				}	
				setEvents(eventsList);
				
		
				String wallMsgs = lineScanner.nextLine();
				Scanner wallMsgScanner = new Scanner(wallMsgs);
				DLL wallMsg = new DLL();
				wallMsgScanner.useDelimiter("\",");
				String message; 
				while (wallMsgScanner.hasNext()) {
					message = wallMsgScanner.next();
					message = message.substring(1, message.length());
					wallMsg.add(message); 
				}
				setTimeline(wallMsg);
				
				
				
				String friends = lineScanner.nextLine();
				Scanner friendScanner = new Scanner(friends);
				OrdArr friendArray = new OrdArr(250); 
				friendScanner.useDelimiter(",");  
				String friend;
				while (friendScanner.hasNext()) {
					friend = friendScanner.next();
					friendArray.insert(friend); 
				}
				setFriends(friendArray);
			
			table.insert(new StudentRecord()); 			
			}//end while
			
			
		System.out.println(table.find("lhigdon@conncoll.edu"));
	
		} catch(FileNotFoundException ex) {
			System.out.println("File not Found");
			System.exit(0);
		}
	
	}*/
	
	public static void main(String args[])
	{
		StudentRecord r = new StudentRecord(); 
		
		//r.insertFile();
	}
	
	
}
