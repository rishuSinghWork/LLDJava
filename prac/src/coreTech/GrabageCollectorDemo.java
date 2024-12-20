package coreTech;

import java.util.ArrayList;
import java.util.List;

// Object Class 
class Object {
	private boolean marked;
	private int size;
	private List<Object> references;
	
	public Object(int size) {
		this.size = size;
		this.marked = false;
		this.references = new ArrayList<>();
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public void setMarked(boolean marked) {
		this.marked = marked;
	}
	
	public List<Object> getReference(){
		return references;
	}
	
	public void addReference(Object obj) {
		references.add(obj);
	}
	
	public int getSize() {
		return size;
	}
}


// Heap Class 
class Heap{
	private List<Object> objects; // the one in use 
	private List<Object> freeList; // the memory blocks that have been freed 
	
	public Heap() {
		objects = new ArrayList<>();
		freeList = new ArrayList<>();
	}
	
	public Object allocate(int size) {
		for(Object block : freeList) {
			if(block.getSize() >= size) {
				freeList.remove(block);
				objects.add(block);
				return block;
			}
		}
		Object newObject = new Object(size);
		objects.add(newObject);
		return newObject;
	}
	
	public List<Object> getObjects(){
		return objects;
	}
	
	public void addToFreeList(Object obj) {
		freeList.add(obj);
	}
	
	public void setObjects(List<Object> newObjects) {
		objects = newObjects;
	}
}

// Garbage Collector 
class GarbageCollector{
	private Heap heap;
	
	public GarbageCollector(Heap heap) {
		this.heap = heap;
	}
	
	// mark 
	// the policy of marking is not clear yet 
	public void mark(Object root) {
		if(root == null || root.isMarked())
			return;
		root.setMarked(true);
		for(Object ref : root.getReference())
			mark(ref);
		//System.out.println("Marking done of " + root);
	}
	
	// sweep 
	public void sweep() {
		List<Object> newObjects = new ArrayList<>();
		for(Object obj : heap.getObjects()) {
			if(!obj.isMarked())
				heap.addToFreeList(obj);
			else {
				obj.setMarked(false);
				newObjects.add(obj);
			}
		}
		heap.setObjects(newObjects);
		//System.out.println("Sweep done");
	}
	
	// collect
	public void collect(List<Object> roots) {
		// mark
		for(Object root : roots)
			mark(root);
		// sweep
		sweep();
	}
}

public class GrabageCollectorDemo {
	public static void main(String[] args) {
		Heap heap = new Heap();
		GarbageCollector gc = new GarbageCollector(heap);
		
		// obj allocation 
		Object root1 = heap.allocate(10);
		Object root2 = heap.allocate(20);
		Object child1 = heap.allocate(5);
		
		// create references
		root1.addReference(child1);
		
		// perform gc 
		List<Object> roots = new ArrayList<>();
		roots.add(root1);
		roots.add(root2);
		gc.collect(roots);
	}
}


