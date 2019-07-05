package rx.observable.create;

import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import rx.utils.RxUtils;

//-The Maybe class represents a deferred computation and emission of a single value, no value at all or an exception. 
//-Note that onSuccess, onError and onComplete are mutually exclusive events; unlike Observable,
// onSuccess is never followed by onError or onComplete.

//-Does not operate by default on a particular Scheduler. 
public class MaybeTest {

	public MaybeTest() {
		
		Maybe<Boolean> source = Maybe.<Boolean>create(em -> {
			boolean nextBoolean = ThreadLocalRandom.current().nextBoolean();
			if(nextBoolean)
				em.onSuccess(nextBoolean);
			else
				em.onComplete();
		});
		source.subscribe(RxUtils::consumeBooleanWithThreadPrint, System.out::println, () -> System.out.println("Was False, Done!"));
		
		
		Maybe.just("One Item")
			.subscribe(System.out::println, System.out::println);
		
		
		Maybe.empty()
			.subscribe(System.out::println, System.out::println, () -> System.out.println("Empty, Done!"));
		
		//firstElement() operator is similar to first(), but it returns an empty result if no elements are emitted:
		//Observable<String> source2 = Observable.just("One", "Two", "Three", "Four", "Five");
		Observable<String> source2 = Observable.empty();
			source2.firstElement().subscribe(s -> System.out.println("Received: " + s),
				System.out::println, () -> System.out.println("No First Element, Done!"));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new MaybeTest();

	}

}
