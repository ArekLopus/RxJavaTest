package rx.observable.create;

import io.reactivex.Observable;

//-Empty observables are common to represent empty datasets.
//-They can result from operators such as filter() when all emissions fail to meet a condition.
//-Sometimes, you will deliberately create empty Observables using Observable.empty()
//-An empty Observable is essentially RxJava's concept of null. It is the absence of a value.
//-Empty Observables are much more elegant than nulls because operations will simply continue empty rather than throw NullPointerExceptions.
public class Observable_empty {

	public Observable_empty() throws InterruptedException {
		
		Observable<String> empty = Observable.empty();
		empty.subscribe(System.out::println, Throwable::getMessage, () -> System.out.println("Done!"));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Observable_empty();

	}

}
