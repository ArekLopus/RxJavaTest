package rx.operators.action;

import io.reactivex.Observable;

//-Modifies the source ObservableSource so that it invokes an action when it calls onNext.
//-The doOnNext() operator allows you to peek at each emission coming out of an operator and going into the next.
//-This operator does not affect the operation or transform the emissions in any way. 

//-doOnNext does not operate by default on a particular Scheduler.
public class DoOnNext {
	
	public DoOnNext() {
		
		Observable.just("One", "Two", "Three", "Four")
			.doOnNext( e -> System.out.println("doOnNext, el: " + e) )
			.subscribe(s -> System.out.println("Received: " + s));
		System.out.println();
		
				
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new DoOnNext();

	}

}
