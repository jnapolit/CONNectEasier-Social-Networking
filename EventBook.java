import java.io.*;
import java.util.Scanner;
import java.util.Calendar;


public class EventBook 
{
	public HashTable accounts;
	public PriorQue events;
	public StudentRecord user;
	
	public EventBook()
	{
		accounts = new HashTable(100);
		events = new PriorQue(100);
		user = new StudentRecord();
		
	} //end constructor
	
	public String loginScreen()
	{
		System.out.println("Welcome to the Conn Coll Event Book.");
		System.out.println("Please choose an option:");
		System.out.println("  a. login to an existing account");
		System.out.println("  b. create a new account");
		System.out.println("  c. quit");
		Scanner s = new Scanner(System.in);
		String choice = s.next();
		while (! (choice.equalsIgnoreCase("a") || choice.equalsIgnoreCase("b") || choice.equalsIgnoreCase("c")))
		{
			System.out.println("That was not one of your choices, please enter a valid menu choice");
			choice = s.next();
		}
		return choice; 
	} //end loginScreen
	
	
	public void newUser()
	{
		Scanner s = new Scanner(System.in) ;
		System.out.println("Please enter your name: ");
		String name = s.next();
		System.out.println("Please enter your email: ");
		String email = s.next();
		while ( !checkEmail(email) ) 
		{
			System.out.println("Email is already in use. Please enter another email: ");
			email = s.next();
		}
		System.out.println("Please enter your password: ");
		String password = s.next(); 	//FEATURES: password check maybe
		System.out.println("Please enter your class year: ");
		String year = s.next();
		
		StudentRecord newUser = new StudentRecord();
		newUser.setName(name);
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setClassyear(year);
		
		accounts.insert(newUser);
	} //end newUser()
	
	
	public boolean login()
	{
		Scanner s = new Scanner(System.in) ;
		System.out.println("Please enter your username (your email): ");
		String email = s.next();
		System.out.println("Please enter your password: "); 
		String password = s.next(); 		
		
		int i = accounts.find(email);
		
		if (!(i>=0 && password.equals(accounts.a[i].getPassword())))
		{
			System.out.println(" "); 
			System.out.println("Could not find user and password combination.");
			return false; 
		}
		else
		{
			user = accounts.a[i];
			System.out.println("Log in was successful"); 
			return true; 
		}
			 
	} //end login
	
	
	private boolean checkEmail(String email) 
	{
		int i = accounts.find(email);
		if (i != -1)
		{
			return false;
		}
		else //email not in use
		{
			return true;
		}
	} //end checkEmail
	
	
	public void homeScreen()
	{
		System.out.println("Welcome to the Conn Coll Event Book.");
		if (events.getMin()==null)
		{
			System.out.println("There are no upcoming events"); 
		}
		else
		{
			System.out.println(events.getMin());
		}
		user.getTimeline().display();
		user.getEvents().display();
	} //end homeScreen
	
	
	public String homeScreenMenu()
	{

		System.out.println("Please choose an option:");
		System.out.println("  a. post to timeline");
		System.out.println("  b. add an event"); 			//think about removing later
		System.out.println("  c. view friends");
		System.out.println("  d. add or remove friends");
		System.out.println("  e. logout");
		
		Scanner s = new Scanner(System.in);
		String choice = s.next();
		
		while (! (choice.equalsIgnoreCase("a") || choice.equalsIgnoreCase("b") || choice.equalsIgnoreCase("c") || choice.equalsIgnoreCase("d") || choice.equalsIgnoreCase("e")))
		{
			System.out.println("That was not one of your choices, please enter a valid menu choice");
			choice = s.next();
		}
		
		return choice;
	} //end homeScreen
	
	
	public void addEvent()
	{
		Calendar newEventTime = Calendar.getInstance();
		Scanner s = new Scanner(System.in);
		
		System.out.println("What is the name of your event? ");
		String name = s.next();
		
		ENode oldEvent = events.find(name);
		
		String choice = "a";
		
		while (oldEvent != null)
		{
			System.out.println("An event by this name already exists on the following date: " + oldEvent.key.toString());
			System.out.println("Would you like to join the existing event or rename the new one? ");
			System.out.println("  a. rename the new event");
			System.out.println("  b. join the existing one");
			
			choice = s.next();
			
			if (choice.equals("b"))
			{
				break;
			}
			oldEvent = events.find(name);
			
		}
		
		if (choice.equals("b"))
		{
			//add event to user list
			user.getEvents().add(oldEvent.title + oldEvent.key.toString());
			oldEvent.attendees.insert(user.getEmail());
			//add user to event attendee list
		} //end if
		else //creating a new one
		{
			Calendar c = Calendar.getInstance();
			
			System.out.println("What is the year YYYY of your new event? ");
			int year = s.nextInt();		
			while ( !(2013 < year && year< 10000) )
			{
				System.out.println("Invalid year. Please enter a valid year in YYYY format: ");
				year = s.nextInt();
			}
			
			System.out.println("What is the month MM of your new event? ");
			int month = s.nextInt();
			while ( !(0 < month && month<13 ) )
			{
				System.out.println("Invalid month. Please enter a valid month in MM format: ");
				month = s.nextInt();
			}
			
			System.out.println("What is the day DD of your new event? ");
			int day = s.nextInt();
			while ( !(0 < day && day<32 ) )
			{
				System.out.println("Invalid day Please enter a valid day in DD format: ");
				day = s.nextInt();
			}
			
			System.out.println("What is the hour (0-23) of your new event? ");
			int hour = s.nextInt();
			while ( !(-1 < hour && hour<24 ) )
			{
				System.out.println("Invalid hour. Please enter a valid hour between 0-23: ");
				hour = s.nextInt();
			}
			
			System.out.println("What is the minute (0-59) of your new event? ");
			int minute = s.nextInt();
			while ( !(-1 < minute && minute<60 ) )
			{
				System.out.println("Invalid minute. Please enter a valid minute between 0-59: ");
				minute = s.nextInt();
			}
			
			System.out.println("\nYou entered: " + month + "/" + day + "/" + year + " at " + hour + ":" + minute);
			//add to master heap of events
			
			c.set(year, month-1, day, hour, minute); 
			System.out.println("The calendar object been set to: " + c.getTime()); 
			ENode newEvent =  new ENode(c, name);
			newEvent.attendees.insert(user.getEmail());	
			newEvent.display(); 
			events.insert(newEvent);
			user.getEvents().add(name + " " + newEventTime.toString());
			
			events.display(); 
			
			
		} //end else

	} //end addEvent
	
	
	public void updateEvents()
	{
		Calendar now = Calendar.getInstance();
		while (events.getMin()!=null && now.getTimeInMillis() > events.getMin().key.getTimeInMillis())
		{
			ENode event = events.extractMin();
			String eventName = event.title;
			for (int i = 0; i < event.attendees.size(); i ++)
			{
				String student = event.attendees.a[i];
				int j = accounts.find(student);
				int k = accounts.a[j].getEvents().find(event.title);
				accounts.a[j].getEvents().remove(k);
			} // end for
		} //end while
	} //end updateEvents
	
	public void timelinePost()
	{
		Scanner s = new Scanner (System.in);
		System.out.println("What would you like to post on your timeline? ");
		String post = s.next();
		user.getTimeline().add(post);
		
	} //end timelinePost

	public void viewFriends()
	{
		OrdArr friends = user.getFriends();
		for (int i = 0; i < friends.size(); i++) 
		{
			int j = accounts.find(friends.a[i]);
			System.out.println(accounts.a[j].getName());
			System.out.println(accounts.a[j].getEmail());
			System.out.println(accounts.a[j].getTimeline().getLast()); 
			System.out.println("");
		} //end for
		System.out.println("Please pick a friend to focus on:");
		Scanner s = new Scanner(System.in);
		String friend = s.next();
		while ( !exists(friend) )
		{
			System.out.println("Person does not exist. Please pick a different friend: ");
			friend = s.next();
		} //end while
		System.out.println("Please choose an option:");
		System.out.println("  a. post to friend's timeline");
		System.out.println("  b. see friend's events");
		String choice = s.next();
		int k = accounts.find(friend);
		switch(choice)
		{
			case "a":
				System.out.println("What do you want to post to their timeline? /n");
				String post = s.next();
				String finalPost = user.getName() + ": " + post;
				accounts.a[k].getTimeline().add(finalPost);
				break;
			case "b":
				accounts.a[k].getEvents().display();
				break;
		} //end switch
		//create a case for no friends

	} //end viewFriends
	
	public void editFriends()
	{
		System.out.println("a. add a friend");
		System.out.println("b. remove a friend");
		
		Scanner s = new Scanner(System.in);
		String choice = s.next();
		switch(choice) 
		{
			case "a":
				System.out.println("Please enter the email address of a friend to add: ");
				String friend = s.next();
				while (!exists(friend))
				{
					System.out.println("Person does not exist. Choose another email: ");
					friend = s.next();
				}
				user.getFriends().insert(friend);
				int i = accounts.find(friend);
				accounts.a[i].getFriends().insert(user.getEmail());
				break;
			case "b": 
				System.out.println("Please enter the email address of a friend to remove: ");
				friend = s.next();
				while (!exists(friend) && user.getFriends().find(friend) == user.getFriends().size())
				{
					System.out.println("Person does not exist or is not your friend. Choose another email: ");
					friend = s.next();
				}
				user.getFriends().delete(friend);
				int l = accounts.find(friend);
				accounts.a[l].getFriends().delete(user.getEmail());
				break;
		} //end switch
					
	} //end editFriends
	
	private boolean exists(String email)
	{
		if (accounts.find(email) != -1) 
		{
			return true;
		}
		return false;
	} //end exists
	
	public void saveHash() 
	{
		String filename = "hashtable.ser";
		FileOutputStream fos = null; 
		ObjectOutputStream out = null; 
		try 
		{
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(accounts);
			out.close();
			fos.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		}		
	}
	
	public void loadHash()
	{
		String filename = "hashtable.ser";
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try 
		{
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			accounts = (HashTable) in.readObject();
			in.close();
			fis.close();
		}
		catch (Exception ex) 
		{
			System.out.println(ex);
			accounts = new HashTable(100);
		}
	}
	
	public void saveEvents() 
	{
		String filename = "priorque.ser";
		FileOutputStream fos = null; 
		ObjectOutputStream out = null; 
		try 
		{
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(events);
			out.close();
			fos.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		}		
	}
	
	public void loadEvents()
	{
		String filename = "priorque.ser";
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try 
		{
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			events = (PriorQue) in.readObject();
			in.close();
			fis.close();
		}
		catch (Exception ex) 
		{
			System.out.println(ex);
			events= new PriorQue(100);
		}
	}
	
	
	public static void main (String [] args)
	{
		HashTable a = new HashTable(100); 
		PriorQue q = new PriorQue(100);
		EventBook e = new EventBook();
	
		
		System.out.println(e.loginScreen());
		//e.newUser(); 
		//System.out.println(e.login("jnapolit@conncoll.edu", "Camel_2017"));
		//e.homeScreen(); 
		//String choice = e.homeScreenMenu(); 
		//System.out.println(choice);
		//e.addEvent(); 
	}
} //end class
	
	
	
	
	
	
	
