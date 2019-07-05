package rx.observable.create;

import io.reactivex.Observable;

//-For testing, this Observable immediately calls onError() with a specified exception: 
// 	error(Callable)	- Exception is created from scratch and new exception instances are provided to each Observer
//	error(Throwable)
public class Observable_error {

	public Observable_error() throws InterruptedException {
		
		Observable.error(new Exception("Something is wrong!"))
			.subscribe(i -> System.out.println("Received: " + i), Throwable::printStackTrace, () -> System.out.println("Done!"));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Observable_error();

	}

}
