package rx.subjects_and_processors;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

//-It behaves almost the same way as PublishSubject, but it will replay the last emitted item to each new Observer downstream.
//-This is somewhat like putting replay(1).autoConnect() after a PublishSubject, but it consolidates these operations into
// a single optimized Subject implementation that subscribes eagerly to the source.

//-Subject that emits the most recent item it has observed and all subsequent observed items to each subscribed Observer.
//-BehaviorSubject does not operate by default on a particular Scheduler
public class BehaviorSubjectTest {

	public BehaviorSubjectTest() {
		
		Subject<String> subject = BehaviorSubject.create();
		
		subject.subscribe(s -> System.out.println("Observer 1: " + s));
		
		subject.onNext("One");
		subject.onNext("Two");
		subject.onNext("Three");
		
		subject.subscribe(s -> System.out.println("Observer 2: " + s));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new BehaviorSubjectTest();

	}

}
