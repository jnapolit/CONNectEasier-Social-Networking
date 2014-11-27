import java.io.*;
import java.util.Scanner;
import java.util.Calendar;

public class EventBookApp
{	
	public static void main(String args[])
	{
		EventBook fb = new EventBook();
		
		fb.loadHash(); 
		fb.loadEvents(); 
		fb.updateEvents(); 
		
		boolean isExit = false; 
		String choice;
		boolean LogIn; 
		String command; 
		
		while (isExit==false)
		{
			choice = fb.loginScreen();
			LogIn = false; 
			
			while(LogIn ==false)
			{
				switch(choice) 
				{
					case "a":					
						LogIn= fb.login(); 
					
						if (LogIn == true) 
						{
							break;
						}
						else
						{
							choice = fb.loginScreen();				
							break;
						}
					
					case "b": 
						fb.newUser();
						LogIn = true; 
						break; 
				
					case "c":
						isExit = true; 
						LogIn=true; 
						break; 
			
				} //end switch
				
			}//end while mainscreen
			
			if (isExit ==true)
			{
				System.out.println("Goodbye! Thank you for choosing our application"); 
			}
			else
			{
				
				fb.homeScreen(); 
				command= fb.homeScreenMenu(); 	
				
				while(!(choice.equalsIgnoreCase("e")))
				{
					switch(command) 
					{
						case "a":					
							fb.timelinePost();  
							break; 
				
						case "b": 
							fb.addEvent(); 
							break; 
					
						case "c":
							fb.viewFriends(); 						
							break; 
					
						case "d":
							fb.editFriends(); 
							break; 
			
					} //end switch
				
					command = fb.homeScreenMenu(); 	
				
				}//end while for home screen
				
			}//end of else
			
		}//end isExit while 	
			
		fb.saveHash(); 
		fb.saveEvents();
			 
	
	}//end main		

} //end class
