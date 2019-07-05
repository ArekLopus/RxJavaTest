package rx.subjects_and_processors;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

//-The simplest Subject type is the PublishSubject, which, like all Subjects, hotly broadcasts to its downstream Observers.
//-Other Subject types add more behaviors, but PublishSubject is the "vanilla" type, if you will.
public class PublishSubject_ {

	public PublishSubject_() {
		
		Subject<String> subject = PublishSubject.create();
		
		subject.map(String::length)
			.subscribe(System.out::println);
		
		subject.onNext("One");
		subject.onNext("Three");
		subject.onNext("Four");
		subject.onComplete();
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new PublishSubject_();

	}

}
