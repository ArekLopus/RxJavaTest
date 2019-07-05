package rx.subjects_and_processors;

import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

//-ReplaySubject is similar to PublishSubject followed by a cache() operator. It immediately captures emissions regardless of the presence
// of downstream Observers and optimizes the caching to occur inside the Subject itself:

//-You need to be wary of using this with a large volume of emissions or infinite sources because it will cache them all and take up memory.

//-Replays events (in a configurable bounded or unbounded manner) to current and late Observers. 
//-ReplaySubject does not operate by default on a particular Scheduler
public class ReplaySubjectTest {

	public ReplaySubjectTest() {
		
		Subject<String> subject = ReplaySubject.create();
		
		subject.subscribe(s -> System.out.println("Observer 1: " + s));
		
		subject.onNext("One");
		subject.onNext("Two");
		subject.onNext("Three");
		subject.onComplete();
		
		subject.subscribe(s -> System.out.println("Observer 2: " + s));
				
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ReplaySubjectTest();

	}

}
