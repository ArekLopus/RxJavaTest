package rx.observable.create;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-Converts an Array into an ObservableSource that emits the items in the Array. 
//-Does not operate by default on a particular Scheduler. 
public class Observable_fromArray {

	public Observable_fromArray() {
		
		Observable.fromArray(new String[] {"One", "Two", "Three", "Four"})
		    .subscribe(RxUtils::consumeStringWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint, () -> System.out.println("Op Completed"));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Observable_fromArray();

	}

}
