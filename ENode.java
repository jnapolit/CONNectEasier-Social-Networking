import java.util.Calendar;
import java.io.Serializable;

public class ENode implements java.io.Serializable
{
	public Calendar key; 
	
	public String title; 
	
	public OrdArr attendees; 		//emails
	
	public ENode(Calendar k, String t)
	{
		key = k; 
		
		title = t; 
		
		attendees = new OrdArr(100); 			
		
	}
	
	public void display()
	{
		System.out.println("The event " + title + " is at " + (key.get(Calendar.MONTH)+1)+"/"+ key.get(Calendar.DATE)+"/" + key.get(Calendar.YEAR)+" " + key.get(Calendar.HOUR)+":"+ key.get(Calendar.MINUTE)+" "+ "attendes are:" );
		attendees.display();
	}
	
	
	public static void main (String [] args)
	{
		Calendar userCal = Calendar.getInstance();  
		
		userCal.set(2014, 11, 21, 23, 00);
		
		ENode e = new ENode(userCal , "Dying"); 
		
		e. attendees.insert("jnapolit@conncoll.edu"); 
		
		e. attendees.insert("bnapolit@conncoll.edu"); 
		
		e.display();
	}
	
}
