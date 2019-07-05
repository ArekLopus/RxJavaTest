package rx.observable.create;

import java.util.concurrent.CompletableFuture;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-Converts a Future into an ObservableSource. 
//-You can convert any object that supports the Future interface into an ObservableSource that emits the return value of the Future.get()
// of that object, by passing the object into the from method. 
//-Important note: This ObservableSource is blocking; you cannot dispose it. 
//-Unlike 1.x, disposing the Observable won't cancel the future. If necessary, one can use composition to achieve the cancellation effect:
// futureObservableSource.doOnDispose(() -> future.cancel(true));. 
//-Does not operate by default on a particular Scheduler. 
public class Observable_fromFuture {

	public Observable_fromFuture() {
		
		CompletableFuture<String[]> cf = CompletableFuture.supplyAsync(() -> new String[] {"One", "Two", "Three", "Four"}); 
		
		Observable.fromFuture(cf) 
			.flatMap(e -> Observable.fromArray(e))
			.subscribe(RxUtils::consumeStringWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint, () -> System.out.println("Op Completed"));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Observable_fromFuture();

	}

}
