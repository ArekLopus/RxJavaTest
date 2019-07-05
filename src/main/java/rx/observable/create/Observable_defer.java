package rx.observable.create;

import io.reactivex.Observable;

//-Observable.defer() creates a separate state for each Observer.
//-You can create a fresh Observable for each subscription using Observable.defer(),
// which accepts a lambda instructing how to create an Observable for every subscription.
//-Returns an Observable that calls an ObservableSource factory to create an ObservableSource for each new Observer that subscribes.
// That is, for each subscriber, the actual ObservableSource that subscriber observes isdetermined by the factory function. 

public class Observable_defer {
	
	private int start = 1;
	private int count = 5;
	
	public Observable_defer() throws InterruptedException {
		
		Observable<Integer> source = Observable.defer(() -> Observable.range(start,count));
		source.subscribe(i -> System.out.println("Observer 1: " + i + ", thread: " + Thread.currentThread().getName()));

		//modify count
		count = 2;
		source.subscribe(i -> System.out.println("Observer 2: " + i + ", thread: " + Thread.currentThread().getName()));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Observable_defer();

	}

}
