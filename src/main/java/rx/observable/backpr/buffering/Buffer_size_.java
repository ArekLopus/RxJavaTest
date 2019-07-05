package rx.observable.backpr.buffering;

import java.util.HashSet;
import java.util.List;

import io.reactivex.Observable;

//-The buffer() operator will gather emissions within a certain scope and emit each batch as a list or another collection type.
//-The scope can be defined by a fixed buffer sizing or a timing window that cuts off at intervals or even slices by the emissions of another Observable.
//-You can also supply a second bufferSupplier lambda argument to put items in another collection besides a list, such as HashSet.
public class Buffer_size_ {

	public Buffer_size_() {
		
		Observable<List<Integer>> buffer = Observable.range(1,20)
	    	.buffer(8);
	    
		buffer
			.subscribe(System.out::println);
		System.out.println();
		
		
		Observable<HashSet<Integer>> buffer2 = Observable.range(1,20)
	    	.buffer(8, HashSet::new);
	    
		buffer2
	    	.subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) {
		new Buffer_size_();

	}

}



