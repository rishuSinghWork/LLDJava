package coreTech;

import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Data{
	private final String content;
	
	public Data(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return  content;
	}
}

public class HighlyMemoryEfficientCache<K,V> {

	// ConcurrentHashMap to store data for thread safety(?)
	private final Map<K, WeakReference<V>> cache;
	
	// LRU to ensure recently used items are retained 
	private final LinkedHashMap<K, V> lruCache;
	
	private final int lruCacheSize;
	
	public HighlyMemoryEfficientCache(int lruCacheSize) {
		this.cache = new ConcurrentHashMap<>();
		this.lruCache = new LinkedHashMap<>(lruCacheSize, 0.75f, true) {
			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				return size() > lruCacheSize;
			}
		};
		this.lruCacheSize = lruCacheSize;
	}
	
	public void put(K key, V value) {
		synchronized(lruCache) {
			lruCache.put(key, value);
		}
		cache.put(key, new WeakReference<>(value));
	}
	
	public V get(K key) {
		V value;
		
		// First checking in the lruCache
		synchronized(lruCache) {
			value = lruCache.get(key);
		}
		if (value != null) {
			System.out.println("Cache Hit: Found in LRUC" + lruCache.keySet());
			return value;
		}
		
		// if not found moving to weakRef cache 
		WeakReference<V> weakRef = cache.get(key);
		if(weakRef != null) {
			value = weakRef.get();
			if(value != null) {
				synchronized(lruCache) {
					lruCache.put(key, value);
				}
				System.out.println("Cache Hit: Found in weak ref cache" + cache.keySet());
				return value;
			} else {
				System.out.println("Cache Miss: Weak reference was collected." + cache.keySet());
			}
		} else {
			System.out.println("Cache Miss: No entry found.");
		}
		return null;
	}
	
	public static void main(String[] args) {
		HighlyMemoryEfficientCache<String, Data> cache = new HighlyMemoryEfficientCache<>(3);
		
		// add data to the cache 
		cache.put("key1", new Data("Data for key 1"));
		cache.put("key2", new Data("Data for key 2"));
		cache.put("key3", new Data("Data for key 3"));
		
		
		// access data to populate LRU 
		cache.get("key1");
		cache.get("key2");
		
		// Add more data to cause LRU Eviction 
		cache.put("key4", new Data("Data for key 4"));
		
		// Attempt to retrieve evicted data 
		cache.get("key3");
		
		// Suggest GC to clear weak reference 
		System.gc();
		
		// Attempt to retrieve remaining data 
		cache.get("key1");
		cache.get("key2");
		cache.get("key4"); 
	}
}
