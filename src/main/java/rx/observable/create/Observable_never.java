package rx.observable.create;

import io.reactivex.Observable;

//-It never calls onComplete(), forever leaving observers waiting for emissions but never actually giving any.
//-This Observable is primarily used for testing and not that often in production. 
public class Observable_never {

	public Observable_never() throws InterruptedException {
		
		Observable<String> empty = Observable.never();
		empty.subscribe(System.out::println, Throwable::getMessage, () -> System.out.println("Done!"));
		Thread.sleep(1000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Observable_never();

	}

}
