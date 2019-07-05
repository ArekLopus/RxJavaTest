package rx.operators.combining.flatmap;

import io.reactivex.Observable;

//	flatMap(Function<? super String, ? extends ObservableSource<? extends String>> mapper,
//			BiFunction<? super String, ? super String, ? extends String> resultSelector)

//-Returns an Observable that emits the results of a specified function to the pair of values emitted by the source ObservableSource
// and a specified collection ObservableSource.
public class FlatMap_3 {

	public FlatMap_3() {
		
		Observable<String> source = Observable.just("One", "Two", "Three", "Four");
		
		source
			.flatMap(s -> Observable.fromArray(s.split("")),   (s,r) -> s + "-" + r)
		    .subscribe(System.out::println);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new FlatMap_3();

	}

}
