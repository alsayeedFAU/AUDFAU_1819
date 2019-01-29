/**
 * A Mapping maintaining a key and a value. The value may be changed.
 */
public class Mapping<K, V> {
	public final K key;
	public V value;
	public Mapping<K, V> next;

	public Mapping(K key, V value, Mapping<K, V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "(" + key + "," + value + "," + (next == null ? "\u22A5" : "\u27A1") + ")";
	}
}