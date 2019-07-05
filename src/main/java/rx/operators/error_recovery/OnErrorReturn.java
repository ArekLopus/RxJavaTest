package rx.operators.error_recovery;

import io.reactivex.Observable;

//-Instructs an ObservableSource to emit an item (returned by a specified function) rather than invoking onError if it encounters an error.  
//-This gives you access to Throwable.
//-onErrorReturn does not operate by default on a particular Scheduler.
public class OnErrorReturn {
	
	public OnErrorReturn() {
		
		Observable.just(5, 2, 3, 0, 4, 1, 8)
	    	.map(i -> 10 / i)
	    	.onErrorReturn(e -> -1)
	    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new OnErrorReturn();

	}

}
