package rx.operators.collection;

import java.util.HashSet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import io.reactivex.Observable;

//-Collects items emitted by the finite source ObservableSource into a single mutable data structure and returns a Single that emits this structure. 
//-collect does not operate by default on a particular Scheduler.
//-This is a simplified version of reduce that does not need to return the state on each pass. 
//-Note that this operator requires the upstream to signal onComplete for the accumulator object tobe emitted.

//-Use collect() instead of reduce() when you are putting emissions into a mutable object, and you need a new mutable object seed each time.
// We can also use collect() for trickier cases that are not straightforward collection implementations.
//-The collect() operator is helpful to collect emissions into any arbitrary type that RxJava does not provide out of the box.
public class Collect {
	
	public Collect() {
		
		Observable.just("One", "Two", "Three", "Four")
			.collect(HashSet::new, HashSet::add)
			.subscribe(s -> System.out.println("Received: " + s + ", class: " + s.getClass()));
		
		Observable.just("One", "Two", "Three", "Four")
	    	.collect(Json::createArrayBuilder, JsonArrayBuilder::add)
	    	.map(JsonArrayBuilder::build)
	    	.subscribe(s -> System.out.println("Received: " + s + ", class: " + s.getClass()));
		

		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Collect();

	}

}
