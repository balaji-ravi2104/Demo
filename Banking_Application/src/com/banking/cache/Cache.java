package com.banking.cache;

public interface Cache<K, V> {

	void set(K key, V value);

	V get(K key);

	void rem(K key);

	void close();
}
