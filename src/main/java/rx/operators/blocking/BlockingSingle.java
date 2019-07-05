package rx.operators.blocking;

import io.reactivex.Observable;

//-If this Observable completes after emitting a single item, return that item, otherwise throw a NoSuchElementException.
//-blockingSingle() is a similar blocking operator to blockingFirst(),
// which expects only a single item to be emitted, but throws an error if there are more.
//-Exception if more than 1 element:
//	java.lang.IllegalArgumentException: Sequence contains more than one element!
public class BlockingSingle {

	public BlockingSingle() {
	    
		Observable<String> source = Observable.just("One");
	    String isSingle = source
	    	.blockingSingle();
	    
	    System.out.println(isSingle.equals("One"));
	    
	    
	    System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new BlockingSingle();

	}

}
