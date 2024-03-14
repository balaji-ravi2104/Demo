package com.banking.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.banking.utils.CustomException;

import redis.clients.jedis.Jedis;

public class RedisCache<K, V> implements Cache<K, V> {

	private final Jedis jedis;

	public RedisCache(int port) {
		this.jedis = new Jedis("localhost", port);
	}

	@Override
	public void set(K key, V value) throws CustomException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos)) {

			oos.writeObject(value);
			byte[] serializedValue = bos.toByteArray();
			jedis.set(key.toString().getBytes(), serializedValue);

		} catch (IOException e) {
			// e.printStackTrace();
			throw new CustomException("Error While Casting Values!!");
		}
	}

	@Override
	public V get(K key) throws CustomException {
		byte[] bytes = jedis.get(key.toString().getBytes());
		V deserializedValue = null;

		if (bytes != null) {
			try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
					ObjectInputStream in = new ObjectInputStream(bis)) {

				@SuppressWarnings("unchecked")
				V value = (V) in.readObject();

				deserializedValue = value;

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				throw new CustomException("Error While Casting Values!!");
			}
		}
		return deserializedValue;
	}

	@Override
	public void rem(K key) {
		jedis.del(key.toString().getBytes());

	}

	@Override
	public void close() {
		jedis.close();
	}

}
