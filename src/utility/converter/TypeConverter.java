package utility.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Der TypeConverter dient zum Umwandeln bestimmter Collections in eine andere.
 */
public class TypeConverter<K, V> {

	/**
	 * <i><b>Umwandeln der Werte einer Hashmap in eine ArrayList</b></i><br>
	 * <br>         
	 * @return ArrayList aus den Werten einer HashMap
	 */
	public ArrayList<V> hashmapvaluesToArrayList(HashMap<K,V> map){
		Iterator<V> it = map.values().iterator();
		ArrayList<V> arraylist = new ArrayList<>();
		while(it.hasNext()){
			arraylist.add(it.next());
		}
		return arraylist;
	}
	
	/**
	 * <i><b>Umwandeln der Schlüssel einer Hashmap in eine ArrayList</b></i><br>
	 * <br>         
	 * @return ArrayList aus den Schlüsseln einer HashMap
	 */
	public ArrayList<K> hashmapkeysToArrayList(HashMap<K,V> map){
		Iterator<K> it = map.keySet().iterator();
		ArrayList<K> arraylist = new ArrayList<>();
		while(it.hasNext()){
			arraylist.add(it.next());
		}
		return arraylist;
	}
	
}
