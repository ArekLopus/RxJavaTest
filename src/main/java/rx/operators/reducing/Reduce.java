package rx.operators.reducing;

import io.reactivex.Observable;

//-Returns a Maybe that applies a specified accumulator function to the first item emitted by a sourceObservableSource, then feeds the result
// of that function along with the second item emitted by the sourceObservableSource into the same function, and so on until all items
// have been emitted by the finite source ObservableSource,and emits the final result from the final call to your function as its sole item. 
//-The scan() emits the rolling accumulation for each emission, whereas reduce() yields a single emission reflecting the final accumulation
// once onComplete() is called.
//-reduce does not operate by default on a particular Scheduler.
public class Reduce {

	public Reduce() {
		
		Observable.just(1, 2, 3, 4, 5, 6)
			.reduce( (accumulator, next) -> accumulator + next)
			.subscribe(s -> System.out.println("Received: " + s));
		System.out.println();
		
		Observable.just(1, 2, 3, 4, 5, 6)
			.reduce(100, (accumulator, next) -> accumulator + next)
			.subscribe(s -> System.out.println("Received: " + s));
		System.out.println();
		
		Observable.just(5, 3, 7, 10, 2, 14)
			.reduce("", (total, next) -> total + (total.equals("") ? "" : ",") + next)
			.subscribe(s -> System.out.println("Received: " + s));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Reduce();

	}

}
