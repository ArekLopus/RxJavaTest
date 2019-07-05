package rx.subjects_and_processors;

import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.Subject;

//-AsyncSubject has a highly finite-specific behavior: it will only push the last value it receives, followed by an onComplete() event.
//-This is a Subject you do not want to use with infinite sources since it only emits when onComplete() is called.
//-AsyncSubject resembles CompletableFuture as it will do a computation that you can choose to observe for completion and get the value.
//-You can also imitate AsyncSubject using takeLast(1).replay(1) on an Observable. Try to use this approach first before resorting to AsyncSubject.

//-A Subject that emits the very last value followed by a completion event or the received error to Observers. 
//-AsyncSubject does not operate by default on a particular Scheduler
public class AsyncSubjectTest {

	public AsyncSubjectTest() {
		
		Subject<String> subject = AsyncSubject.create();
		
		subject.subscribe(s -> System.out.println("Observer 1: " + s + ", thread: " + Thread.currentThread().getName()),
				Throwable::printStackTrace, () -> System.out.println("Observer 1 Done"));
		
		subject.onNext("One");
		subject.onNext("Two");
		subject.onNext("Three");
		subject.onComplete();
		
		subject.subscribe(s -> System.out.println("Observer 2: " + s + ", thread: " + Thread.currentThread().getName()),
				Throwable::printStackTrace, () -> System.out.println("Observer 2 Done"));
		
				
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new AsyncSubjectTest();

	}

}
