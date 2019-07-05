package rx.observable.backpr.buffering;

import io.reactivex.Observable;

//-You can also provide a skip argument that specifies how many items should be skipped before starting a new buffer.
//-If skip is equal to count, the skip has no effect. But if they are different, you can get some interesting behaviors.
//-This will essentially cause every third element to not be buffered.

//-If you make skip less than count, you can get some interesting rolling buffers.
// If you buffer items into a size of 3 but have skip of 1, you will get rolling buffers.
//-Fe, we emit the numbers 1 through 5 but create buffers [1, 2, 3], then [2, 3, 4], then [3, 4, 5], and so on.
//-You may find surprising use cases for it. Fe, you can use buffer(2,1) to emit the "previous" emission and the next emission together. 
public class Buffer_size_skipElement {

	public Buffer_size_skipElement() {
		
		Observable.range(1, 10)
	    	.buffer(2, 3)			// skip every 3rd element (no 3, 6, 9)
			.subscribe(System.out::println);
		System.out.println("");
		
		
		Observable.range(1,5)
	    	.buffer(3, 1)
	    	.subscribe(System.out::println);

		Observable.range(1,5)
	    	.buffer(2, 1)
	    	.filter(c -> c.size() == 2)
	    	.subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) {
		new Buffer_size_skipElement();

	}

}



