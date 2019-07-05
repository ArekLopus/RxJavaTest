package rx.operators.action;

import io.reactivex.Observable;

//-Modifies the source ObservableSource so that it invokes an action when it calls onComplete or onError. 
//-doOnTerminate does not operate by default on a particular Scheduler.
public class DoOnTerminate {
	
	public DoOnTerminate() {
		
		Observable.just(5, 2, 4, 0, 3, 2, 8)
	    	.map(i -> 10 / i)
	    	.doOnTerminate(() -> System.out.println("Finished exceptionally or not."))
	    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));	
		
				
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new DoOnTerminate();

	}

}
