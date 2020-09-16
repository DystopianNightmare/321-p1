import java.util.LinkedList;

public class Cache<T> {


	private LinkedList<T> cache;
	private int cap;
	public int size;
	/**
	 * constructor to create a cache using a linked list
	 * @param capacity - size of the linked list
	 */
	public Cache(int capacity) {

		cache = new LinkedList<T>();
		cap = capacity;
		size = 0;
	}
	
	/**
	 * adds the object to the cache
	 * @param element - object to be added
	 */
	public void addObject(T element) {
		cache.addFirst(element);
		size++;
	}
	/*
	 * removes the object from the cache
	 */
	public void removeObject(T element) {
		cache.remove(element);
		size--;
	}
	/**
	 * clears the cache by making a new list
	 */
	public void clearCache() {
		cache = new LinkedList<T>();
		size = 0;

	}
	/**
	 * checks if cache is at capacity
	 * @return true if it is at capacity
	 */
	public boolean atCap() {
		return size >= cap;
	}

	/**
	 * searches cache for element
	 * @param element - element to be searched for
	 * @return true if element is found
	 */
	public boolean search( T element) {

		if(cache.contains(element)) {
			removeObject(element);
			addObject(element);
			return true;
		}
		else {
			if(!atCap()) {
				addObject(element);
			}else {
				cache.removeLast();
				size--;
				addObject(element);
			}
			return false;
		}
	}
	/**
	 * adds element to cache
	 * @param element - represents element to be added
	 */
	public void addToCache(T element) {
		if(!atCap()) {
			addObject(element);
		}else {
			cache.removeLast();
			size--;
			addObject(element);
		}


	}








}
