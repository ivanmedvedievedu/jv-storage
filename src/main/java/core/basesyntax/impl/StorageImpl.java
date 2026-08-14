package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int STORAGE_SIZE = 10;
    private int storageElementsAmount = 0;
    private final K[] keyStorage = (K[]) new Object[STORAGE_SIZE];
    private final V[] valueStorage = (V[]) new Object[STORAGE_SIZE];

    @Override
    public void put(K key, V value) {
        int isPresentResult = isPresent(key);
        if (isPresentResult == -1) {
            for (int i = 0; i < keyStorage.length; i++) {
                if (valueStorage[i] == null) {
                    keyStorage[i] = key;
                    valueStorage[i] = value;
                    storageElementsAmount++;
                    break;
                }
            }
        } else {
            keyStorage[isPresentResult] = key;
            valueStorage[isPresentResult] = value;
        }
    }

    public int isPresent(K key) {
        for (int i = 0; i < keyStorage.length; i++) {
            if (keyStorage[i] == null && key == null && valueStorage[i] != null
                    || keyStorage[i] != null && keyStorage[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < keyStorage.length; i++) {
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
