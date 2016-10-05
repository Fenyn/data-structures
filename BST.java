
/**
 * A Binary Search Tree is a Binary Tree with the following
 * properties:
 * 
 * For any node x: (1) the left child of x compares "less than" x; 
 * and (2) the right node of x compares "greater than or equal to" x
 *
 *
 * @param <T> the type of data object stored by the BST's Nodes
 */
public class BST<T extends Comparable<T>> {

	/**
	 * The root Node is a reference to the 
	 * Node at the 'top' of a QuestionTree
	 */
	private Node<T> root;
	
	
	/**
	 * Construct a BST
	 */
	public BST() {
		root = null;
	}
	
	/**
	 * @return the root of the BST
	 */
	public Node<T> getRoot() { 
		return root;
	}
	
	/**
	 * _Part 1: Implement this method._
	 *
	 * Add a new piece of data into the tree. To do this, you must:
	 * 
	 * 1) If the tree has no root, create a root node 
	 *    with the supplied data
	 * 2) Otherwise, walk the tree from to the bottom (to a leaf) as though
	 *    searching for the supplied data. Then:
	 * 3) Add a new Node to the leaf where the search ended.
	 *
	 * @param data - the data to be added to the tree
	 */
	public void add(T data) {
		// made a different function that takes a node input as well so I can use recursion
		addFixer(data, root);
	}
	
	private Node<T> addFixer(T data2, Node<T> currentNode){
		//makes a root if none exists
		if( root == null){
			root = new Node<T>(data2);
		}
		//makes new node when correct spot is found
		if( currentNode == null){
			currentNode = new Node<T>(data2);
		}
		else {
			//compares key to data, then traverses tree in correct direction
			if (data2.compareTo(currentNode.data) < 0){
				currentNode.left = addFixer(data2, currentNode.left);
				currentNode.left.parent = currentNode;
			}
			else if (data2.compareTo(currentNode.data) >= 0){
				currentNode.right = addFixer(data2, currentNode.right);
				currentNode.right.parent = currentNode;
			}
			
		}
		return currentNode;
	}
	
	
	/**
	 * _Part 2: Implement this method._
	 * 
	 * Find a Node containing the specified key and
	 * return it.  If such a Node cannot be found,
	 * return null.  This method works by walking
	 * through the tree starting at the root and
	 * comparing each Node's data to the specified 
	 * key and then branching appropriately.
	 * 
	 * @param key - the data to find in the tree
	 * @return a Node containing the key data, or null.
	 */
	public Node<T> find(T key) {
		// made a new function that includes a node input so I can use recursion		
		return findFixer(key, root);
	}

	private Node<T> findFixer(T key, Node<T> currentNode){
		// returns null if somehow we end up with a bad input
			if(currentNode == null){
				return null;
			}
			//returns node when found
			if(currentNode.data.compareTo(key) == 0){
				return currentNode;
			}
			//compares data to key and traverses in direction
			else if(currentNode.data.compareTo(key) > 0){
				return findFixer(key, currentNode.left);
			}
			else if(currentNode.data.compareTo(key) < 0){
				return findFixer(key, currentNode.right);
			}
		return currentNode;
	}
	/**
	 * _Part 3: Implement this method._
	 *  
	 * @return the Node with the largest value in the BST
	 */
	public Node<T> maximum() {
		// goes through tree until rightmost element is found and returns it
		Node<T> startNode = root;
		while (startNode.right != null){
			startNode = startNode.right;
		}
		return startNode;
	}
	
	public Node<T> maxWithInput(Node<T> startNode){
		//same as maximum but with custom start node
		while (startNode.right != null){
			startNode = startNode.right;
		}
		return startNode;
	}
	
	/**
	 * _Part 4: Implement this method._
	 *  
	 * @return the Node with the smallest value in the BST
	 */
	public Node<T> minimum() {
		// goes through tree until leftmost element is found and returns it
		Node<T> startNode = root;
		while(startNode.left != null){
			startNode = startNode.left;
		}
		return startNode;
		
		
	}
	
	public Node<T> minWithInput(Node<T> startNode){
		//same thing as minimum but with custom start node
		while(startNode.left != null){
			startNode = startNode.left;
		}
		return startNode;
	}

	
	/**
	 * _Part 5: Implement this method._
	 *  
	 * Searches for a Node with the specified key.
	 * If found, this method removes that node
	 * while maintaining the properties of the BST.
	 *  
	 * @return the Node that was removed or null.
	 */
	public Node<T> remove(T data) {
		// uses previous finder function to find what node needs removal
		Node<T> targetNode = findFixer(data, root);
		// returns root if node is root
		if(targetNode == root){
			return root;
		}
		//if node has 0 children, it simply removes it
		if(targetNode.left == null && targetNode.right == null){
			if( targetNode.parent.left == targetNode){
				targetNode.parent.left = null;
			}
			else if (targetNode.parent.right == targetNode){
				targetNode.parent.right = null;
			}
			targetNode.parent = null;
			return targetNode;
		}
		//if node has 1 child, it removes node and reassigns child to its place
		else if (targetNode.left == null && targetNode.right != null){
			targetNode.parent.right = targetNode.right;
			targetNode.parent = null;
			return targetNode;
		}
		else if (targetNode.left != null && targetNode.right == null){
			targetNode.parent.left = targetNode.left;
			targetNode.parent = null;
			return targetNode;
		}
		
		//if node has 2 children, finds minimum element on right side
		//then assigns that child to its current spot,
		// removes that child, and removes current node
		else if (targetNode.left != null && targetNode.right != null){
			Node<T> nodeReplace = minWithInput(targetNode.right);
			if (targetNode.parent.right == targetNode){
				targetNode.parent.right = nodeReplace;
			}
			else if (targetNode.parent.left == targetNode){
				targetNode.parent.left = nodeReplace;
			}
			nodeReplace.parent = targetNode.parent;
			nodeReplace.right = targetNode.right;
			nodeReplace.left = targetNode.left;
			targetNode.parent = null;
			targetNode.left = null;
			targetNode.right = null;
			
			return targetNode;
		}
		
		return null;
	}
	
	/**
	 * 
	 * The Node class for our BST.  The BST
	 * as defined above is constructed from zero or more
	 * Node objects. Each object has between 0 and 2 children
	 * along with a data member that must implement the
	 * Comparable interface.
	 * 
	 * @param <T>
	 */
	public static class Node<T extends Comparable<T>> {
		private Node<T> parent;
		private Node<T> left;
		private Node<T> right;
		private T data;
		
		private Node(T d) {
			data = d;
			parent = null;
			left = null;
			right = null;
		}
		public Node<T> getParent() { return parent; }
		public Node<T> getLeft() { return left; }
		public Node<T> getRight() { return right; }
		public T getData() { return data; }
	}
}
