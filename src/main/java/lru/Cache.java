package lru;

public interface Cache<K, T> {
    T get(K key) throws CacheException;

    void clear();
}
