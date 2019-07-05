package rx.operators.combining.flatmap;

import java.time.LocalDate;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-Takes each emission and mappes it to an Observable. Then, it merges the emissions from the resulting Observables into a single stream.

//-Applies the given io.reactivex.functions.Function to each item emitted by a reactive source,
// where that function returns a reactive source, and emits the items that result from merging the results of these function applications.
//-Returns an Observable that emits items based on applying a function that you supply to each item emitted by the source ObservableSource,
// where that function returns an ObservableSource, and then merging those resultingObservableSources and emitting the results of this merger. 
//-flatMap does not operate by default on a particular Scheduler.

//-map transform one event to another. flatMap transform one event to zero or more events.
//-FlatMap behaves very much like map, the difference is that the function it applies returns an observable itself
public class FlatMap_2 {

	public FlatMap_2() throws InterruptedException {
		
		Observable.just("1918-11-11", "1941-12-07", "1945-05-07")
			.flatMap(s -> Observable.just(s).map(LocalDate::parse))
			.subscribe(s -> System.out.println(s + ", class: " + s.getClass() + ", thread: " + Thread.currentThread().getName()));
		System.out.println();
		
		Observable.just("1918-11-11", "1941-12-07", "1945-05-07")
			.flatMap(s -> Observable.just(s)
					 				.subscribeOn(Schedulers.computation())
					 				.map(LocalDate::parse)
			)
			.subscribe(s -> System.out.println(s + ", class: " + s.getClass() + ", thread: " + Thread.currentThread().getName()));
		
		
		Thread.sleep(200);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new FlatMap_2();

	}

}
