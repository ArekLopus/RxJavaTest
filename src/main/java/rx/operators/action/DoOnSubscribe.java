package rx.operators.action;

import io.reactivex.Observable;

//-Modifies the source ObservableSource so that it invokes the given action when it is subscribed from its subscribers.
// Each subscription will result in an invocation of the given action except when the source ObservableSource is reference counted,
// in which case the source ObservableSource will invokethe given action for the first subscription. 
//-doOnSubscribe does not operate by default on a particular Scheduler.
public class DoOnSubscribe {
	
	public DoOnSubscribe() {
		
		Observable<String> source = Observable.just("One", "Two", "Three", "Four");
		source
			.doOnSubscribe( d -> System.out.println("Subscribed.") )
			.subscribe(s -> System.out.println("Received: " + s));
		
		source
			.doOnSubscribe( d -> System.out.println("Subscribed.") )
			.subscribe(s -> System.out.println("Received: " + s));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new DoOnSubscribe();

	}

}
