package Runner;

import java.util.Arrays;
import java.util.List;

public class Runner {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		// Parameter as an argument
		list.forEach(System.out::println); // Method reference to an instance(println) method.

		// Parameter as an argument to a static method..
		list.stream()
				// .map(e->String.valueOf(e)) can be wriiten as below
				.map(String::valueOf) // Method reference to an static(valueOf) method..
				.forEach(System.out::println);
		
		// Parameter as a target
		list.stream()
				.map(String::valueOf)
				// .map(e -> e.toString()) 
				// Method reference to an instance Method and here parameter used as a target(e.toString())..
				.map(String::toString)
				.forEach(System.out::println);
		
		
		// Two Parameter as an argument
		System.out.println("Sum is : "+
		list.stream()
				//.reduce(0,(total,e)-> Integer.sum(total, e))); // here order of the parameter is matters
				  .reduce(0, Integer::sum));
		
		
		// Two Parameter and one as target and another one as argument
		System.out.println("Concat Value is : "+
		list.stream()
			.map(String::valueOf)
			//.reduce("", (carry,str)->carry.concat(str)));
			.reduce("", String::concat));
		
		// Filter example
		System.out.println("Square and Sum of Even Numbers : "+
		list.stream()
			.filter(e -> e%2==0)
			//.map(e -> e*2)
			.mapToInt(e -> e*2)
			//.reduce(0,Integer::sum));
			.sum());
		
	}
	
}
