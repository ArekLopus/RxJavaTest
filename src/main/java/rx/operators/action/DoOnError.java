package rx.operators.action;

import io.reactivex.Observable;

//-Modifies the source ObservableSource so that it invokes an action if it calls onError. 
//-It can help deduct where an error occurrs.
//-doOnError does not operate by default on a particular Scheduler.
public class DoOnError {
	
	public DoOnError() {
		
		Observable.just(5, 2, 4, 0, 3, 2, 8)
	    	.doOnError(e -> System.out.println("Source failed!"))
	    	.map(i -> 10 / i)
	    	.doOnError(e -> System.out.println("Division failed!"))
	    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));	
		
				
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new DoOnError();

	}

}
