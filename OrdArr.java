import java.io.Serializable;
/** 
* A class that allows access and manipulation of an ordered array of numbers, 
* providing a high-level interface for common array operations.
* Filename: OrdArray.java (adapted from Robert Lafore's Data Structures text)
*/



public class OrdArr implements java.io.Serializable
{

	public String [] a;		// private instance variable for array a
	
	private int nElems;		// private instance variable for number of data items
	
	
////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Construct a new array of longs along with 
	 * an integer variable to track the number of elements in use
	 * @param max
	 *		specifies the size of the array
	 */
	
	public OrdArr(int max)          // constructor
	{
		a = new String[max];             // create array
		
		nElems = 0;
		
	}

	
/////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Accessor method for nElems
	 * @return 
	 *		returns the number of array elements in use
	 */ 
	
	public int size() 
	{
		
		return nElems; 
		
	}

	
//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Finds the given value in the array
	 * @param searchKey
	 * 		the value to be searched for
	 * @return
	 *		the index where the searchKey is found, 
	 *		or the value of nElems if not found
	 */
	
	public int find(String searchKey) 
	{	
		
		int low= 0;		//set the low to be at index zero
		
		int high= nElems-1;		//set the high to be the last index in the array
		
		while (low <= high)		//while the low is less than or equal to the high index (there is no overlap)
		{
			int mid= ( low + high ) /  2;		//set the mid to be the midpoint between the high and low (does integer division)
			
			if ( a[mid] == searchKey )		//if  the number we are looking for is equal to our midpoint.
										//eventually should come into this condition because the low and high numbers will close in, unless that searchKey is not in our array
			{
				
				return mid; 		// give the position of our midpoint
				
			}
			
			else if ( (searchKey.compareTo(a[mid])) <0 )		//else if the inputted name is alphabetically before the mid
			{
				
				high= mid-1; 		//reset our range and change our high number to be just below the midpoint
				
			}
			
			else 		//if the number we are looking for is greater than the midpoint number 
			{
				
				low= mid+1; 		//reset our range and change our low number to be just above the midpoint
				
			}			
		
		}
		
		return nElems;             // thus, can't find it
		
	} // end find()
	
	

/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/**
	 * Inserts a new value into array a[]
	 * (Assumes the array is long enough to handle all items that will be inserted.)
	 * @param value
	 *		the value to be inserted
	 */
	
	public void insert(String value)  // put element into array
	{
		
		int lo = 0; 		//set out low to be the first index in the array
		
		int hi = nElems -1; 		//set our high to be the last index nElems
		
		int mid = 0; 		//set our midpoint to be zero incase the array is empty 
		
		int spot=0; 		//set the spot that our value will go to zero 
		
		boolean FoundIt = false; 		//set FoundIt to false. This variable will allow us to break out of the while statement without using a break
		
		if (nElems==0 || (a[0].compareTo(value))>0)		//if the inputted name is alphabetically before the first name in the array or our array is empty 
		{
			
			spot = 0; 		//the value should go in the zeroth index
			
			
		}
		
		else if ((value.compareTo(a[nElems-1]))>0 )		//else if our value alphabetically after the last item in the array
		{
			
			spot = nElems; 			//the value should go in the spot just after the last item in nElems
			
		}
		
		else
		{
			
			while (!FoundIt && lo <= hi) 		//while we have not found the spot and our lo is less than or equal to our high (there is no overlap)
			{
				
				mid = ( lo + hi ) / 2; 		//set mid to be the midpoint between our lo and hi 
				
				
				if ( (a[mid].compareTo(value))>=0 &&  (value.compareTo(a[mid-1]))>0)		// if our mid value is greater than or equal to the value we are inserting and 
																			//the value is greater than the number directly to the left of the mid.
																			//If it made it into this while loop, in order for us to find the correct position 
																			//and, get out of this loop,  we will have to  get into this if statement  and 
																			//the lo and high should overlap 
				{
					
					spot = mid; 		//the value should go in the index of mid 
					
					FoundIt = true;		//set FoundIt to true because we found the correct spot for our inserted value to go
					
				}
				
				else if ( (value.compareTo(a[mid]))<0)		//if the value we are trying to insert is less than our mid value 
				{
					
					hi = mid -1; 		//reset our range and set the hi to be just below the mid
					
				}
				
				else if ( (value.compareTo(a[mid])) > 0)	//if the value we are trying to insert is greater than our mid value
				{
					
					lo = mid+1; 		//reset our range and set the lo to be just above the mid
					
				}
				
			}
			
		}
		
		int j =  nElems -1  ; 		//declare and initialize j to be the last index of the last element in nElems
			
		
		while (j>=0 && j >= spot) 		//while j is greater than or equal to zero and it is greater than or equal to the spot
		{
			
			a[j+1] = a [j]; 		//move the item in the j-th position to the next position 
			
			j = j-1; 			//decrement j
			
		}
		
		a[spot]= value;		//put our value into the correct spot in our array a
		
		nElems ++; 		//incremenet the amount of elements we have 
		
		
			
		
	}  // end insert()
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Deletes the specified value
	 * @param value 
	 * 		The value to be deleted
	 * @return 
	 * 		true if search and deletion was successful, false otherwise
	 */
	
	public boolean delete(String value)
	
	{
		int j = 0;		// initialize and declare j
		
		int position = find(value); 		//find the index the 'value' has, if it has any
		
		if(a[position] == value)  // found it
		{
			
			for(int k=position; k<nElems; k++)  // move bigger ones down
			{
				a[k] = a[k+1];
				
			}    
			
			nElems--;                   // decrement size
			
			return true;
			
		} //end if
		
		else  // value not found in the array
		{
			return false;
			
		}
		
	}  // end delete()
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Displays array contents
	 */
	
	public void display()             // displays array contents
	{
		
		for(int j=0; j<nElems; j++)       // for each element,
		{
			System.out.print(a[j] );  // display it
		
			System.out.println("");
		}

	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void main(String[] args)
	{
		OrdArr friends = new OrdArr(100); 
		
		friends.insert("jnapolit@conncoll.edu");
		
		friends.insert("rnapolit@conncoll.edu");
		
		friends.insert("kcull@conncoll.edu");	

		friends.insert("jacon@conncoll.edu");
		
		friends.display();
	}
	
}
	
