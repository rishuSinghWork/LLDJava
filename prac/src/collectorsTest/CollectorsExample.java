package collectorsTest;

import java.util.*;
import java.util.stream.*;

public class CollectorsExample {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
		
		// Collect into a list
		List<String> nameList = names.stream().collect(Collectors.toList());
		System.out.println("List: " + nameList);
		
		// collect into a set 
		Set<String> nameSet = names.stream().collect(Collectors.toSet());
		System.out.println("Set: " + nameSet);

	}

}
