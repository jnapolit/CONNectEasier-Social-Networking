//Jessica Napolitano

// ASSUMES THE ARRAY IS STORING NODES AND IT IS CALLED a. ALSO ASSUMES THIS CLASS WILL HAVE A FIELD THAT KEEPS TRACK OF
//THE SIZE OF THE ARRAY(HOW MANY NODES ARE IN IT) NOT TO BE CONFUSED WITH THE CAPACITY 

import java.util.Calendar;
import java.util.Scanner;

public class PriorQue
{
	private int n; 
	
	private int N; 
	
	private ENode [] a; 
	
	public PriorQue(int max)
	{
		n = 0; 
		N = max;  
		a = new ENode [max]; 
	}

	/**
	*@param	i
	*		position of parent node 
	*@return
	*		the position of the the parent's left kid
	*/

	public int leftChild(int pos)		//The user inputs the position in the array of the parent
	{
		return pos*2;			//And the method returns the index of the left child. The left child's
							//index is twice the amount of the parent's due to the fact that we started
							//labeling with the root as one and each node must have at most two children. 
							//One can also think about the fact that each level goes up by a a power of two 
							//So the first row is 2^0= 1, then 2^1=2, then 2^2=4, then 2^3=8 and so on. You can
							//see that each of these will be the left child of the parent node. so the left child of the root 
							//is at 2 and their left child will be at 4 and theirs at 8. This contributes to the fact that the height 
							//is log base 2
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	*@param	i
	*		position of parent node 
	*@return
	*		the position of the the parent's right kid
	*/

	public int rightChild(int i)		//Again this has the same logic as leftchild however we must add one to get the right 
							//child. This makes sense becasue the right child will be right next to the left child
							//but over one. 
	{
		return(2*i+1);
	}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public int parent (int i)
	{
		int result = i/2; 
		return result; 
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	*@param	i
	*		position of two array spots you want to swap
	*swaps two items in the array
	*/

	public void swap (int i, int j)		//This method swaps the values in two positions (i,j)
								//It does this by initializing and declaring a temp value to equal whatever is held 
								//in the position of a[j]. This allows us to reset the value in this position without
								//losing it's old value. We then assign this old value to the spot we are swapping with.
	{
		ENode temp = a[j]; 
		a[j]= a[i];
		a[i]=temp;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	*@param  p
	*		index of spot in array which you want to check
	*@return 
	*		boolean of whether p has a legitamate smaller child 
	*CAUTION: assumes there is a field named n which is the size of the array. 
	*It is the size not the capacity. Also uses the .key assuming that the array is storing nodes
	*/ 
	
	public boolean hasSmallerChild(int p)
	{
		if (leftChild(p) <= n && a[leftChild(p)].key.getTimeInMillis() < a[p].key.getTimeInMillis())
		{
			return true; 
		}
		
		else if (rightChild(p)<=n && a[rightChild(p)].key.getTimeInMillis() < a[p].key.getTimeInMillis())
		{
			return true; 
		}
		
		else
		{
			return false; 
		}
	}	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	*@return
	*		the min which was deleted
	*CAUTION: this method requires a field which keeps track of the number of elements in the array 
	*Here that field is called n. It is the size not the capacity of the array 
	*/

	public ENode extractMin()
	{
		if(n!=0)
		{
			swap(1,n);				//we call the swap method here to swap the first item(the min) with the last item in the array 
			n=n-1;					//we then decrease the size of our elements to be one less. The min will still be in that same position 
								//however if we need that spot in the furture we will just write over it. For now we will just think of it
								//as garabage in there 
			int p=1; 
			while (hasSmallerChild(p))		//then we have to bump the value we just swapped with the min down to it's proper place
			{							//if it is bigger than it's children (which is something we cannot have in a heap by definition)
				if (rightChild(p) > n)
				{
					swap(p,leftChild(p)); 
					p = leftChild(p); 
				}
			
				else
				{

					if (a[leftChild(p)].key.getTimeInMillis()<a[rightChild(p)].key.getTimeInMillis())						//then if the leftchild is smaller
					{
						swap(p,leftChild(p));							//swap it with that one
						p = leftChild(p);
					}	
					else	//right child is less than left
					{	
						swap(p,rightChild(p));						//and if the right child is smaller swap it with that one. Basically just swap it with  whichever child is 
														//smaller. This will ensure that it will never be bigger than either of it's children 
						p = rightChild(p); 
					}
				}
			}
	
			return a[n+1]; 			//finally we can just return a[n+1] because the min is still in the spot where we swapped it. We had to say n+1 because previously we had
									//decreased the elements so that the spot the min was filling can be overwritten in the future since it is technically 'deleted' 
		}
		
		else
		{
			System.out.println("There is nothing in this heap!!!"); 
			return null; 
		}
		
	}
	

	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void insert(ENode i)
	{
		
		if ((n+1)<=N)
		{
			
			a[n+1] = i; 
		
			n = n+1; 
		
			int child = n; 
		
			while (child>1 && a[parent(child)].key.getTimeInMillis() > a[child].key.getTimeInMillis())
			{
				swap(child, parent(child)); 
				child = parent(child); 
			
			}
		}
		
		else
		{
			System.out.println("There is no room to insert that event into the heap"); 
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Displays array contents
	 */
	
	public void display()             // displays array contents
	{
		
		for(int j=1; j<(n+1); j++)       // for each element,
		{
			a[j].display();  // display it
		
			System.out.println("");
		}
		
		
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	public ENode find(String e)
	{
		for (int i = 1; i <= n; i++) 
		{
			if (a[i].title.equals(e))
			{
				return a[i];
			} //end if 
			
		} //end for
		
		return null;
		
	} //end fin
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public ENode getMin() 
	{
		if (n!=0)
		{
			return a[1];
		}
		
		else
		{
			return null;
		}
	}
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) 
	{
		PriorQue q = new PriorQue(10); 
		
		Calendar userCal = Calendar.getInstance();  
		
		Calendar userCal2 = Calendar.getInstance();  
		
		Calendar userCal3 = Calendar.getInstance(); 
		
		Calendar userCal4 = Calendar.getInstance(); 
		
		Calendar userCal5 = Calendar.getInstance(); 
		
		userCal.set(2014, 11, 21, 23, 00);
		
		userCal2.set(2013, 10, 20, 22, 99);
		
		userCal3.set(2011, 9, 20, 22, 99);
		
		userCal4.set(2011, 8, 20, 22, 99);
		
		userCal5.set(2011, 8, 20, 22, 30);
		
		q.insert(new ENode(userCal, "Dying in CompSci")); 
		
		q.insert(new ENode(userCal2, "alive")); 
		
		q.insert(new ENode(userCal3, "throwing computer")); 
		
		q.insert(new ENode(userCal4, "biting people")); 
		
		q.insert(new ENode(userCal5, "hiting")); 
		
		q.display();
		
		System.out.println("--------------------------"); 
		
		q.getMin().display();
		
		System.out.println("--------------------------"); 
		
		q.extractMin().display();
		
		System.out.println("--------------------------"); 
		
		q.display();
		 
		System.out.println("--------------------------"); 
		
		System.out.println(q.find("alive")); 
		
		
		
	}
		
		
		

}
