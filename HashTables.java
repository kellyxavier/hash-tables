import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/********************************************************************
Written by: Kelly Xavier

Assignment:  

  Create three hash tables of a list of phrases that are stored in 
  a data file. You should ask for the name of the data file.  Your
  hash tables should be stored in ten element arrays. 
  The hash function is the sum of the ASCII values of the 
  characters in the phrase mod 10.
  
  The first table will use linear probing to resolve collisions.  If
  the array is full you should print an "out of memory" message AND 
  the phrase that could not be added.
  
  The second table will use rehashing to resolve collisions.  The 
  rehash function is: the sum of the ASCII values of the first and 
  last character % 10.  If the phrase is only 1 character long just
  use it as both the first and last character. If this spot is also a
  collision start linear probing from the rehash value.  If the array
  is full you should print an "out of memory" message AND the phrase 
  that could not be added.
  
  The third table will use chained hashing to resolve collisions.  
  Each element of the array should be a TreeNode - the root of a
  binary search tree that stores all phrases with that hash code.   
  
  
  PRINT:  
  
  For the first two hash tables simply print the array with the
  element numbers. Don't worry if there were phrases that couldn't 
  be added - you have already printed messages for those.  Make 
  sure you CLEARLY label the element number for each phrase.
  
  0: phrase 2
  1: phrase 9
  ...
  9: phrase 1
  
  For the third hash table print the element numbers vertically and
  each BST using an inorder traversal.  
  
  0:	phrase 1, phrase 5
  1:	empty
  ...
  9:	phrase 2, phrase 3, phrase 4
  
  For all three hash tables print "empty" if the element is empty.
  Each hash table should be clearly labeled (linear probing, rehashing
  and chained hashing) and in a separate JOP window.
  
  
  HAND IN:  Create a jar file, put it in my hand in folder and print.

********************************************************************/
public class HashTables
{
	public static void changeJOP()
	{
		UIManager.put("Label.font", new FontUIResource 
				(new Font("Tempus Sans ITC", Font.BOLD, 20)));
		UIManager.put("OptionPane.messageForeground",Color.green);
		UIManager.put("TextField.background", Color.black);
		UIManager.put("TextField.font", new FontUIResource
				(new Font("Dialog", Font.ITALIC, 24)));
		UIManager.put("TextField.foreground", Color.green);
		UIManager.put("Panel.background",Color.black);
		UIManager.put("OptionPane.background", Color.black);
		UIManager.put("Button.background",new Color(0,0,255));
		UIManager.put("Button.foreground", new Color(255,255,255));
		UIManager.put("Button.font", new FontUIResource	
				(new Font("Tempus Sans ITC", Font.BOLD, 14)));
	}
	
	public static int hash(String phrase)
	{
		int asciiSum =0;
		for(int i =0; i <phrase.length(); i++)
		{
			asciiSum+=(int)phrase.charAt(i);
		}
		return asciiSum%10;
	}
	
	public static String [] linearProbing(String fileName)
	{
		String [] list = new String [10];
		String mem="";
		try
		{ 
			Scanner inFile = new Scanner(new File(fileName));
			while (inFile.hasNext()) 
			{ 
				String phrase=inFile.nextLine();
				int pos = hash(phrase);
				int count=0;
				while(list[pos]!=null&&count<list.length)
				{
					if(pos==list.length-1)
					{	
						pos=0;
						count++;
					}
					else
					{
						pos++;
						count++;
					}
				}
				if(list[pos]==null)
					list[pos]=phrase;
				else
					mem+=(phrase + " ");
			}
			if(mem.length()!=0)
				JOptionPane.showMessageDialog(null, 
						"Out of memory so " + mem + " could not be "
								+ "added to the array with "
								+ "linear probing");
			inFile.close();
		}
		catch (Exception e)
		{ 
			e.printStackTrace();
		}
		return list;
	}
	
	public static String [] reHash(String fileName)
	{
		String [] list = new String [10];
		String mem="";
		try
		{ 
			Scanner inFile = new Scanner(new File(fileName));
			while (inFile.hasNext()) 
			{ 
				String phrase=inFile.nextLine();
				int pos =hash(phrase);
				if(list[pos]!=null)
				{
					int asciiSum =(int) phrase.charAt(0) + 
							(int) phrase.charAt(phrase.length()-1);
					pos= asciiSum%10;
				}
				int count=0;
				while(list[pos]!=null&&count<list.length)
				{
					if(pos==list.length-1)
					{	
						pos=0;
						count++;
					}
					else
					{
						pos++;
						count++;
					}
				}
				if(list[pos]==null)
					list[pos]=phrase;
				else
					mem+=(phrase + " ");
			}
			if(mem.length()!=0)
				JOptionPane.showMessageDialog(null, 
						"Out of memory so " + mem + " could not be "
								+ "added to the array with "
								+ "rehashing");
			inFile.close();
		}
		catch (Exception e)
		{ 
			e.printStackTrace();
		}
		return list;
		
	}
	
	public static BSTClass<String>[] chainedHashing(String fileName)
	{
		BSTClass<String> [] list = new BSTClass [10];
		try
		{ 
			Scanner inFile = new Scanner(new File(fileName));
			while (inFile.hasNext()) 
			{ 
				String phrase=inFile.nextLine();
				int pos =hash(phrase);
				if(list[pos] == null)
					list[pos] = new BSTClass<String>();
				list[pos].add(phrase+" ");
			}
			inFile.close();
		}
		catch (Exception e)
		{ 
			e.printStackTrace();
		}
		return list;
	}
	
	public static void print(String [] lpList, String [] rhList, 
			BSTClass<String>[] chList)
	{
		String output1="";
		String output2="";
		String output3="";
		for(int i=0; i<lpList.length; i++)
		{
			if(lpList[i]!=null)
				output1+=(i+ ": " + lpList[i] + "\n");
			else
				output1+=(i+ ": empty");
		}
		for(int i=0; i<rhList.length; i++)
		{
			if(rhList[i]!=null)
				output2+=(i+ ": " + rhList[i] + "\n");
			else
				output2+=(i+ ": empty");
		}
		for(int i=0; i<chList.length; i++)
		{
			if(chList[i]!=null)
				output3+=(i+ ": " + chList[i].printInOrder() + "\n");
			else
				output3+=(i+ ": empty \n");
		}
		JOptionPane.showMessageDialog(null,
				"Hash Table with Linear Probing \n"+output1 
				+"\n" + "\n");
		JOptionPane.showMessageDialog(null,
				"Hash Table with Rehashing \n"+output2 
				+"\n" + "\n");
		JOptionPane.showMessageDialog(null,
				"Hash Table with Chained Hashing \n"+output3 
				+"\n" + "\n");
	}

	public static void main (String [] args)
	{
		changeJOP();
		String fileName = "data.txt";
		String [] lpList = linearProbing(fileName);
		String [] rhList = reHash(fileName);
		BSTClass<String>[] chList = chainedHashing(fileName);
		print(lpList, rhList, chList);
	}
}