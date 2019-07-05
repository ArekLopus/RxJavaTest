package rx.observable.create;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-Up to 10 items
//-Does not operate by default on a particular Scheduler. 
public class Observable_just {

	public Observable_just() {
		
		Observable.just("One", "Two", "Three", "Four")
		    .subscribe(RxUtils::consumeStringWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint, () -> System.out.println("Op Completed"));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Observable_just();

	}

}
