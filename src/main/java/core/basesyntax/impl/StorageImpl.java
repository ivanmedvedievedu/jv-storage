package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int STORAGE_SIZE = 10;
    private int storageElementsAmount = 0;
    private final K[] keyStorage = (K[]) new Object[STORAGE_SIZE];
    private final V[] valueStorage = (V[]) new Object[STORAGE_SIZE];

    @Override
    public void put(K key, V value) {
        for (int i = 0; i < keyStorage.length; i++) {
            if (key == null && keyStorage[i] == null && valueStorage[i] == null) {
                valueStorage[i] = value;
                storageElementsAmount++;
                break;
            } else if (key != null && keyStorage[i] == null && valueStorage[i] == null) {
                valueStorage[i] = value;
                keyStorage[i] = key;
                storageElementsAmount++;
                break;
            } else if (key == null && keyStorage[i] == null && valueStorage[i] != null) {
                valueStorage[i] = value;
                break;
            } else if (key != null && key.equals(keyStorage[i]) && valueStorage[i] != null) {
                valueStorage[i] = value;
                break;
            }
        }
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < storageElementsAmount; i++) {
            if (key == null) {
                if (keyStorage[i] == null) {
                    return valueStorage[i];
                }
            } else if (key.equals(keyStorage[i])) {
                return valueStorage[i];
            }
        }
        return null;
    }

    @Override
    public int size() {
        return storageElementsAmount;
    }
}
