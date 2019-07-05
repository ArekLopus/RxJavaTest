package rx.operators.transforming;

import io.reactivex.Observable;

//-Returns an Observable that applies a specified accumulator function to the first item emitted by a sourceObservableSource, then feeds
// the result of that function along with the second item emitted by the sourceObservableSource into the same function, and so on until
// all items have been emitted by the source ObservableSource, emitting the result of each of these iterations.
//-The scan() emits the rolling accumulation for each emission, whereas reduce() yields a single emission reflecting the final accumulation
// once onComplete() is called.
//-scan does not operate by default on a particular Scheduler.
public class Scan {

	public Scan() {
		
		Observable.just(1, 2, 3, 4, 5, 6)
			.scan( (accumulator, next) -> accumulator + next)
			.subscribe(s -> System.out.println("Received: " + s));
		System.out.println();
		
		Observable.just(1, 2, 3, 4, 5, 6)
			.reduce( (accumulator, next) -> accumulator + next)
			.subscribe(s -> System.out.println("Received (reduce): " + s));
		System.out.println();
		
		Observable.just("Hi", "Hey", "Hellooo", "Johny")
			.scan( (accumulator, next) -> accumulator + " " + next)
			.subscribe(s -> System.out.println("Received: " + s));
		System.out.println();
		
		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
				.scan(0, (total, next) -> total + 1)
				.skip(1)
				.subscribe(s -> System.out.println("Received: " + s));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Scan();

	}

}
