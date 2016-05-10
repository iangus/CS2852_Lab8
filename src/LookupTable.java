import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * CS2852 - 041
 * Spring 2016
 * Lab
 * Name: Ian Guswiler
 * Created: 5/3/2016
 */
public class LookupTable<K extends Comparable<K>,V> implements Map<K,V>{
    private List<Entry<K,V>> entries;

    public LookupTable(){
        entries = new ArrayList<>();
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        Entry<K,V> lookFor = new AbstractMap.SimpleEntry<>((K) key,null);
        int result = Collections.binarySearch(entries,lookFor,Entry.comparingByKey());
        return result >= 0;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("This method has not been implemented.");
    }

    @Override
    public V get(Object key) {
        Entry<K,V> lookFor = new AbstractMap.SimpleEntry<>((K) key,null);
        int index = Collections.binarySearch(entries,lookFor,Entry.comparingByKey());
        return containsKey(key) ? entries.get(index).getValue() : null;
    }

    @Override
    public V put(K key, V value){
        V previousValue = null;
        Entry<K,V> lookFor = new AbstractMap.SimpleEntry<>(key,null);
        int index = Collections.binarySearch(entries,lookFor,Entry.comparingByKey());
        if(index >= 0){
            previousValue = entries.get(index).setValue(value);
        } else {
            entries.add(-(index) - 1, new AbstractMap.SimpleEntry<>(key, value));
        }

        return previousValue;
    }

    @Override
    public V remove(Object key) {
        V removed = null;
        Entry<K,V> lookFor = new AbstractMap.SimpleEntry<>((K) key,null);
        int index = Collections.binarySearch(entries,lookFor,Entry.comparingByKey());
        if(index >=0){
            removed = entries.remove(index).getValue();
        }
        return removed;
    }

    @Override
    public void putAll(Map m) {
        throw new UnsupportedOperationException("This method has not been implemented.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This method has not been implemented.");
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException("This method has not been implemented.");
    }

    @Override
    public Collection values() {
        throw new UnsupportedOperationException("This method has not been implemented.");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("This method has not been implemented.");
    }


}
