package rx.operators.combining;

import io.reactivex.Observable;

//-Zipping allows you to take an emission from each Observable source and combine it into a single emission. 
//-Each Observable can emit a different type, but you can combine these different emitted types into a single emission. 

//-An emission from one Observable must wait to get paired with an emission from the other Observable. If one Observable calls onComplete()
// and the other still has emissions waiting to get paired, those emissions will simply drop, since they have nothing to couple with.

//-Returns an Observable that emits the results of a specified combiner function applied to combinations of two items emitted,
// in sequence, by two other ObservableSources.  
//-zip does not operate by default on a particular Scheduler.
public class Zip {

	public Zip() throws InterruptedException {
		
		Observable<String> source1 = Observable.just("One", "Two", "Three", "Four", "Five");
		Observable<Integer> source2 = Observable.range(1,6); 
		
		Observable.zip(source1, source2, (s1, s2) -> s1 + "-" + s2)
		    .subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Zip();

	}

}
