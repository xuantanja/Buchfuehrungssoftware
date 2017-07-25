package utility.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class IDMap<K, V> {

	private HashMap<K, ArrayList<V>> container;

	public void clear() {
		container = new HashMap<>();
	}

	public boolean containsKey(Object key) {
		return container.containsKey(key);
	}

	public boolean containsValue(Object value) {
		Iterator<ArrayList<V>> it = container.values().iterator();
		while (it.hasNext()) {
			if (it.next().contains(value)) {
				return true;
			}
		}
		return false;
	}

	public V get(K key, int index) {
		return container.get(key).get(index);
	}
	
	public ArrayList<V> getAll(K key) {
		return container.get(key);
	}

	public boolean isEmpty() {
		return container.isEmpty();
	}

	public Set<K> keySet() {
		return container.keySet();
	}

	public void put(K key, V value) {
		if(containsKey(key)){
			container.get(key).add(value);
		} else {
			ArrayList<V> newlist = new ArrayList<>();
			newlist.add(value);
			container.put(key, newlist);
		}
	}

	public void putAll(K key, ArrayList<V> value) {
		container.put(key, value);
	}

	public ArrayList<V> remove(K key) {
		return container.remove(key);
	}

	public int size() {
		int size = 0;
		Iterator<ArrayList<V>> it = container.values().iterator();
		while (it.hasNext()) {
			size += it.next().size();
		}
		return size;
	}

}
