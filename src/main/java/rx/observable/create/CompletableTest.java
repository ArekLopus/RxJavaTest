package rx.observable.create;

import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Completable;

//-The Completable class represents a deferred computation without any value but only indication for completion or exception. 
//-Completable behaves similarly to Observable except that it can only emit either a completion or error signal
// (there is no onNext or onSuccess as with the other reactive types). 
//-Note that as with the Observable protocol, onError and onComplete are mutually exclusive events. 

//-Does not operate by default on a particular Scheduler. 
public class CompletableTest {

	public CompletableTest() {
		
		Completable source = Completable.fromCallable(() -> {
			boolean nextBoolean = ThreadLocalRandom.current().nextBoolean();
			if(nextBoolean)
				return "Finished";
			else
				throw new IllegalArgumentException("Error");
		});
		
		source.subscribe(() -> System.out.println("Done."), System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new CompletableTest();

	}

}
