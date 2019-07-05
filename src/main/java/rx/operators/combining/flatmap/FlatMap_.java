package rx.operators.combining.flatmap;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//	flatMap(Function<? super Observable<String>, ? extends ObservableSource<? extends String>> mapper)
//-Function takes Observable<String>, returns ObservableSource<String>

//-The flatMap operator converts each item returned from a source observable into an independent observable using the supplied function
// and then merges all the observables into a single observable. 
//-It basically merges an observable sequence of observable sequences into a single observable sequence.
//-We need a Observable<Observable<T>> as input or map emits into Observable<T> in flatMap().
public class FlatMap_ {

	public FlatMap_() throws InterruptedException {
		
		
		List<String> list = Arrays.asList("One", "Two", "Three", "Four");
		
		Observable<String> source = Observable.fromIterable(list);
		source
			.subscribe(i -> System.out.println("Received: " + i));
		System.out.println();
		
		Observable<Observable<String>> mapped = source.map(e -> Observable.just(e + " mapped"));
		mapped
			.subscribe(i -> System.out.println("Received: " + i));
		System.out.println();
		
		
		Observable<Observable<String>> mappedFlat = source.map(e -> Observable.just(e + " flatMapped 1"));
		mappedFlat
			.flatMap(e -> e)
			.subscribe(i -> System.out.println("Received: " + i));
		System.out.println();
		
		Observable.fromIterable(list)
			.observeOn(Schedulers.computation())
			.flatMap(e -> Observable.just(e + " flatMaped 2, thread" + Thread.currentThread().getName()))
			.subscribe(i -> System.out.println("Received: " + i));
		System.out.println();
		
		Thread.sleep(100);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new FlatMap_();

	}

}
