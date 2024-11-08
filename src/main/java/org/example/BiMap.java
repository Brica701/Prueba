package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public class BiMap<K, V> {
    private final Map<K, V> keyToValueMap;
    private final Map<V, K> valueToKeyMap;
    public BiMap() {
        this.keyToValueMap = new HashMap<>();this.valueToKeyMap = new HashMap<>();
    }
    public BiMap<K, V> put(K key, V value) {
        if (valueToKeyMap.containsKey(value) &&
                !Objects.equals(valueToKeyMap.get(value), key)) {
            throw new IllegalArgumentException("El valor " + value +
                    " ya está asociado con otra clave.");
        }
        if (keyToValueMap.containsKey(key)) {
            V oldValue = keyToValueMap.get(key);
            if (!Objects.equals(oldValue, value)) {
                valueToKeyMap.remove(oldValue);
                keyToValueMap.put(key, value);
                valueToKeyMap.put(value, key);
            }
        } else {
            keyToValueMap.put(key, value);
            valueToKeyMap.put(value, key);
        }
        return this;
    }
    public BiMap<K, V> forcePut(K key, V value) {

        if (valueToKeyMap.containsKey(value)) {
            K existingKey = valueToKeyMap.get(value);
            keyToValueMap.remove(existingKey);
            valueToKeyMap.remove(value);
        }
        if (keyToValueMap.containsKey(key)) {V oldValue = keyToValueMap.get(key);
            valueToKeyMap.remove(oldValue);
        }
        keyToValueMap.put(key, value);
        valueToKeyMap.put(value, key);
        return this;
    }
    public BiMap<V, K> inv() {
        BiMap<V, K> invertedBiMap = new BiMap<>();
        for (Map.Entry<K, V> entry : keyToValueMap.entrySet()) {
            invertedBiMap.put(entry.getValue(), entry.getKey());
        }
        return invertedBiMap;
    }
    public V get(K key) {
        return keyToValueMap.get(key);
    }
    public K getKey(V value) {
        return valueToKeyMap.get(value);
    }
    public boolean containsKey(K key) {
        return keyToValueMap.containsKey(key);
    }
    public boolean containsValue(V value) {
        return valueToKeyMap.containsKey(value);
    }
    @Override
    public String toString() {
        return keyToValueMap.toString();
    }
}
