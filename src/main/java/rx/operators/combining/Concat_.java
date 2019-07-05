package rx.operators.combining;

import io.reactivex.Observable;

//-Concatenation is remarkably similar to merging, but with an important nuance: it will fire elements of each provided Observable sequentially
// and in the order specified. It will not move on to the next Observable until the current one calls onComplete().
//-This makes it great to ensure that merged Observables fire their emissions in a guaranteed order.
//-However, it is often a poor choice for infinite Observables, as an infinite Observable will indefinitely hold up the queue and forever
// leave subsequent Observables waiting.

//-You should prefer concatenation when you want to guarantee that Observables fire their emissions in order.
//-If you do not care about ordering, prefer merging instead. 

//-Returns an Observable that emits the items emitted by two ObservableSources, one after the other, withoutinterleaving them.  
//-concat does not operate by default on a particular Scheduler.
public class Concat_ {

	public Concat_() {
		
		//Works with different types!
		Observable<String> source1 = Observable.just("One", "Two" , "Three", "Four");
		Observable<Integer> source2 = Observable.just(1, 2, 3, 4);
		
		Observable.concat(source1, source2)
	    	.subscribe(i -> System.out.println("Received: " + i));
		

		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Concat_();

	}

}
