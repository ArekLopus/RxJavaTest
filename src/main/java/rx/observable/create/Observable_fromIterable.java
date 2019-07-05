package rx.observable.create;

import java.util.Arrays;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-Converts an Iterable sequence into an ObservableSource that emits the items in the sequence. 
//-Does not operate by default on a particular Scheduler. 
public class Observable_fromIterable {

	public Observable_fromIterable() {
		
		Observable.fromIterable(Arrays.asList("One", "Two", "Three", "Four"))
		    .subscribe(RxUtils::consumeStringWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint, () -> System.out.println("Op Completed"));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Observable_fromIterable();

	}

}
