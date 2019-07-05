package rx.operators.blocking;

import io.reactivex.Observable;

//-Consumes the upstream Observable in a blocking fashion and invokes the given Consumer with each upstream item
// on the current thread until the upstream terminates.
//-A more fluent way to block for each task is to use the blockingForEach() operator instead of blockingIterable().
//-This will block the declaring thread and wait for each emission to be processed before allowing the thread to continue.
public class BlockingForEach {

	public BlockingForEach() {
	    
		Observable<String> source = Observable.just("One", "Two", "Three", "Four", "Five");
		
		source.filter(s -> s.length() == 4)
        	.blockingForEach(s -> System.out.println(s));
	    
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new BlockingForEach();

	}
}
