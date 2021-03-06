package rx.operators.reducing;

import io.reactivex.Observable;

//-Returns a Single that applies a specified accumulator function to the first item emitted by a sourceObservableSource and a seed value
// derived from calling a specified seedSupplier, then feeds the result of that function along with the second item emitted by
// an ObservableSource into the same function, and so on until all items have been emitted by the finite source ObservableSource,
// emitting the final result from the final call to your function as its sole item. 

//-Note that this operator requires the upstream to signal onComplete for the accumulator object tobe emitted. 

//-reduceWith does not operate by default on a particular Scheduler.
public class ReduceWith2 {

	public ReduceWith2() {
		
		Observable.just(1, 2, 3, 4, 5)
        	.reduceWith(StringBuilder::new, (set, x) -> {
        		set.append(x + ",");
        		return set;
        	})
        	.subscribe(s -> System.out.println("Received: " + s + " class: " + s.getClass()));
		
		Observable.just(1, 2, 3, 4, 5)
    		.reduce(new StringBuilder(), (set, x) -> {
    			set.append(x + ",");
    			return set;
    		})
    		.subscribe(s -> System.out.println("Received: " + s + " class: " + s.getClass()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ReduceWith2();

	}

}
