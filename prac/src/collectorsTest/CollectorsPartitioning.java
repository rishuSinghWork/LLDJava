package collectorsTest;
import java.util.*;
import java.util.stream.*;
public class CollectorsPartitioning {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		
		// Partition numbers even and odd
		Map<Boolean, List<Integer>> partitioned = numbers.stream()
				.collect(Collectors.partitioningBy(num -> num % 2 == 0));
		System.out.println("Partitioned: " + partitioned);

	}

}
