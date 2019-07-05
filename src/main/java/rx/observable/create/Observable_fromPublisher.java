package rx.observable.create;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import rx.utils.RxUtils;

//-Converts an arbitrary Reactive-Streams Publisher into an Observable.
//-The Publisher must follow the Reactive-Streams specification. Violating the specification may result in undefined behavior. 
//-If possible, use create(ObservableOnSubscribe) to create asource-like Observable instead. 
//-Note that even though Publisher appears to be a functional interface, it is not recommended to implement it
// through a lambda as the specification requires state management that is not achievable with a stateless lambda. 
//-Does not operate by default on a particular Scheduler. 
public class Observable_fromPublisher {

	public Observable_fromPublisher() {
		
		Flowable<String> fa = Flowable.fromArray(new String[] {"One", "Two", "Three", "Four"});
		
		Observable.fromPublisher(fa)
			.subscribe(RxUtils::consumeStringWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint, () -> System.out.println("Op Completed"));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Observable_fromPublisher();

	}

}
