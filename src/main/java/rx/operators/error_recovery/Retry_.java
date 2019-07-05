package rx.operators.error_recovery;

import io.reactivex.Observable;

//-Returns an Observable that mirrors the source ObservableSource, resubscribing to it if it calls onError.
//-retry does not operate by default on a particular Scheduler.
public class Retry_ {
	
	public Retry_() {
		
		Observable.just(5, 2, 3, 0, 4, 1, 8)
	    	.map(i -> 10 / i)
	    	//.retry()	// infinite retry count
	    	.retry(1)
	    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Retry_();

	}

}
