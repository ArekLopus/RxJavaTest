package rx.operators.action;

import io.reactivex.Observable;

//-Modifies the source ObservableSource so that it invokes an action when it calls onComplete. 
//-doOnComplete does not operate by default on a particular Scheduler.
public class DoOnComplete {
	
	public DoOnComplete() {
		
		Observable.just("One", "Two", "Three", "Four")
			.doOnComplete( () -> System.out.println("Done emitting!") )
			.subscribe(s -> System.out.println("Received: " + s));
		
				
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new DoOnComplete();

	}

}
