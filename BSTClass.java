import javax.swing.JOptionPane;
/********************************************************************
Written by: Kelly Xavier

ASSIGNMENT:  Write a binary search tree class. 

Your class should have the following:
	- two instance attributes - the root and an instance of TreePainter. 
	- a default constructor that sets root to null, creates an instance
	of TreePainter and sets the location for the TreePainter window
	to 0,0.

Your class should have public methods that do the following:
	adds - takes a Comparable, adds it to the tree and returns nothing.
	delete - takes a Comparable, deletes the first instance it finds  and
	 		returns true (for deleted) or false (for not found) 
	print - prints the pre, in & postorder traversals, LABELED and in
	 		ONE JOP window AND updates the tree in TreePainter.
	 		The TreePainter window should start at 0,0. 
	contains - takes a Comparable and returns true if the value is
			in the tree, false otherwise.
	closeWindow - disposes of the TreePainter window.

Your class should have 6 private methods that do the following:
	the recursive pre, in & postorder traversals
	delete no children, delete 1 child, delete 2 children

Write a BSTRunner file that does the following:
	asks the user to input a string
	creates an instance of your BST class using the characters
		in the input string - it should show up in TreePainter.
	prints a menu in a JOptionPane that lets the user choose to
		add, delete, search, print or exit.  
		Add & delete should ask the user what character to add or
			delete.  The updated tree should show up in the
			TreePainter window.  
		Print should print the pre, in & postorder traversals, labeled
		Contains should print a JOP window with the value and whether
		 	or not the value is stored in the tree.
	Your program should continue until the user chooses exit.

 ********************************************************************/
public class BSTClass<T extends Comparable <T>>
{
	private TreeNode <T> root;
	//private TreePainter //tp;

	public BSTClass()
	{
		root = null;
		////tp = new TreePainter(root);
		//tp.setLocation(0,0);
	}

	public  void exit()
	{
		//tp.dispose();
	}

	public void print()
	{
		JOptionPane.showMessageDialog(null, "Pre order: " 
				+ preOrderTrav(root) + "\nIn order: " 
				+ inOrderTrav(root) + "\nPost order: " 
				+ postOrderTrav(root));
		//tp.setTree(root);
	}

	public void add(T value)
	{
		if(root == null)
		{
			root = new TreeNode<T>(value);
		}
		else
		{
			TreeNode<T> trav = root;
			TreeNode<T> back = root;
			while(trav!=null)
			{
				back = trav;
				if(value.compareTo(trav.getValue())< 0)
				{
					trav = back.getLeft();
				}
				else 
				{
					trav = back.getRight();
				}
			}
			if(value.compareTo(back.getValue())< 0)
			{
				back.setLeft(new TreeNode<T>(value));
			}
			else
			{
				back.setRight(new TreeNode<T>(value));
			}
		}
		//tp.setTree(root);
	}

	public T contains(T value)
	{
		if(root == null)
		{
			return null;
		}
		else
		{
			TreeNode<T> trav = root;
			TreeNode<T> back = root;
			while(trav!=null)
			{
				if(value.equals(trav.getValue()))
				{
					return trav.getValue();
				}
				back = trav;
				if(value.compareTo(trav.getValue())< 0)
				{
					trav = back.getLeft();
				}
				else 
				{
					trav = back.getRight();
				}
			}
			return null;
		}
	}

	public boolean delete(T value)
	{
		if(root == null)
		{
			return false;
		}
		else
		{
			TreeNode<T> trav = root;
			TreeNode<T> back = root;
			while(trav!=null)
			{
				if(value.compareTo(trav.getValue())== 0)
				{
					if(trav.getLeft()==null && trav.getRight()==null)
					{
						deleteNoChildren(trav, back);

					}
					else if((trav.getLeft()==null) 
							|| (trav.getRight()==null))
					{
						deleteOneChild(trav, back);
					}
					else
					{
						deleteTwoChildren(trav, back);
					}
					//tp.setTree(root);
					return true;
				}
				back = trav;
				if(value.compareTo(trav.getValue())< 0)
				{
					trav = back.getLeft();
				}
				else 
				{
					trav = back.getRight();
				}
			}
			return false;
		}
	}

	private String preOrderTrav(TreeNode <T> node)
	{
		String word="";
		if (node != null)
		{
			word+=(node.getValue());
			word+=preOrderTrav(node.getLeft());
			word+=preOrderTrav(node.getRight());
		}
		return word;
	}

	private String inOrderTrav(TreeNode <T> node)
	{
		String word="";
		if (node != null)
		{
			word+=inOrderTrav(node.getLeft());
			word+=(node.getValue());
			word+=inOrderTrav(node.getRight());
		}
		return word;
	}

	private String postOrderTrav(TreeNode <T> node)
	{
		String word="";
		if (node != null)
		{
			word+=postOrderTrav(node.getLeft());
			word+=postOrderTrav(node.getRight());
			word+=(node.getValue());
		}
		return word;
	}
	
	public String printInOrder()
	{
		return inOrderTrav(root);
	}

	private void deleteNoChildren(TreeNode<T> trav, TreeNode<T> back)
	{
		if(back==trav)
		{
			root=null;
		}
		else if(back.getLeft()==trav)
		{
			back.setLeft(null);
		}
		else
		{
			back.setRight(null);
		}
	}
	private void deleteOneChild(TreeNode<T> trav, TreeNode<T> back)
	{
		if(back==trav)
		{
			if(trav.getLeft()!=null)
			{
				root=trav.getLeft();
			}
			else
			{
				root=trav.getRight();
			}
		}
		if(back.getLeft()==trav)
		{
			if(trav.getLeft()!=null)
			{
				back.setLeft(trav.getLeft());
			}
			else
			{
				back.setLeft(trav.getRight());
			}
		}
		else
		{
			if(trav.getLeft()!=null)
			{
				back.setRight(trav.getLeft());
			}
			else
			{

				back.setRight(trav.getRight());
			}
		}
	}

	private void deleteTwoChildren(TreeNode<T> trav, TreeNode<T> back)
	{
		TreeNode<T> trav2 = trav.getRight();
		TreeNode<T> back2 = trav;
		while(trav2.getLeft()!=null)
		{
			back2=trav2;
			trav2=trav2.getLeft();
		}
		trav.setValue(trav2.getValue());
		if(trav==back2)
		{
			if(trav2.getRight()!=null)
			{
				back2.setRight(trav2.getRight());
			}
			else
			{
				back2.setRight(null);				
			}
		}
		else if (trav2.getRight()!=null)
		{
			back2.setLeft(trav2.getRight());
		}
		else
		{
			back2.setLeft(null);
		}
	}
}