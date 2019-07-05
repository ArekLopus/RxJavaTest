package rx.observable.create;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-Returns an Observable that, when an observer subscribes to it, invokes a function you specify
// and then emits the value returned from that function. 

//-This allows you to defer the execution of the function you specify until an observer subscribes to theObservableSource.
// That is to say, it makes the function "lazy." 

//-Also, if that procedure throws an error, we want it to be emitted up the Observable chain through onError()
// rather than throw the error at that location in traditional Java fashion. (Callable throws an exception -> V call() throws Exception)
//-If initializing your emission has a likelihood of throwing an error, you should use Observable.fromCallable() instead of Observable.just().

//-Does not operate by default on a particular Scheduler. 
public class Observable_fromCallable {

	public Observable_fromCallable() {
		
		Callable<String[]> c = () -> new String[] {"One", "Two", "Three", "Four"};
		Observable.fromCallable(c)
			.flatMap(e -> Observable.fromArray(e))
		    .subscribe(RxUtils::consumeStringWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint, () -> System.out.println("Op Completed"));
		
		// fromCallable()
		Observable.fromCallable(() -> 1 / 0)
	    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("Callable Error Captured: " + e));
		
		// just()
		Observable.just(1 / 0)
	    	.subscribe(i -> System.out.println("RECEIVED: " + i), e -> System.out.println("Just Error Captured: " + e));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Observable_fromCallable();

	}

}
