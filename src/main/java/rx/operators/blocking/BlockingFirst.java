package rx.operators.blocking;

import io.reactivex.Observable;

//-Returns the first item emitted by this Observable, or throws NoSuchElementException if it emits no items. 
//-blockingFirst() operator will stop the calling thread and make it wait for the first value to be emitted and returned
// (even if the chain is operating on a different thread with observeOn() and subscribeOn()).
//-Note that the blockingFirst() operator will throw an error and fail the test if no emissions come through.
// However, you can provide a default value as an overload to blockingFirst() so it always has a value to fall back on.
//-Maybe and Single do not have blockingFirst() since there can only be one element at most.
// Logically, for a Single and Maybe, it is rather the only element, so the equivalent operator is blockingGet().
public class BlockingFirst {

	public BlockingFirst() {
	    
		Observable<String> source = Observable.just("One", "Two", "Three", "Four", "Five");
	    String firstWithLengthFour = source.filter(s -> s.length() == 4)
	        .blockingFirst();
	    
	    System.out.println(firstWithLengthFour.equals("Four"));
	    
	    
	    System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new BlockingFirst();

	}
}
