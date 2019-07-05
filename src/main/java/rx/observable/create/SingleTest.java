package rx.observable.create;

import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Observable;
import io.reactivex.Single;
import rx.utils.RxUtils;

//-The Single class implements the Reactive Pattern for a single value response. 
//-Single behaves similarly to Observable except that it can only emit either a single successful value or an error
// (there is no "onComplete" notification as there is for an Observable). 
//-Note that onSuccess and onError are mutually exclusive events; unlike Observable, onSuccess is never followed by onError. 

//-Does not operate by default on a particular Scheduler. 
public class SingleTest {

	public SingleTest() {
		
		Single<Boolean> source = Single.<Boolean>create(em -> {
			boolean nextBoolean = ThreadLocalRandom.current().nextBoolean();
			if(nextBoolean)
				em.onSuccess(nextBoolean);
			else
				em.onError(new IllegalArgumentException("Error: " + nextBoolean));
		});
		source.subscribe(RxUtils::consumeBooleanWithThreadPrint, System.out::println);
		
		
		Single.just("One Item")
			.subscribe(System.out::println, System.out::println);
		
		
		Observable<String> source2 = Observable.just("One","Two","Three");
		source2.first("Default Item")												 	//returns a Single
		    .subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new SingleTest();

	}

}
