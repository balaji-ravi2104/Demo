package arraylistoperations;

import exception.CustomException;
import message.ConstantMessage;
import utils.UtilMethods;

import java.lang.reflect.InvocationTargetException;
import java.util.List;   

public class ArrayListHelper{
	
	public <E> int findListSize(List<E> list) throws CustomException {
		UtilMethods.isNull(list, ConstantMessage.INPUT_NULL_MESSAGE);
        return list.size();
	}
	  

	 
	public <E> int findElementIndex(List<E> list, E element) throws CustomException {
		UtilMethods.isNull(list,ConstantMessage.INPUT_NULL_MESSAGE);
        UtilMethods.isNull(element,ConstantMessage.INPUT_NULL_MESSAGE);
        return list.indexOf(element);
	}

	 
	public <E> E findElement(List<E> list, int index) throws CustomException {
		int length = findListSize(list);
        UtilMethods.isPositionValid(index,length,ConstantMessage.POSITION_MISMATCH_MESSAGE);
        return list.get(index);
	}

	 
	public <E> int[] findDuplicateIndexes(List<E> list) throws CustomException {
		UtilMethods.isNull(list,ConstantMessage.INPUT_NULL_MESSAGE);
        int[] result = new int[2];
        for(E s:list){
        	int firstIndex = list.indexOf(s);
        	int lastIndex = list.indexOf(s);
            if(firstIndex!=lastIndex){
                result[0] = firstIndex;
                result[1] = lastIndex;
                break;
            }
        }
        return result;
	}

	 
	public <E> void addElementAtIndex(List<E> list, int index, E element) throws CustomException {
		 int length = findListSize(list);
	        UtilMethods.isNull(element,ConstantMessage.INPUT_NULL_MESSAGE);
	        UtilMethods.isPositionValid(index,length,ConstantMessage.POSITION_MISMATCH_MESSAGE);
	        list.add(index,element);
	}

	 
	public <E> List<E> getSubList(List<E> list, int startIndex, int lastIndex) throws CustomException {
		int length = findListSize(list);
        UtilMethods.validateStartEndPositon(startIndex,lastIndex,length,ConstantMessage.POSITION_MISMATCH_MESSAGE);
        return (List<E>) getNewArrayList().subList(startIndex, lastIndex);
	}  

	 
	public <E> List<E> combineLists(List<E> list1, List<E> list2) throws CustomException {
		UtilMethods.isNull(list1,ConstantMessage.INPUT_NULL_MESSAGE);
        UtilMethods.isNull(list2,ConstantMessage.INPUT_NULL_MESSAGE);
        List<E> result = getNewArrayList();
        result.addAll(list1);
        result.addAll(list2);
        return result;
	}

	 
	public <E> List<E> combineListsSecondAhead(List<E> list1, List<E> list2) throws CustomException {
		return combineLists(list2,list1);
	}
	 
	public <E> void removeElement(List<E> list, int index) throws CustomException {
		int length = findListSize(list);
        UtilMethods.isPositionValid(index,length,ConstantMessage.POSITION_MISMATCH_MESSAGE);
        list.remove(index);
	}

	 
	public <E> void deleteFirstListFromSecond(List<E> list1, List<E> list2) throws CustomException {
		UtilMethods.isNull(list1,ConstantMessage.INPUT_NULL_MESSAGE);
        UtilMethods.isNull(list2,ConstantMessage.INPUT_NULL_MESSAGE);
        list1.removeAll(list2);	
	}

	 
	public <E> void retainFirstListFromSecond(List<E> list1, List<E> list2) throws CustomException {
		UtilMethods.isNull(list1,ConstantMessage.INPUT_NULL_MESSAGE);
        UtilMethods.isNull(list2,ConstantMessage.INPUT_NULL_MESSAGE);
        list1.retainAll(list2);
	}


	 
	public <E> void clearList(List<E> list) throws CustomException {
		 UtilMethods.isNull(list,ConstantMessage.INPUT_NULL_MESSAGE);
	      list.clear();
	}

	 
	public <E> boolean checkIfElementPresent(List<E> list, E element) throws CustomException {
		 UtilMethods.isNull(list,ConstantMessage.INPUT_NULL_MESSAGE);
	     UtilMethods.isNull(element,ConstantMessage.INPUT_NULL_MESSAGE);
	     return list.contains(element);
	}

	 
	//@SuppressWarnings("unchecked")
	public  <E> List<E> getNewArrayList() throws CustomException{
		try {
			Class<?> clazz = Class.forName("java.util.ArrayList");
			return (List<E>) clazz.getDeclaredConstructor().newInstance();
		}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new CustomException("Error in Creating ArrayList",e);
		}
	}         

	 
	public <E> void appendData(List<E> list, E[] array) throws CustomException {
		UtilMethods.isNull(list,ConstantMessage.INPUT_NULL_MESSAGE);
        UtilMethods.isNull(array,ConstantMessage.INPUT_NULL_MESSAGE);
        for(E element:array){
            list.add(element);
        }
	}
    
    //Coding to the interface 
}
