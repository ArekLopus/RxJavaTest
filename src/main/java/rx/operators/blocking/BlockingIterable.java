package rx.operators.blocking;

import io.reactivex.Observable;

//-Converts this Observable into an Iterable. 

//-One of the most interesting blocking operators is blockingIterable().
//-Rather than returning a single emission like our previous examples, it will provide the emissions as they become available through
// iterable<T>. The Iterator<T> provided by the Iterable<T> will keep blocking the iterating thread until the next emission is available,
// and the iteration will end when onComplete() is called.
//-The blockingIterable() will queue up unconsumed values until the Iterator is able to process them.
// This can be problematic without backpressure as you may run into OutOfMemoryException errors.
//-Unlike C#, note that Java's for-each construct will not handle cancellation, breaking, or disposal. You can work around this by iterating
// he Iterator from the iterable inside try/finally. In the finally block, cast the Iterator to a disposable so you can call its dispose().
//-The blockingIterable() can be helpful in quickly turning an Observable or Flowable into pull-driven functional sequence types such as
// a Java 8 Stream or Kotlin sequence, which can be built-off iterables. However, for Java 8 streams, you are likely better-off using 
// RxJava2Jdk8Interop library (https://github.com/akarnokd/RxJava2Jdk8Interop), so that termination is handled more safely.
public class BlockingIterable {

	public BlockingIterable() {
	    
		Observable<String> source = Observable.just("One", "Two", "Three", "Four", "Five");
	    
		Iterable<String> allWithLengthFour = source.filter(s -> s.length() == 4)
	        .blockingIterable();
	    
		for (String s: allWithLengthFour) {
			System.out.println(s);
	    }
	    
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new BlockingIterable();

	}

}
