package rx.operators.error_recovery;

import io.reactivex.Observable;

//-Instructs an ObservableSource to emit an item rather than invoking onError if it encounters an error. 
//-You can use this to prevent errors from propagating or to supply fallback data should errors be encountered. 
//-onErrorReturnItem does not operate by default on a particular Scheduler.
public class OnErrorReturnItem {
	
	public OnErrorReturnItem() {
		
		Observable.just(5, 2, 3, 0, 4, 1, 8)
	    	.map(i -> 10 / i)
	    	.onErrorReturnItem(-1)
	    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new OnErrorReturnItem();

	}

}
