package rx.operators.action;

import io.reactivex.Observable;

//-Modifies the source ObservableSource so that it invokes an action for each item it emits. 
//-It is like putting subscribe() right in the middle of your Observable chain.
//-doOnEach does not operate by default on a particular Scheduler.
public class DoOnEach {
	
	public DoOnEach() {
		
		Observable.just(5, 2, 4, 0, 3, 2, 8)
	    	.map(i -> 10 / i)
	    	.doOnEach(e -> System.out.println("doOnEach, el: " + e))
	    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));	
		
			
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new DoOnEach();

	}

}
