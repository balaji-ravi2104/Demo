package hashmapoperations;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import exception.CustomException;
import message.ConstantMessage;
import utils.UtilMethods;
 
public class HashMapHelper{

	public <K,V> int getMapSize(Map<K,V> hashMap) throws CustomException{
		UtilMethods.isNull(hashMap, ConstantMessage.INPUT_NULL_MESSAGE);
		return hashMap.size();
	}
	
	public  <K,V> void addKeyAndValue(Map<K, V> hashMap,K key,V value) throws CustomException{
		UtilMethods.isNull(hashMap, ConstantMessage.INPUT_NULL_MESSAGE);
		hashMap.put(key, value);
	}   

	public  <K,V> boolean isKeyExists(Map<K, V> hashMap,K key) throws CustomException{
		UtilMethods.isNull(hashMap, ConstantMessage.INPUT_NULL_MESSAGE);
		return hashMap.containsKey(key);
	}
	
	public  <K,V> boolean isValueExists(Map<K, V> hashMap,V value) throws CustomException{
		UtilMethods.isNull(hashMap, ConstantMessage.INPUT_NULL_MESSAGE);
		return hashMap.containsValue(value);
	}
	
	public  <K,V> void replaceValue(Map<K, V> hashMap,K key,V value) throws CustomException{
		UtilMethods.isNull(hashMap, ConstantMessage.INPUT_NULL_MESSAGE);
		hashMap.replace(key, value);
	}
	
	public  <K,V>  V getValue(Map<K, V> hashMap,V key) throws CustomException{
		UtilMethods.isNull(hashMap, ConstantMessage.INPUT_NULL_MESSAGE);
		return hashMap.get(key);
	}
	
	public  <K,V> void removeFromMap(Map<K, V> hashMap,K key) throws CustomException{
		UtilMethods.isNull(hashMap, ConstantMessage.INPUT_NULL_MESSAGE);
		hashMap.remove(key);
	}
	
	public  <K,V> V getDefaultValue(Map<K, V> hashMap,K key) throws CustomException{
		UtilMethods.isNull(hashMap, ConstantMessage.INPUT_NULL_MESSAGE);
		String returnValue = "ZOHO";
		return hashMap.getOrDefault(key, (V)returnValue);
	}
	
	public  <K,V> void removeKeyByValue(Map<K, V> hashMap, V value) throws CustomException{
		UtilMethods.isNull(hashMap,ConstantMessage.INPUT_NULL_MESSAGE);
		for(Map.Entry<K, V> entry : hashMap.entrySet()) {
			if (entry.getValue().equals(value)) {
                hashMap.remove(entry.getKey());
                break;
            }
		}
	}
	
	public  <K,V> void replaceValueByExisitingValue(Map<K, V> hashMap, K stringKey, V stringValue,
			V newValue) throws CustomException {
		UtilMethods.isNull(hashMap,ConstantMessage.INPUT_NULL_MESSAGE);
		hashMap.replace(stringKey, stringValue, newValue);
	}
	
	public  <K,V> void transferHashMapContents(Map<K, V> hashMap1, Map<K, V> hashMap2) throws CustomException {
		UtilMethods.isNull(hashMap1,ConstantMessage.INPUT_NULL_MESSAGE);
		UtilMethods.isNull(hashMap2,ConstantMessage.INPUT_NULL_MESSAGE);
		hashMap1.putAll(hashMap2);
	}
	
	public  <K,V> void removeAllEntries(Map<K, V> hashMap) throws CustomException {
		UtilMethods.isNull(hashMap,ConstantMessage.INPUT_NULL_MESSAGE);
		hashMap.clear();
	}
 	
	public  <K,V> Map<K, V> getNewHashMap() throws CustomException {
	    try {
	        Class<?> clazz = Class.forName("java.util.HashMap");
	        return (Map<K, V>) clazz.getDeclaredConstructor().newInstance();
	    } catch (ClassNotFoundException | InstantiationException |
	             IllegalAccessException | IllegalArgumentException |
	             InvocationTargetException | NoSuchMethodException |
	             SecurityException e) {
	        throw new CustomException("Error creating HashMap", e);
	    }
	}



	

}
