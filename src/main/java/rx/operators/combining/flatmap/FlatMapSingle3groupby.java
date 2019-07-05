package rx.operators.combining.flatmap;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

//-A powerful operation that allows you to group emissions by a specified key into separate Observables.
//-This can be achieved by calling the groupBy() operator, which accepts a lambda mapping each emission to a key.
//-It will return an Observable<GroupedObservable<K,T>>, which emits a special type of Observable called GroupedObservable.
//-GroupedObservable<K,T> is just like any other Observable, but it has the key K value accessible as a property.
//-It will emit the T emissions that are mapped for that given key.

//-Returns an Observable that emits the items emitted by two ObservableSources, one after the other, withoutinterleaving them.  
//-concat does not operate by default on a particular Scheduler.
public class FlatMapSingle3groupby {

	public FlatMapSingle3groupby() {
		
		// Groups emissions by each String's length.
		Observable<String> source = Observable.just("One", "Two" , "Three", "Four", "Five");
		Observable<GroupedObservable<Integer,String>> byLengths = source.groupBy(s -> s.length());
		
		
		byLengths
			.flatMapSingle(grp -> grp.toList())
	    	.subscribe(i -> System.out.println("Received: " + i));
		System.out.println();
		
		
		byLengths.flatMapSingle(grp -> grp
										.reduce("",(x,y) -> x.equals("") ? y : x + ", " + y)
										.map(s -> grp.getKey() + ": " + s))
		    							.subscribe(System.out::println);
		

		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new FlatMapSingle3groupby();

	}

}
