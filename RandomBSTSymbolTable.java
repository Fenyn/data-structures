import java.util.Random;
import java.util.Vector;

public class RandomBSTSymbolTable<K extends Comparable<K>, V> implements SymbolTable<K,V> {
	
	private Node root;
	static Random rng = new Random(1234);
	
	private class Node{
		private K key;
		private V val;
		private Node left, right;
		private int N; // N = cached size of tree (1 for leaf)
		
		private Node (K k, V v){
			key = k;
			val = v;
			left = null;
			right = null;
			N = 1;
		}
	}
	
	public RandomBSTSymbolTable() {root = null;}
	
	public void insert(K key, V val){
		root = insertRandom(root, key, val);
	}
	
	//Random insertion provided in assignment
	private Node insertRandom(Node tree, K key, V val){
		if (tree == null){tree = new Node(key,val);} 
		if (rng.nextDouble()*(tree.N+1) <1.0) {return insertRoot(tree,key,val);}
		int cmp = key.compareTo(tree.key);
		if (cmp == 0) {
			tree.key = key;
			tree.val = val;
		} else if (cmp < 0){
			tree.left = insertRandom(tree.left, key, val);
			tree.N = 1+size(tree.right)+size(tree.right);
		} else if (cmp > 0){
			tree.right = insertRandom(tree.right, key, val);
			tree.N = 1+size(tree.right)+size(tree.left);
		}
		return tree;
	}
	
	private Node insertRoot(Node tree, K key, V val){
		if (tree == null) {
			return new Node(key, val);
		}else{
			int cmp = key.compareTo(tree.key);
			if (cmp == 0) {
				tree.key = key;
				tree.val = val;
			} else if (cmp < 0){
				tree.left = insertRandom(tree.left, key, val);
				tree = rotateRight(tree);
			} else if (cmp > 0){
				tree.right = insertRandom(tree.right, key, val);
				tree = rotateLeft(tree);
			}
		}
		return tree;
	}
	
	private Node rotateRight(Node tree){
		assert tree != null && tree.left != null;
		Node root = tree.left;
		tree.left = root.right;
		root.right = tree;
		
		tree.N = 1 + size(tree.left) + size(tree.right);
		root.N = 1 + size(root.left) + tree.N;
		
		return root;
	}
	
	private Node rotateLeft(Node tree){
		assert tree != null && tree.right != null;
		Node root = tree.right;
		tree.right = root.left;
		root.left = tree;
		
		tree.N = 1 + size(tree.left) + size(tree.right);
		root.N = 1 + size(root.right) + tree.N;
		
		return root;
	}
	
	//search function using helper method
	public V search(K key) {
		Node node = searchHelper(root, key);
		if (node != null){
			return node.val;
		}
		else{return null;}
	}
	
	//helper method to recursively search
	private Node searchHelper(Node tree, K key){
		if (tree == null) {return null;}
		
		int cmp = key.compareTo(tree.key);
		
		if (cmp == 0) {return tree;}
		if (cmp < 0) {return searchHelper(tree.left, key);}
		
		return searchHelper(tree.right, key);
		
	}
	
	//returns size of a given tree
	private int size(Node tree) {return (tree==null) ? 0 : tree.N;}

	@Override
	public V remove(K key) {
		Node removed = new Node(null, null);
		root = removeHelper(root,key,removed);
		removeHelper(root,key,removed);
		root.N = 1 + size(root.left) + size(root.right);
		return removed.val;
	}
	
	//helper method to remove from BST
	private Node removeHelper(Node tree, K key, Node removed){
		if (tree == null) {return null;}
		
		int cmp = key.compareTo(tree.key);
		
		if (cmp == 0) {
			removed.key = tree.key;
			removed.val = tree.val;
			
			if (tree.left == null) {return tree.right;}
			else if (tree.right == null) {return tree.left;}
			else {tree = join(tree.left, tree.right);}
		
		} else if (cmp < 0) {tree.left = removeHelper(tree.left,key,removed);
		
		} else if (cmp > 0) {tree.right = removeHelper(tree.right,key,removed);
		
		} else {return null;}
		
		return tree;
	}


	//join helper function provided in assignment
	private Node join(Node X, Node Y){
		if (X == null) return Y;
		if (Y == null) return X;
		
		if (rng.nextDouble()*(X.N+Y.N)<X.N){
			X.right = join(X.right, Y);
			X.N = 1 + size(X.left) + size(Y.right);
			return X;
		} else {
			Y.left = join(X, Y.left);
			Y.N = 1 + size(Y.left) + size(Y.right);
			return Y;
		}
	}
	
	// Serialization methods provided in assignment
	private void serializeAux(Node tree, Vector<String> vec){
		if (tree == null)
			vec.addElement(null);
		else {
			vec.addElement(tree.key.toString() + ":black");
			serializeAux(tree.left, vec);
			serializeAux(tree.right, vec);
		}
	}
	
	public Vector <String> serialize() {
		Vector<String> vec = new Vector<String>();
		serializeAux(root, vec);
		return vec;
	}
}