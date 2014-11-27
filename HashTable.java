import java.io.*;

public class HashTable implements Serializable
{
	public StudentRecord [] a;
	
	private int n;
		
	private int N; 
	
	
	public HashTable(int max)          // constructor
	{
		a = new StudentRecord [max];             // create array
		
		n = 0;
		
		N = max; 
		
	}
	
	private int charCode(char c)
	{ 
		int ascii = (int) c ; 
		return ascii; 
	}
	
	private int hornerHash (String s) 
	{
		int hashVal = 0; 
		
		for(int i=0; i< s.length(); i++)
		{
			hashVal = (hashVal*26 + charCode(s.charAt(i)))% N;
		}
		
		return hashVal; 
		
	}
	
	public void insert(StudentRecord r)
	{
		int index = hornerHash(r.getEmail()); 
		
		while (a[index]!=null)			//already filled
		{
			for (int i=1; i<N; i++)		//quadratic probe
			{
				index = (index+(i*i))%N ;
			}
		}
		
		a[index] = r; 				//insert student into hash 
		
		n+=1; 
		
	}
	
	public int find(String s)
	{
		int posIndex = hornerHash(s);
		
		if (a[posIndex]!=null && s.equals(a[posIndex].getEmail()))
		{
			return posIndex;
		}
		else
		{
			for (int i=1; i<N; i++)
			{
				posIndex = ((posIndex+i)%N);
				
				if (a[posIndex]!= null && s==a[posIndex].getEmail())
				{
					return posIndex;
				}
			}
			
			return -1; 			
		}			
		
	}
	
	public static void main(String[] args)
	{
		
		
		HashTable records = new HashTable(100); 
		
		StudentRecord r = new StudentRecord();
		
		StudentRecord r2 = new StudentRecord();

		System.out.println(records.hornerHash("jnapolit@conncoll.edu")); 
		
		System.out.println(records.hornerHash("rnapolit@conncoll.edu")); 
		
		r.setEmail("jnapolit@conncoll.edu"); 
		
		r2.setEmail("rnapolit@conncoll.edu"); 		
		
		records.insert(r);
		
		records.insert(r2);
		
		System.out.println(records.find("jnapolit@conncoll.edu")); 
		
		
		
	}
	
}
