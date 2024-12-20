package collectorsTest;

import java.util.*;
import java.util.stream.*;

public class CollectorsGrouping {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
		
		// Group names by their length
		Map<Integer, List<String>> groupedByLength = names.stream()
				.collect(Collectors.groupingBy(String :: length));
		System.out.println("Group by Length: " + groupedByLength);
	}

}
