/*
 * CS2852 - 041
 * Spring 2016
 * Lab 8
 * Name: Ian Guswiler
 * Created: 5/3/2016
 */

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Lookup map that holds entries sortable by K values
 *
 * @author Ian Guswiler
 * @version 5/9/16
 * @param <K> key
 * @param <V> value
 */
public class LookupTable<K extends Comparable<K>,V> implements Map<K,V>{
    private List<Entry<K,V>> entries;

    /**
     * Constructor that creates a new lookup table with an empty ArrayList for entries.
     */
    public LookupTable(){
        entries = new ArrayList<>();
    }

    /**
     * Gives the current size of the lookup table
     * @return size of the lookup table
     */
    @Override
    public int size() {
        return entries.size();
    }

    /**
     * Checks if the table is empty
     * @return returns true if the table is empty
     */
    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    /**
     * Checks the table for a specified key
     * @param key key to be searched for
     * @return returns true if an entry with the specified key is found
     */
    @Override
    public boolean containsKey(Object key) {
        Entry<K,V> lookFor = new AbstractMap.SimpleEntry<>((K) key,null);
        int result = Collections.binarySearch(entries,lookFor,Entry.comparingByKey());
        return result >= 0;
    }

    /**
     * Not implemented
     * @param value Ignored
     * @return Not implemented
     */
    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("This method has not been implemented.");
    }

    /**
     * Retrieves the value associated with a specified key
     * @param key the key to which the value is associated
     * @return value associated with the specified key
     */
    @Override
    public V get(Object key) {
        Entry<K,V> lookFor = new AbstractMap.SimpleEntry<>((K) key,null);
        int index = Collections.binarySearch(entries,lookFor,Entry.comparingByKey());
        return containsKey(key) ? entries.get(index).getValue() : null;
    }

    /**
     * Places the specified key and value combo in the table
     * @param key key to be placed in the table
     * @param value associated value to be placed in the table
     * @return previous value that the key was associated with. Returns null if the key was not in the table.
     */
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

    /**
     * Removes the entry for a specified key
     * @param key key to be removed from the table
     * @return value that was removed with the key. Returns null if the key was not in the table.
     */
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

    /**
     * Not implemented
     */
    @Override
    public void putAll(Map m) {
        throw new UnsupportedOperationException("This method has not been implemented.");
    }

    /**
     * Not implemented
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException("This method has not been implemented.");
    }

    /**
     * Not implemented
     * @return Ignored
     */
    @Override
    public Set keySet() {
        throw new UnsupportedOperationException("This method has not been implemented.");
    }

    /**
     * Not implemented
     * @return Ignored
     */
    @Override
    public Collection values() {
        throw new UnsupportedOperationException("This method has not been implemented.");
    }

    /**
     * Not implemented
     * @return Ignored
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("This method has not been implemented.");
    }


}
