public class MoveToFrontList {

	private StringCountElement head = new StringCountElement(); // the head reference
	private StringCountElement tail = new StringCountElement(); // the tail reference
	private int size; // the size of the list (number of valid items)

	/**
	 * _Part 1: Implement this constructor._
	 * 
	 * Creates a new, initially empty MoveToFontList. This list should be a
	 * linked data structure.
	 */
	public MoveToFrontList() {

		// Creates a new MoveToFrontList with placeholder head and tail elements
		// which point to each other. They have null keys so they won't affect
		// any searches or rankings.
		
		head.next = tail;
		tail.prev = head;
		head.key = null;
		tail.key = null;
		size = 0;
	}

	/**
	 * This method increments the count associated with the specified string
	 * key. If no corresponding key currently exists in the list, a new list
	 * element is created for that key with the count of 1. When this method
	 * returns, the key will have rank 0 (i.e., the list element associated with
	 * the key will be at the front of the list)
	 * 
	 * @param key
	 *            the string whose count should be incremented
	 * @return the new count associated with the key
	 */
	public int incrementCount(String key) {
		StringCountElement s = find(key);
		if (s != null) {
			// found the key, splice it out and increment the count
			spliceOut(s);
			s.count++;
			
		} else {
			// need to create a new element
			s = new StringCountElement();
			s.key = key;
			s.count = 1;
		}
		// move it to the front
		spliceIn(s, 0);
		return s.count;
	}

	/**
	 * 
	 * @return the number of items in the list
	 */
	public int size() {
		return size;
	}

	/**
	 * _Part 2: Implement this method._
	 * 
	 * Find the list element associated with the specified string. That is, find
	 * the StringCountElement that a key equal to the one specified
	 * 
	 * @param key
	 *            the key to look for
	 * @return a StringCountElement in the list with the specified key or null
	 *         if no such element exists.
	 */
	public StringCountElement find(String key) {
		
		//This element is a tracking element that I use to 
		//scan through the list
		StringCountElement current = head.next;
		
		for (int i =0; i< size();i++){
			//checks if current element is the matching one,
			//then returns it if it is
			if (current.key == key){
				return current;
			}
			else{
				//progresses through the list
				current = current.next;
			}
		}
		return null;
	}

	/**
	 * _Part 3: Implement this method._
	 * 
	 * Compute the rank of the specified key. Rank is similar to position, so
	 * the first element in the list will have rank 0, the second element will
	 * have rank 1 and so on. However, an item that does not exist in the list
	 * also has a well defined rank, which is equal to the size of the list. So,
	 * the rank of any item in an empty list is 0.
	 * 
	 * @param key
	 *            the key to look for
	 * @return the rank of that item in the rank 0...size() inclusive.
	 */
	public int rank(String key) {
		// initialize new elements that we use to find the
		// key to match as well as to track through the list
		StringCountElement toMatch = find(key);
		StringCountElement current = head.next;
		int counter = 0;
		
		
		for (int i=0; i<size();i++){
			// checks if they match and returns rank if they do 
			if(current==toMatch){
				return counter;
			}
			else{
				// iterates through list and increases rank
				current = current.next;
				counter++;
			}
		}
		return size();
	}

	/**
	 * _Part 4: Implement this method._
	 * 
	 * Splice an element into the list at a position such that it will obtain
	 * the desired rank. The element should either be new, or have been spliced
	 * out of the list prior to being spliced in. That is, it should be the case
	 * that: s.next == null && s.prev == null
	 * 
	 * @param s
	 *            the element to be spliced in to the list
	 * @param desiredRank
	 *            the desired rank of the element
	 */
	public void spliceIn(StringCountElement s, int desiredRank) {
		
		int currentRank = 0;
		StringCountElement tracker = head;
			// iterates through list until we reach the desired rank
			while(desiredRank != currentRank){
				currentRank = rank(tracker.key);
				tracker = tracker.next;
			}
			// edge case for if we're at the head of list
			if(desiredRank == currentRank){
				if(tracker.prev == null){
					s.prev = head;
					s.next = tracker.next;
				}
				// edge case for tail of list
				else if(tracker.next == null){
					s.next = tail;
					s.prev = tracker.prev;
				}
				// normal case
				else {
					s.next = tracker.next;
					s.prev = tracker.prev;
				}
				// increases size of list by 1 and modifies the
				// values of things on either side of spliced element
				size += 1;
				s.next.prev = s;
				s.prev.next = s;
			}
		return;
	}

	/**
	 * _Part 5: Implement this method._
	 * 
	 * Splice an element out of the list. When the element is spliced out, its
	 * next and prev references should be set to null so that it can safely be
	 * splicedIn later. Splicing an element out of the list should simply remove
	 * that element while maintaining the integrity of the list.
	 * 
	 * @param s
	 *            the element to be spliced out of the list
	 */
	public void spliceOut(StringCountElement s) {
		
		StringCountElement placeholderNext;
		
		//holds value of s' next element
		placeholderNext = s.next;
		
		//sets element after s' prev value to be s' prev value
		// and sets prior element to be linked to element after s
		s.next.prev = s.prev;
		s.prev.next = placeholderNext;
		
		//decrease size of list
		size -= 1;
		
		//sets s to be ready to spliced in again
		s.next = null;
		s.prev = null;
	}

}
