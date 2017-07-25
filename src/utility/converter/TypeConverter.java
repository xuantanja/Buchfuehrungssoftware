package utility.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class TypeConverter<K, V> {

	public ArrayList<V> hashmapvaluesToArrayList(HashMap<K,V> map){
		Iterator<V> it = map.values().iterator();
		ArrayList<V> arraylist = new ArrayList<>();
		while(it.hasNext()){
			arraylist.add(it.next());
		}
		return arraylist;
	}
	
	public ArrayList<K> hashmapkeysToArrayList(HashMap<K,V> map){
		Iterator<K> it = map.keySet().iterator();
		ArrayList<K> arraylist = new ArrayList<>();
		while(it.hasNext()){
			arraylist.add(it.next());
		}
		return arraylist;
	}
	
}
