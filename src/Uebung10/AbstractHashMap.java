/**
 * This class provides a skeletal implementation of a simple hash map.
 *
 * @param <K>
 *            the type of keys maintained by this map
 * @param <V>
 *            the type of mapped values
 */
public abstract class AbstractHashMap<K, V> {
	/**
	 * The number of key-value mappings in this map.
	 */
	protected int size;
	/**
	 * The internal representation of the HashMap. Each non-empty {@link #buckets} entry is a reference to the head of a linked list used for collision handling.
	 */
	protected Mapping<K, V>[] buckets;

	/**
	 * Constructs an empty HashMap with the specified initial capacity.
	 * 
	 * @param initialCapacity
	 *            the initial capacity, i.e. the size of the internal data structure {@link #buckets}
	 * @throws IllegalArgumentException
	 *             if the initial capacity is zero or negative.
	 */
	protected AbstractHashMap(int initialCapacity) {
		initialize(initialCapacity);
	}

	/**
	 * Initializes the {@link #buckets} to the given capacity. All possibly contained {@link #buckets} are released (i.e. emptied) and the {@link #size} is reset accordingly.
	 */
	@SuppressWarnings("unchecked")
	protected final void initialize(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException();
		}
		this.buckets = new Mapping[capacity];
		this.size = 0;
	}

	/**
	 * Returns the number of key-value mappings in this map.
	 * <p>
	 * This method <b>must have O(1)</b> runtime complexity!
	 * </p>
	 * 
	 * @return the number of key-value mappings in this map
	 */
	public abstract int size();

	/**
	 * Unconditionally appends the given mapping to <b>the end of the bucket</b> with the given index and increases {@link #size}. This method does <b>not</b> check or handle the case where a mapping with the same key already exists in this map!
	 *
	 * @param idx
	 *            index of the bucket for the given mapping
	 * @param mapping
	 *            the new mapping to insert <b>at the end</b> of the bucket at the given index
	 */
	protected abstract void insertMapping(int idx, Mapping<K, V> mapping);

	/**
	 * Calculates the index of the bucket for the given key. The index is based on the {@link #hashCode()} (ATTENTION: <i>might be negative or huge!</i>) of the given key <b>truncated</b> (modulo) and <b>shifted</b> (addition) if necessary to match the bounds of {@link #buckets}.
	 * <p>
	 * This method <b>must have O(1) runtime</b> complexity - <b>DO NOT USE</b> loops or any classes from the Java API!
	 * </p>
	 *
	 * @param key
	 *            the key for which the bucket index in the internal representation must be determined
	 * @return the index of the bucket that possibly contains the specified key resp. where a new mapping with the specified key would be inserted next
	 * @throws IllegalArgumentException
	 *             if the given key is {@code null}
	 */
	protected abstract int getBucketIndex(K key);

	/**
	 * Retrieves the mapping with the specified key, or {@code null} if this map contains no mapping with that key.
	 * <p>
	 * More formally, if this map contains a mapping from a key k to a value v such that key.equals(k), then this method returns the mapping (k,v); otherwise it returns {@code null}. (There can be at most one such mapping.)
	 * </p>
	 *
	 * @param key
	 *            the key whose associated mapping is to be returned
	 * @return the mapping with the specified key, or {@code null} if this map contains no mapping with that key
	 * @throws IllegalArgumentException
	 *             if the specified key is {@code null}.
	 */
	protected abstract Mapping<K, V> getMapping(K key);

	/**
	 * Returns the value to which the specified key is mapped, or {@code null} if this map contains no mapping for the key.
	 * <p>
	 * More formally, if this map contains a mapping from a key k to a value v such that key.equals(k), then this method returns v; otherwise it returns {@code null}. (There can be at most one such mapping.)
	 * </p>
	 * <p>
	 * A return value of {@code null} does not <i>necessarily</i> indicate that the map contains no mapping for the key; it's also possible that the map explicitly maps the key to {@code null}. The {@link #containsKey(Object)} operation may be used to distinguish these two cases.
	 * </p>
	 *
	 * @param key
	 *            the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or {@code null} if this map contains no mapping for the key
	 * @throws IllegalArgumentException
	 *             if the specified key is {@code null}
	 */
	public abstract V get(K key);

	/**
	 * Returns {@code true} if this map contains a mapping for the specified key.
	 *
	 * @param key
	 *            the key whose presence in this map is to be tested
	 * @return {@code true} if this map contains a mapping for the specified key.
	 * @throws IllegalArgumentException
	 *             if the specified key is {@code null}
	 */
	public abstract boolean containsKey(K key);

	/**
	 * Associates the specified value with the specified key in this map. If the map previously contained a mapping for the key, the old value is replaced. Otherwise a new mapping is created and inserted.
	 *
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return {@code true} if a new additional mapping has been added, {@code false} otherwise (i.e. if a mapping for the given key already existed and the value has just been updated)
	 * @throws IllegalArgumentException
	 *             if the specified key is {@code null}
	 */
	public abstract boolean put(K key, V value);

	/**
	 * Removes the mapping for the specified key from this map if present.
	 *
	 * @param key
	 *            key whose mapping is to be removed from the map
	 * @return {@code true} if a mapping with the specified key was found in and removed from this map, {@code false} otherwise
	 * @throws IllegalArgumentException
	 *             if the specified key is {@code null}
	 */
	public abstract boolean remove(K key);

	/**
	 * Resizes this map to use a greater number of buckets, while keeping all current entries of this HashMap.
	 *
	 * @param newCapacity
	 *            the new capacity (i.e. the new number of buckets after resizing)
	 * @throws IllegalArgumentException
	 *             if the desired new capacity is not truely bigger than the current capacity
	 */
	public abstract void resize(int newCapacity);
}