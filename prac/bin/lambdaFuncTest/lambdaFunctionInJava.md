Lambda Function 

	1. Single parameter : 
		x -> x * 2
		(x,y) -> x + y
		(x,y) -> {
			int sum =  x + y;
			return sum;
		}
		
	--> Usage 
	1. With functional interface
	1.0 Runnable :
		```
		Runnable r = () -> System.out.println("Running in a thread.");
		new Thread(r).start();
		```
		
		Collections : sorting, filtering, transforming collections
		```
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
		names.forEach(name -> System.out.println(name));
		```
		---------------------- STREAMS ------------------------------
		-------------------------------------------------------------
		
		Streams : mapping, filtering and reducing data in the stream 
		```
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		List<Integer> squares = numbers.stream()
			.map(x -> x * x)
			.collect(Collectors.toList());
		```
		
		Creating Streams : for collections, arrays or manually(? wtf does that mean)
			From collection : 
			```
			List<Integer> numbers = Arrays.asList(1,2,3,4,5);
			Stream<Integer> stream = numbers.stream();
			```
			Generally wont do that at least haven't done that ever store into something
			else.
			
			From array : 
			```
			Stream<Integer> stream = Stream.of("A", "B", "C");
			```
			can use this one for sure better than the last one 
			
		Stream Operations : 
			Intermediate : transform streams into other streams
				Lazy : they don't execute until a terminal operation is called.	
			Terminal : Execute the pipeline and produce a result.
				Eager : cause the stream pipeline to execute.
				
			E.G.1 	Filtering and Mapping 
			```
			List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
			
			// Find the names starting with the A and convert to upper case 
			List<String> filteredName = names.stream()
				.filter(name -> name.startsWith("A"))
				.map(String::toUpperCase)
				.collect(Collectors.toList());
			System.out.println(filteredNames);
			```
			
			E.G.2	Finding and Mapping 
			```
			List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
			
			// Find any even number
			Optional<Integer> anyEven = numbers.stream()
				.filter(x -> x % 2 == 0)
				.findAny();
			// Find if all numbers are positive 
			boolean allPositive = numbers.stream()
				.allMatch(x -> x > 0);
				
			System.out.println(anyEven.orElse(-1));
			System.out.println(allPositive);
			```
			
			E.G.3	Reduction 
			```
			List<Integer> numbers = Arrays.asList(1,2,3,4);
			
			// Sum all numbers using reduce
			int sum = numbers.stream()
				.reduce(0, Integer::sum);
			System.out.println(sum);
			```
			
			E.G.4	Grouping and Partitioning 
			```
			List<String> name = Arrays.asList("Alice", "Bob", "Charlie", "David");
			
			// group names by letters 
			Map<Integer, List<String>> groupedByLength = names.stream()
				.collect(Collectors.groupBy(String::length));(? really check this again)
				
			System.out.println(groupedByLength);
			```
			
			-->> Stream and Lambda Function 
			```
			List<Integer> numbers = Arrays.asList(1,2,3,4,5);
			
			// Find squares of even numbers
			List<Integer> evenSquares = numbers.stream()
				.filter(x -> x % 2 == 0)
				.map(x - x * x)
				.collect(Collectors.toList());
			System.out.println(evenSquares);
			```
		---------------------- STREAMS ------------------------------
		-------------------------------------------------------------
	1.1 Predicate<T>
		reps conditions that returns true or false.
		```
		Predicate<Integer> isEven = x -> x % 2 == 0;
		```
		
	1.2 Consumer<T>
		reps an operation that consumes a value without returning anything 
		```
		Consumer<String> print = x -> System.out.println(x);
		```
		
	1.3 Function<T, R>
		maps the input T to an output R
		```
		Function<Integer, String> stringify = x -> "Number: " + x;
		```
		
	1.4 Supplier<T>
		reps the supplier of values 
		```
		Supplier<Double> random = () -> Math.random();
		```
		
	1.5 Comparator<T>
		compare two objects
		```
		Comparator<String> lengthComparator = (a, b) -> a.length() - b.length();
		```	
	
	