package collectorsTest;

import java.util.*;
import java.util.stream.*;

public class CollectorsAggregate {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		
		// Count elements
		long count = numbers.stream().collect(Collectors.counting());
		System.out.println("Count: " + count);
		
		// Sum elements
		int sum = numbers.stream().collect(Collectors.summingInt(Integer::intValue));
		System.out.println("Sum: " + sum);
		
		// Average of Elements
		double average = numbers.stream().collect(Collectors.averagingDouble(Integer :: intValue));
		System.out.println("Average: " + average);
	}

}
