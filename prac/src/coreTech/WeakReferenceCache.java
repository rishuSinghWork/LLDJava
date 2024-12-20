package coreTech;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
//
//class Data{
//	private final String content;
//	
//	public Data(String content) {
//		this.content = content;
//	}
//	
//	public String getContent() {
//		return content;
//	}
//}


class WeakReferenceCache {
	// Cache to store weak reference to Data objects
	private Map<String, WeakReference<Data>> cache = new HashMap<>();
	
	// Method to add data in the cache 
	public void putData(String key, Data data) {
		cache.put(key, new WeakReference<>(data));
	}
	
	// Method to retrieve data from the cache 
	public Data getData(String key) {
		WeakReference<Data> dataRef = cache.get(key);
		if(dataRef != null) {
			Data data = dataRef.get();
			if(data != null) {
				System.out.println("Cache Hit: Data found in cache.");
				return data;
			} else {
				System.out.println("Cache Miss: Data was garbage collected.");
			}
		} else {
			System.out.println("Cache Miss: No entrey for the given key.");
		}
		return null;
	}
	
	public static void main(String[] args) {
		WeakReferenceCache cache = new WeakReferenceCache();
		String key = "sampleKey";
		
		// Create a new data object and add it to cache 
		Data data = new Data("This is a sample data.");
		cache.putData(key, data);
		
		// Retrieve data from the cache
		Data cacheData = cache.getData(key);
		System.out.println("Retrieved content: " + (cacheData != null ? cacheData.getContent() : null));
		
		// Suggest to the JVM to perform garbage collection 
		data = null; // remove strong reference to the data 
		System.gc(); // Request garbage collection 
		
		// Try to retrieve data again after GC 
		cacheData = cache.getData(key);
		System.out.println("Retrieved content after GC: " + (cacheData != null ? cacheData.getContent():"null"));
	}
}
