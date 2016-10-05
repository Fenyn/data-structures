import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChainedTable<K, V> implements
		Iterable<ChainedTable.Entry<K, V>> {
	LinkedList<Entry<K, V>>[] bucketArray;
	int collisions;

	@SuppressWarnings("unchecked")
	/**
	 * Create a ChainedTable instance with the specified
	 * number of buckets.
	 * 
	 * @param buckets the number of buckets to make in this table
	 */
	public ChainedTable(int buckets) {
		bucketArray = (LinkedList<Entry<K, V>>[]) new LinkedList[buckets];
		collisions = 0;
		for (int i = 0; i < bucketArray.length; i++) {
			bucketArray[i] = new LinkedList<Entry<K, V>>();

		}

	}
	
	private int MakeHash(K key){
		return key.hashCode();
	}

	private int Compress(int hash){
		int compressed = 0;
		compressed = hash%bucketArray.length;
		return compressed;
	}
	
	public int HashCompress(K key){
		int z = MakeHash(key);
		z = Compress(z);
		return z;
	}

	/**
	 * _Part 1: Implement this method._
	 *
	 * Puts an entry into the table. If the key already exists,
	 * it's value is updated with the new value and the previous
	 * value is returned.
	 * 
	 * @param key
	 *            the object used as a key to retrieve the value
	 * @param value
	 *            the object stored in association with the key
	 * 
	 * @throws IllegalArugmentException
	 *            if the value parameter is null
	 *
	 * @return the previously stored value or null if the key is new
	 */
	public V put(K key, V value) {
		// TODO: implement this
		// make sure value is not null
		//	-if it is, throw IllegalArguementException
		// Need to compute Key's hash code
		// Compress the hash code to the size of the bucket array, call this z
		// Look in bucketArray[z] to check if **key** already exists
		// 	-if so update value and return old value
		// 	-if not, create new Entry, add to linked list, return null
		if (value == null){
			IllegalArgumentException e = new IllegalArgumentException();
			throw e;
		}
		int z = HashCompress(key);
		for(int i=0;i<bucketArray.length;i++){
			if(bucketArray[z].equals(key)){
				//placeholder function to add;
			}
		}
		return null;
	}

	/**
	 * _Part 2: Implement this method._
	 *
	 * Retrieves the value associated with the specified key. If
	 * it exists, the value stored with the key is returned, if no
	 * value has been associated with the key, null is returned.
	 * 
	 * @param key
	 *            the key object whose value we wish to retrieve
	 * @return the associated value, or null
	 */
	public V get(K key) {
		// TODO: implement this
		// Need to compute Key's hash code
		// Compress the hash code to the size of the bucket array, call this z
		// Look in bucketArray[z] to check if **key** already exists
		//	-if key already exists, return value
		//	-if not, return null
		int z = HashCompress(key);
		for(int i=0;i<bucketArray.length;i++){
			if(bucketArray[z].equals(key)){
				//function to return value
			}
		}
		return null;
	}

	/**
	 * _Part 3: Implement this method._
	 *
	 * Looks through the entire bucket where the specified key
	 * would be found and counts the number of keys in this bucket
	 * that are not equal to the current key, yet still have the
	 * same hashcode.
	 * 
	 * @param key
	 * @return a count of collisions
	 */
	public int countCollisions(K key) {
		// TODO: implement this
		// Need to compute Key's hash code
		// Compress the hash code to the size of the bucket array, call this z
		// Look in bucketArray[z]
		int z = HashCompress(key);
		return 0;
	}

	/**
	 * This method is given, but you must perform the work to
	 * maintain the collisions member variable that is read here.
	 * 
	 * @return a count of the number of collisions that have
	 *           occurred thus far.
	 */
	public int getCollisionCount() {
		// a bit tricky, but the computation can be acquired during calls to put
		//you can assume no calls to remove
		return collisions;
	}

	/**
	 * _Part 4: Implement this method._
	 *
	 * Removes the value associated with the specified key, if it exists.
	 * 
	 * @param key
	 *            the key used to find the value to remove.
	 *
	 * @return the value if the key was found, or null otherwise.
	 */
	public V remove(K key) {
		// TODO: implement this
		// Need to compute Key's hash code
		// Compress the hash code to the size of the bucket array, call this z
		// Look in bucketArray[z] to check if **key** already exists
		//	-if so, remove Entry instance associated with it
		//	-if not, return null
		int z = HashCompress(key);
		return null;
	}

	/**
	 * _Part 5: Implement this method._
	 *
	 * Returns the number of entries (i.e., Entry instances) in
	 * this table
	 * 
	 * @return the number of entries.
	 */
	public int size() {
		// TODO: implement this
		return 0;
	}

	/**
	 * 
	 * Creates and returns a new Iterator object that iterates
	 * over the keys currently in the table. The iterator should
	 * fail fast, and does not need to implement the remove
	 * method.
	 * 
	 * This method is implemented for you, but the real heavy
	 * lifting is done by the implementation of the TableIterator
	 * class
	 * 
	 * 
	 * @return a new Iterator object
	 */
	public Iterator<Entry<K, V>> iterator() {
		return new TableIterator();

	}

	/**
	 * 
	 * This stub implementation provided will not function
	 * correctly.  You should be sure to use and test it in the main
	 * method below.
	 * 
	 */
	public class TableIterator implements Iterator<Entry<K, V>> {
		// you'll probably want/need to use some instance variables
		
		
		/**
		 * _Part 6: Implement this constructor._
		 *
		 * Constructs a new TableIterator instance to walk over
		 * all Entry instances in the enclosing ChainedTable instance.
		 * 
		 */
		public TableIterator() {
			// TODO: implement this
		}

		@Override
		/**
		 * _Part 7: Implement this method._
		 *
		 *  @return true if and only if there is another Entry instance in the
		 *    ChainedTable that can be returned by a subsequent call to next()
		 *  
		 */
		public boolean hasNext() {
			// TODO: implement this
			return false;
		}

		@Override
		/**
		 * _Part 8: Implement this method._
		 *
		 *  @return the next element in the iteration overall all Entry instances
		 *  	in the ChainedTable
		 *  
		 *  @throws NoSuchElementException if the iteration has no more elements 
		 */
		public Entry<K, V> next() {
			// TODO: implement this
			return null;
		}
		
		@Override
		/**
		 * This method can stay empty, you do not have to 
		 * implement it.
		 */
		public void remove() {
			// TODO Auto-generated method stub

		}

	}

	public static class Entry<K, V> {
		K key;
		V value;

		public Entry(K k, V v) {
			key = k;
			value = v;
		}
	}

	/**
	 * This class represents a word of text. In essence, it is just like
	 * a String, but having our own implementation allows us to play with
	 * the hashCode.
	 * 
	 *
	 */
	public static class Word {
		String str;

		public Word(String s) {
			str = s;
		}

		/**
		 * _Part 0: Implement this method._
		 *
		 * @return an int representing the content of the word.
		 *
		 */
		public int hashCode() {
			// TODO: implement this
			return 0;
		}

		public boolean equals(Object o) {
			return str.equals(o.toString());
		}

		public String toString() {
			return str;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("./bigtext.txt"));

		ChainedTable<Word, Integer> map = new ChainedTable<Word, Integer>(
				1000);

		String str;
		Integer value;
		System.out.println("Reading");
		int i = 0;
		while (s.hasNext()) {
			str = s.next();
			value = map.get(new Word(str));
			// System.out.println("Got "+str);
			if (value == null) {
				map.put(new Word(str), 1);

			} else {
				map.put(new Word(str), value + 1);

			}
			i++;
			if (i % 1000 == 0)
				System.out.println("Read " + i + " words");

		}
		System.out.println("Counting");

		System.out.println("Total Collisions: " + map.getCollisionCount());

	}

}
