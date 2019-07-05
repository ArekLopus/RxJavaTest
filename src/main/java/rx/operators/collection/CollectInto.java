package rx.operators.collection;

import io.reactivex.Observable;

//-Collects items emitted by the finite source ObservableSource into a single mutable data structure and returnsa Single that emits this structure.  
//-This is a simplified version of reduce that does not need to return the state on each pass. 
//-Note that this operator requires the upstream to signal onComplete for the accumulator object tobe emitted.
//-collectInto does not operate by default on a particular Scheduler.

public class CollectInto {
	
	public CollectInto() {
		
		Observable.just('R', 'x', 'J', 'a', 'v', 'a')
        	.collectInto(new StringBuilder(), StringBuilder::append)	// 1st arg is an existing data structure.
        	.map(StringBuilder::toString)
        	.subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new CollectInto();

	}

}
