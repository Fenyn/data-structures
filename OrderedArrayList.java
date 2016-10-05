
public class OrderedArrayList<T extends Comparable<T>> {

	/** This is an array of Objects of type T */
	private T[] array;
	private T[] newArray;


	@SuppressWarnings("unchecked")
	/**
	 * Construct an OrderedArrayList with 10 empty slots. Note the recipe for 
	 * creating an array with the generic type. You'll want to reuse this 
	 * recipe in the insert() method.  You'll also possibly want to tweak this 
	 * constructor a bit if you add other instance variables.
	 */
	public OrderedArrayList() {
		array = (T[]) new Comparable[10];

	}

	@SuppressWarnings("unchecked")
	/**
	 * _Part 1: Implement this method._
	 * 
	 * Inserts a new item in the OrderedArrayList. This method should ensure
	 * that the list can hold the new item, and grow the backing array if
	 * necessary. If the backing array must grow to accommodate the new item, it
	 * should grow by a factor of 2. The new item should be placed in sorted
	 * order using insertion sort. Note that the new item should be placed
	 * *after* any other equivalent items that are already in the list.
	 * 
	 * @return the index at which the item was placed.
	 */
	public int insert(T item) {
		// TODO: implement this
		for (int i=0; i< array.length; i++){
			/*if (array[i].equals(null)){
				continue;
			}
			else {
				array = (T[]) new Comparable[array.length*2];
			}*/
			if (array[i] == null){
				int temp = array.length*2;
				newArray = (T[]) new Comparable[temp];
			}
		}
		array = newArray;
		insertionSort(array);
		
		for (int i=0; i<array.length;i++){
			if (array[i] == (null)){
				array[i] = item;
			return i;
			}
		}
		
		return 0;
	}

	public void insertSort(T[] unsortedArray){
		for (int i=0; i<unsortedArray.length;i++){
	
			int j = i;
			T v = unsortedArray[i];
			
			for (j=1-1; j >= 0; j--){
				if (unsortedArray[j].compareTo(v) <= 0){
					break;
				}
				unsortedArray[j+1] = unsortedArray[j];	
				}
			unsortedArray[j+1]=v;
		}
			
	}

		
	private <A extends Comparable<A>> void insertionSort(A[] objArray) {

        A temp;

        for(int i = 1; i < objArray.length; i++) {



            // call compateTo() method to compare two objects.

            for(int j = i; (j > 0 ) && (objArray[j].compareTo(objArray[j-1]) < 0); j--) {

                temp = objArray[j];

                objArray[j] = objArray[j-1];

                objArray[j-1] = temp;

            }

        }
	}

	
	/**
	 * _Part 2: Implement this method._
	 * 
	 * @return the number of items in the list.
	 */
	public int size() {
		// TODO: implement this
		return 0;
	}

	/**
	 * _Part 3: Implement this method._
	 * 
	 * Gets an item from the ordered array list. You can assume that this method
	 * will only be called with valid values of index. Specifically, values that
	 * are in the range 0 - (size-1). To impress your friends and build your
	 * street cred, consider adding checks that the index supplied is in fact in
	 * bounds. If it is not, you can raise an IndexOutOfBoundsException.
	 * 
	 * @param index
	 *            the index to get an item from
	 * @return an item at the specified index
	 */
	public T get(int index) {
		// TODO: implement this
		return null;
	}

	/**
	 * _Part 3: Implement this method._
	 * 
	 * Counts the items in the ordered array list that are equal to the item at
	 * the specified index. Be sure to take advantage of the fact that the list
	 * is sorted here. You should not have to run through the entire list to
	 * make this count.
	 * 
	 * @param index
	 *            an index in the range 0..(size-1)
	 * @return the number of items in the list equal to the item returned by
	 *         get(index)
	 */
	public int countEquivalent(int index) {
		// TODO: implement this
		return 0;
	}

	/**
	 * _Part 4: Implement this method._
	 * 
	 * Finds the location of the first object that is equal to the specified
	 * object. Linear search is sufficient here, but feel free to leverage your
	 * binary search code too.
	 * 
	 * @param obj
	 *            an object to search for in the list
	 * @return the first index of an object equal to the one specified, or -1 if
	 *         no such object is found.
	 */
	public int find(T obj) {
		// TODO: implement this
		return 0;
	}

	/**
	 * _Part 5: Implement this method._
	 * 
	 * Removes all the objects equal to the specified object.
	 * 
	 * @param obj
	 *            an object equal to the one(s) you'd like to remove
	 * @return the number of objects removed
	 */
	public int remove(T obj) {
		// TODO: implement this
		return 0;
	}
	
	/**
	 * This method is included for testing purposes.
	 * Typically, you would not want to expose a private instance variable
	 * as we are doing here. However, it does have value when the code is 
	 * going through a testing phase. Do not modify or remove this method.
	 * Some WebCAT tests may rely upon it.
	 */
	public Comparable<T>[] dbg_getBackingStore() { return array; }
}
