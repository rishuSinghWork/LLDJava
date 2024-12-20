package collectorsTest;
import java.util.*;
import java.util.stream.*;
public class CollectorsJoining {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
		
		// Join names 
		String joinedNames = names.stream().collect(Collectors.joining(" and "));
		System.out.println("Joined names: " + joinedNames);
	}

}
