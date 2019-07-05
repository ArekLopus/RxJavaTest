package rx.operators.action;

import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Maybe;
import rx.utils.RxUtils;

//-Calls the shared consumer with the success value sent via onSuccess for eachMaybeObserver that subscribes to the current Maybe. 
//-Its usage is like doOnNext():
//-doOnSuccess does not operate by default on a particular Scheduler.
public class DoOnSuccess {

	public DoOnSuccess() {
		
		Maybe<Boolean> source = Maybe.<Boolean>create(em -> {
			boolean nextBoolean = ThreadLocalRandom.current().nextBoolean();
			if(nextBoolean)
				em.onSuccess(nextBoolean);
			else
				em.onComplete();
		});
		
		source
			.doOnSuccess( e -> System.out.println("Operation Successful, el: " + e) )
			.subscribe(RxUtils::consumeBooleanWithThreadPrint, System.out::println, () -> System.out.println("Was False, Done!"));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new DoOnSuccess();

	}

}
