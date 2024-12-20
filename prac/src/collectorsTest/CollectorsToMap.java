package collectorsTest;

import java.util.*;
import java.util.stream.*;

public class CollectorsToMap {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
		
		// Map each name to its length
		Map<String, Integer> nameLength = names.stream()
					.collect(Collectors.toMap(name -> name, name -> name.length()));
		
		System.out.println("Map: " + nameLength);
	}

}

