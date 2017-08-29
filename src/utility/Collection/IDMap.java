package utility.Collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * IDMap ist ähnlich zu einer HashMap, die jedoch mehrere Buchungssätze mit der
 * gleichen ID aufnehmen kann.
 *
 */
public class IDMap<K, V> implements Serializable {

	private HashMap<K, ArrayList<V>> container;

	public IDMap() {
		container = new HashMap<>();
	}

	/**
	 * <i><b>Leeren der IDMap</b></i><br>
	 * <br>
	 * Entfernt alle Inhalte aus der IDMap. <br>
	 */
	public void clear() {
		container = new HashMap<>();
	}

	/**
	 * <i><b>Schlüssel in IDMap vorhanden?</b></i><br>
	 * <br>
	 * Prüft, ob der Key in der IDMap vorhanden ist. <br>
	 * 
	 * @param key
	 *            - zu überprüfender Schlüssel
	 * 
	 * @return Liefert, ob der Schlüssel vorhanden ist (true) oder nicht
	 *         (false).
	 */
	public boolean containsKey(K key) {
		return container.containsKey(key);

	}

	/**
	 * <i><b>Wert in IDMap vorhanden?</b></i><br>
	 * <br>
	 * Prüft, ob ein bestimmter Wert in der IDMap vorhanden ist. <br>
	 * 
	 * @param value
	 *            - zu überprüfender Wert
	 * 
	 * @return Liefert, ob der Wert vorhanden ist (true) oder nicht (false).
	 */
	public boolean containsValue(Object value) {
		Iterator<ArrayList<V>> it = container.values().iterator();
		while (it.hasNext()) {
			if (it.next().contains(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <i><b>zugehörige Wert zum Key entnehmen</b></i><br>
	 * <br>
	 * Gibt den n-ten Wert zurück, der dem Schlüssel zugeordnet wurde. <br>
	 * 
	 * @param key
	 *            - Schlüssel des Wertes
	 * @param index
	 *            - Position des Wertes
	 * 
	 * @return der Wert an der Position index
	 */
	public V get(K key, int index) {
		return container.get(key).get(index);
	}

	/**
	 * <i><b>zugehörige Werte zum Key entnehmen</b></i><br>
	 * <br>
	 * Gibt alle Werte zurück, die dem Schlüssel zugeordnet wurden. <br>
	 * 
	 * @param key
	 *            - Schlüssel des Wertes
	 * 
	 * @return alle zugeordneten Werte
	 */
	public ArrayList<V> getAll(K key) {
		return container.get(key);
	}

	/**
	 * <i><b>leere IDmap?</b></i><br>
	 * <br>
	 * Überprüft, ob die IDMap leer ist. <br>
	 * 
	 * @return Ob die IDMap leer ist (true) oder nicht (false).
	 */
	public boolean isEmpty() {
		return container.isEmpty();
	}

	/**
	 * <i><b>KeySet aller vorhandenen Schlüssel</b></i><br>
	 * <br>
	 * Gibt alle Schlüüsel zurück, die der IDMap hinzugefügt wurden. <br>
	 * 
	 * @return alle Schlüssel der IDMap
	 */
	public Set<K> keySet() {
		return container.keySet();
	}

	/**
	 * <i><b>Hinzufügen eines neuen Wertes</b></i><br>
	 * <br>
	 * Fügt der IDMap einen Wert hinzu, der einem bestimmten Schlüssel
	 * zugeordnet wird. Falls der Schlüssel noch nicht vorhanden ist, wird er
	 * neu hinzugefügt. <br>
	 * 
	 * @param key
	 *            - Schlüssel des Wertes
	 * @param value
	 *            - der Wert, der der IDMap hinzugefügt werden soll.
	 */
	public void put(K key, V value) {
		if (containsKey(key)) {
			container.get(key).add(value);
		} else {
			ArrayList<V> newlist = new ArrayList<>();
			newlist.add(value);
			container.put(key, newlist);
		}
	}

	/**
	 * <i><b>Hinzufügen neuer Werte</b></i><br>
	 * <br>
	 * Fügt der IDMap meherere Werte hinzu, die alle dem gleichen Schlüssel
	 * zugeordnet werden sollen. <br>
	 * 
	 * @param key
	 *            - Schlüssel des Wertes
	 * @param value
	 *            - Liste aller Werte, die der IDMap hinzugefügt werden sollen.
	 */
	public void putAll(K key, ArrayList<V> value) {
		container.put(key, value);
	}

	/**
	 * <i><b>Löschen eines Schlüssels</b></i><br>
	 * <br>
	 * Löscht einen bestimmten Schlüssel aus der IDmap inklusive seiner Werte.
	 * <br>
	 * 
	 * @param key
	 *            - Schlüssel, der gelöscht werden soll
	 * 
	 * @return Liste aller Werte, die dem Schlüssel zugeordnet wurden.
	 */
	public ArrayList<V> remove(K key) {
		return container.remove(key);
	}

	/**
	 * <i><b>Länge der IDMap</b></i><br>
	 * <br>
	 * Gibt die Anzahl aller in der IDMap vorhandenen Werte zurück. <br>
	 * 
	 * @return Länge der IDMap
	 */
	public int size() {
		int size = 0;
		Iterator<ArrayList<V>> it = container.values().iterator();
		while (it.hasNext()) {
			size += it.next().size();
		}
		return size;
	}

	/**
	 * <i><b>Alle Werte in einer Collection zurückgeben</b></i><br>
	 * <br>
	 * Gibt alle Werte in Form einer Collection zurück. <br>
	 * 
	 * @return Collection mit allen Werten
	 */
	public Collection<ArrayList<V>> values() {
		return container.values();
	}

}
