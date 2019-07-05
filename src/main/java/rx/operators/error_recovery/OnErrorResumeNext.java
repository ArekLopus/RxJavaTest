package rx.operators.error_recovery;

import io.reactivex.Observable;

//-Instructs an ObservableSource to pass control to another ObservableSource rather than invoking onError if it encounters an error. 
//-onErrorResumeNext does not operate by default on a particular Scheduler.
public class OnErrorResumeNext {
	
	public OnErrorResumeNext() {
		
		Observable.just(5, 2, 3, 0, 4, 1, 8)
	    	.map(i -> 10 / i)
	    	.onErrorResumeNext(Observable.just(-1))
	    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));
		System.out.println();
		
		Observable.just(5, 2, 3, 0, 4, 1, 8)
    		.map(i -> 10 / i)
    		.onErrorResumeNext((Throwable e) -> Observable.just(-1))
    		.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));
		System.out.println();
		
		Observable.just(5, 2, 3, 0, 4, 1, 8)
	    	.map(i -> 10 / i)
	    	.onErrorResumeNext(Observable.empty())
	    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e), ()-> System.out.println("Finished"));
		
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new OnErrorResumeNext();

	}

}
