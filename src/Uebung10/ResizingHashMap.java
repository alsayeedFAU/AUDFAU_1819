
public class ResizingHashMap<K, V> extends AbstractHashMap<K, V> {

	protected ResizingHashMap(int initialCapacity) {
		super(initialCapacity);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	protected void insertMapping(int idx, Mapping<K, V> mapping) {
		if (buckets[idx] == null) {
			buckets[idx] = mapping;
		} else {
			Mapping<K, V> next = buckets[idx];
			while (next.next != null) {
				next = next.next;
			}
			next.next = mapping;
		}

		size += 1;
	}

	@Override
	protected int getBucketIndex(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		return (((key.hashCode() % buckets.length) + buckets.length) % buckets.length);
	}

	@Override
	protected Mapping<K, V> getMapping(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		int idx = getBucketIndex(key);

		if (buckets[idx] != null) {
			if (buckets[idx].key.equals(key)) {
				return buckets[idx];
			}

			Mapping<K, V> next = buckets[idx];

			while (next.next != null) {
				if (next.next.key.equals(key)) {
					return next.next;
				}
				next = next.next;
			}
		}
		return null;
	}

	@Override
	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		return getMapping(key) == null ? null : getMapping(key).value;
	}

	@Override
	public boolean containsKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		return getMapping(key) != null;
	}

	@Override
	public boolean put(K key, V value) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		if (containsKey(key)) {
			getMapping(key).value = value;
			return false;
		}
		insertMapping(getBucketIndex(key), new Mapping<K, V>(key, value, null));
		return true;
	}

	@Override
	public boolean remove(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		if (!containsKey(key)) {
			return false;
		}

		if (buckets[getBucketIndex(key)].key.equals(key)) {
			buckets[getBucketIndex(key)] = buckets[getBucketIndex(key)].next;
		} else {
			Mapping<K, V> next = buckets[getBucketIndex(key)];
			while (!next.next.key.equals(key)) {
				next = next.next;
			}
			next.next = next.next.next;
		}
		size -= 1;
		return true;
	}

	@Override
	public void resize(int newCapacity) {
		if (newCapacity <= buckets.length) {
			throw new IllegalArgumentException();
		}

		Mapping<K, V>[] old = buckets;
		size = 0;
		buckets = new Mapping[newCapacity];

		for (int i = 0; i < old.length; i++) {
			if (old[i] != null) {
				Mapping<K, V> next = old[i];
				while (next != null) {
					insertMapping(getBucketIndex(next.key), new Mapping<K, V>(next.key, next.value, null));
					next = next.next;
				}

			}
		}

	}

}
