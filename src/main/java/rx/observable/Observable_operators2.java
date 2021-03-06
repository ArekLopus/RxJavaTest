package rx.observable;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-Since operators such as map() and filter() yield new Observables (which internally use Observer implementations to receive emissions),
// we can chain all our returned Observables with the next operator rather than unnecessarily saving each one to an intermediary variable:
public class Observable_operators2 {

	public Observable_operators2() {
		
		List<String> list = Arrays.asList("One", "Two", "Three", "Four");
		Observable<String> source = Observable.fromIterable(list);
		
		Observable<Integer> lengths = source.map(String::length);
		Observable<Integer> filtered = lengths.filter(i -> i >= 4);
		filtered.subscribe(RxUtils::consumeNumberWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint, () -> System.out.println("Op Completed"));
		
		
		source
		    .map(String::length)
		    .filter(i -> i >= 5)
		    .subscribe(RxUtils::consumeNumberWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint, () -> System.out.println("Op Completed 2"));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Observable_operators2();

	}

}
