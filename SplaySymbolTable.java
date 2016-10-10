import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Vector;

/* Anthony Knight
 * 10/5/16
 * CS 223
 */

public class SplaySymbolTable<K extends Comparable<K>,V> implements SymbolTable<K,V> {
	
	//Global Variables
	private Node root;	
	//testing variables
	public int rotations = 0;
	public int comparisons = 0;
	
	
	// Node Class
	public class Node {
		public K key;
		public V val;
		public Node left, right;
		public Node parent; // null for root
		public Node(K k, V v) {
			key = k;
			val = v;
			left = null;
			right = null;
			parent = null;
		}
	}
	
	//Create Splay Symbol Table
	public SplaySymbolTable () {root=null;}

	
	//Private helper method for right rotation
	private void rotateRight(Node tree){		
		
		// set child links
		Node root = tree.left;
        tree.left = root.right;
        root.right = tree;
        
        //set parent links
        root.parent = root.right.parent;
        root.right.parent = root;
        if(root.right.left != null){
        	root.right.left.parent = root.right; 
        }
        //set's child pointer for child's parent
        if(root.parent != null){
	        if(root.key.compareTo(root.parent.key)>0){
	        	root.parent.right = root;
	        } else {root.parent.left=root;}
        }
        //set new root pointer
        if (root.parent == null){
        	this.root = root;
        }
        //increment rotation counter
        rotations++;
    }
	
	private void rotateLeft(Node tree){
        // set child links
		Node root = tree.right;
        tree.right = root.left;
        root.left = tree;
        
        //set parent links
        root.parent = root.left.parent;
        root.left.parent = root;
        if(root.left.right != null){
        	 root.left.right.parent = root.left;
        }
        //set child pointer for child's parent
        if(root.parent != null){
	        if(root.key.compareTo(root.parent.key)>0){
	        	root.parent.right = root;
	        } else {root.parent.left=root;}
        }
        //set new root pointer
        if (root.parent == null){
        	this.root = root;
        }
        //increment rotation counter
        rotations++;
    }

	//Splay method
	private void splay(Node x){
		while(x.parent != null && x.parent.parent != null){
			//zag zag case
			if (x == x.parent.right && x.parent == x.parent.parent.right){
				rotateLeft(x.parent.parent);
				rotateLeft(x.parent);
				comparisons++;
			}
			//zig zig case
			else if (x == x.parent.left && x.parent == x.parent.parent.left){
				rotateRight(x.parent.parent);
				rotateRight(x.parent);
				comparisons++;
			}
			
			//zag zig case
			else if (x == x.parent.left && x.parent == x.parent.parent.right){
				rotateRight(x.parent);
				rotateLeft(x.parent);
				comparisons++;
			}
			
			//zig zag case
			else if (x == x.parent.right && x.parent == x.parent.parent.left){
				rotateLeft(x.parent);
				rotateRight(x.parent);
				comparisons++;
			}
		}
		// If tree height is odd number this finishes the splay
		if (x != root){
			if (x == x.parent.right){
				comparisons++;
				if(x.parent.left == null){ //edge case for if only two elements
					x.left = x.parent;
					x.left.right = null;
					x.left.parent = x;
					x.parent = null;
					root = x;
					comparisons++;
				}
				else{rotateLeft(root);}
			}
			else if (x == x.parent.left){
				comparisons++;
				if (x.parent.right == null){ //edge case for if only two elements
					x.right = x.parent;
					x.right.left = null;
					x.right.parent = x;
					x.parent = null;
					root = x;
					comparisons++;
				}
				else{rotateRight(root);}
			}
		}
	}
	//Iterative insert function for splay tree
	public void insert(K key, V val) {
		if (root == null){
			root = new Node(key,val);
			root.parent = null;
		}
		
		Node x = root;
		Node parent = null;
		Boolean finished = false;
		while (finished != true){
			if(x == null){
				comparisons++;
				x = new Node(key, val);
				x.parent = parent;
				x.parent.right = x;
				splay(x);
				finished = true;
			}
			int i = key.compareTo(x.key);
			if (i < 0){
				parent = x;
				parent.left = x.left;
				x = x.left;
				comparisons++;
				
			}
			else if (i > 0){
				parent = x;
				parent.right = x.right;
				x = x.right;
				comparisons++;
			}
			else if (i == 0){
				splay(x);
				finished = true;
				comparisons++;
			}
			else {
				x = new Node(key, val);
				splay(x);
				finished = true;
				comparisons++;
			}
		}
	}
	// Iterative search function for splay tree
	public V search(K key) {
		Node x = root;
		while (x!=null){
			int i = key.compareTo(x.key);
			if (i < 0){
				x = x.left;
				comparisons++;
			}
			else if (i > 0){
				x = x.right;
				comparisons++;
			}
			else if (i == 0){
				splay(x);
				comparisons++;
				return x.val;
				
			}
			else {
				splay(x);
				comparisons++;
				return null;
			}
		}return null;
	}
	
	// TESTING METHODS
	// Serialization method provided in assignment
	private void serializeAux(Node tree, Vector<String> vec) {
		if (tree == null)
			vec.addElement(null);
		else {
			vec.addElement(tree.key.toString() + ":black");
			serializeAux(tree.left, vec);
			serializeAux(tree.right, vec);
		}
	}
	
	//Vector method provided in assignment
	public Vector<String> serialize() {
		Vector<String> vec = new Vector<String>();
		serializeAux(root, vec);
		return vec;
	}
	
	//Testing function provided in assignment
	//Creates an SVG file showing tree provided in Figure 3
	public static void main(String[] args) throws FileNotFoundException{
		SplaySymbolTable<Integer,Integer> table = new SplaySymbolTable<Integer,Integer>();
		table.insert(1, 1);
		table.insert(2, 2);
		table.insert(3, 3);table.insert(4, 4);
		table.insert(9, 9);table.insert(8, 8);table.insert(7, 7);table.insert(6, 6);
		
		table.search(3);
		table.search(9);
		table.search(7);
		
		Vector<String> serializedTable = table.serialize();
		TreePrinter treePrinter = new TreePrinter(serializedTable);
		FileOutputStream out = new FileOutputStream("tree.svg");
		PrintStream ps = new PrintStream(out);
		treePrinter.printSVG(ps);

	  }
}
