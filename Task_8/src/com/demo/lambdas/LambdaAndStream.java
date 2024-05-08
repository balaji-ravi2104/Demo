package com.demo.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaAndStream {

	public static void main(String[] args) {

//		1.Basic understanding of lambdas

//		Function can have 4 things
//		1.name -> can be anonymous
		// 2.parameter list
		// 3.body
//		4.return type -> inferred

		// Example for anonymous inner class

//		Thread thread = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("In another thread");
//			}
//		}); // if we create a thread like this we will get extra .class files

		// The above code can be written by using lambda as below(invoke dynamic)

		/*
		 * Thread thread = new Thread(() -> System.out.println("In another thread"));
		 * thread.start();
		 * 
		 * System.out.println("In Main");
		 */

//		2.Transforming iterative

//		Example for imperative style of iterating

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		// external iterators
		for (int i = 0; i < numbers.size(); i++) {
			System.out.println(numbers.get(i));
		}

		// another external iterator
		for (int num : numbers) {
			System.out.println(num);
		}

		// Example for functional style of iterating

		// internal iterator
		numbers.forEach(new Consumer<Integer>() {
			public void accept(Integer value) {
				System.out.println(value);
			}

		});

		numbers.forEach((Integer value) -> System.out.println(value));

		numbers.forEach((value) -> System.out.println(value));
		// Java 8 has type inference only for lambdas.

		numbers.forEach(value -> System.out.println(value));
		// parenthesis is optional , but only for one parameter lambdas.

		numbers.forEach(System.out::println);
		// Example for method reference

		
		
//		3. Examples for method references

		// parameter as an argument (method reference to an instance method)
		numbers.forEach(e -> System.out.println(e));
		numbers.forEach(System.out::println);

		// parameter as an argument to a static method (method reference to a static method)
		numbers.stream()
				//.map(e -> String.valueOf(e))
				.map(String::valueOf)
				.forEach(System.out::println);
		
		
		// parameter as a target (here, parameter e is a target)
		numbers.stream()
				//.map(e -> String.valueOf(e))
				.map(String::valueOf)
				//.map(e -> e.toString())
				.map(String::toString)
				.forEach(System.out::println);
		
		
		// two parameters as an arguments
		System.out.println(
				numbers.stream()
					   //.reduce(0,(total, e) -> Integer.sum(total,e)));
						.reduce(0,Integer::sum));
	
		
		//two parameters, one is target and another as an argument
		System.out.println(
				numbers.stream()
					   .map(String::valueOf)
					   //.reduce("",(carry,string) -> carry.concat(string))
					   .reduce("", String::concat));
		
//		4.Function Composition
		
		System.out.println(
				numbers.stream()
				   .filter(e -> e%2==0)
				   //.map(e -> e*2)
				   //.reduce(0,Integer::sum)); 
				   .mapToInt(e -> e*2)
				   .sum());
		
		
//		5. parallelizing (parallel stream)
		
		Timeit.code(() ->
		System.out.println(
				//numbers.stream()
			numbers.parallelStream()
				   .filter(e -> e%2==0) 
				   .mapToInt(LambdaAndStream::compute)
				   .sum()));
		
//		6. Collect in Streams
		List<Integer> doubleOfEven = 
				numbers.stream()
					   .filter(e ->e%2==0)
					   .map(e -> e*2)
					   .collect(Collectors.toList()); // we have toList,toSet,
		System.out.println(doubleOfEven);
		
		
//		toMap -> for map we have to specify the key and value
		List<Person> people = createPeople();
		System.out.println(people.stream()
				  .collect(Collectors.toMap(
				   person -> person.getName() +"-"+person.getAge(),
				   person -> person)));
		
		// grouping and mapping example
		System.out.println(
				people.stream()
					  .collect(Collectors.groupingBy(Person::getName,
							   Collectors.mapping((Person::getAge), Collectors.toList()))));
		
//		 7.lazy evaluation
		System.out.println(
				numbers.stream()
					   .filter(LambdaAndStream::isGT3)
					   .filter(LambdaAndStream::isEven)
					   .map(LambdaAndStream::doubleIt)
					   .findFirst());
		// if we perform that above code in imperative style , we got zero as OP if the list is empty, but here we get optional[] value or Optional.empty
		
//		8.properties of stream
		// sized,ordered,distinct,sorted
				numbers.stream()
					   .filter(LambdaAndStream::isEven)
					   .sorted()
					   .distinct()
					   .forEach(System.out::println);
				
				
		// Example for infinite stream
		System.out.println(Stream.iterate(100, e -> e+1));
		
		int k = 121,n=51;
		System.out.println(
				Stream.iterate(k, e -> e+1) // unbounded, lazy
					  .filter(LambdaAndStream::isEven) // unbounded, lazy
					  .filter(e -> Math.sqrt(e)>20) // unbounded, lazy
					  .mapToInt(e -> e*2) // unbounded, lazy
					  .limit(n) // sized,lazy
					  .sum()); // terminal operation
	}
	
	public static boolean isGT3(int num) {
		return num >3;
	}
	
	public static boolean isEven(int num) {
		return num % 2 == 0;
	}
	
	public static int doubleIt(int num) {
		return num*2;
	}
	
	public static int compute(int num) {
		// assume this is time intensive
		try {Thread.sleep(1000);}catch (Exception e) {}
		return num*2;
	}
	
	public static List<Person> createPeople(){
		return Arrays.asList(
				new Person("bala", "male", 22),
				new Person("bala", "male", 21),
				new Person("hem", "male", 22),
				new Person("sury", "male", 30),
				new Person("ponvel", "male", 25),
				new Person("hem", "male", 28),
				new Person("sharan", "male", 21),
				new Person("prasanth", "male", 22)
		);
	}

}
