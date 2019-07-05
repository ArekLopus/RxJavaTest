package rx.operators.combining.flatmap;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

//-map transform one event to another. flatMap transform one event to zero or more events.
//-FlatMap behaves very much like map, the difference is that the function it applies returns an observable itself
public class FlatMapSingle2 {

	public FlatMapSingle2() throws InterruptedException {
		
		Single<List<String>> list = Observable.just("One", "Two", "Three").toList();
		
		list
			.flatMapObservable(e -> Observable
											.fromIterable(e)
											.map(s -> s.length()))
			.subscribe(i -> System.out.println("Received: " + i));
		
		
		Thread.sleep(200);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new FlatMapSingle2();

	}

}
