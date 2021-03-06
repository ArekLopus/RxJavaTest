package rx.operators.blocking;

import io.reactivex.Observable;

//-Returns the last item emitted by this Observable, or throws NoSuchElementException if this Observable emits no items. 
//-If there is blockingFirst(), it only makes sense to have blockingLast().
//-This will block and return the last value to be emitted from an Observable or Flowable operation.
//-Of course, it will not return anything until onComplete() is called, so this is something you will avoid using with infinite sources.
//-Just like blockingFirst(), blockingLast() will throw an error if no emissions occur, but you can specify an overload for a default value.
public class BlockingLast {

	public BlockingLast() {
	    
		Observable<String> source = Observable.just("One", "Two", "Three", "Four", "Five");
	    String lastWithLengthFour = source.filter(s -> s.length() == 4)
	        .blockingLast();
	    
	    System.out.println(lastWithLengthFour.equals("Five"));
	    
	    
	    System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new BlockingLast();

	}
}
