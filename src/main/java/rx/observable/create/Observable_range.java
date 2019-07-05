package rx.observable.create;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-Returns an Observable that emits a sequence of Integers within a specified range.
//-The two arguments for Observable.range() are not lower/upper bounds.
// The first argument is the starting value. The second argument is the total count of emissions,

//There is Observable.rangeLong() if you need to emit larger numbers.

//-Does not operate by default on a particular Scheduler. 
public class Observable_range {

	public Observable_range() {
		
		Observable.range(5, 10)
		    .subscribe(RxUtils::consumeNumberWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint, () -> System.out.println("Op Completed"));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Observable_range();

	}

}
