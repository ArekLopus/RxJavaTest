package rx.subjects_and_processors;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

//-The simplest Subject type is the PublishSubject, which, like all Subjects, hotly broadcasts to its downstream Observers.
//-Other Subject types add more behaviors, but PublishSubject is the "vanilla" type, if you will.
public class PublishSubject_observeOn {

	public PublishSubject_observeOn() throws InterruptedException {
		
		Subject<String> subject = PublishSubject.create();
		
		subject.map(String::length)
			.observeOn(Schedulers.computation())
			.doOnNext( e-> System.out.println(e + ", thread, " + Thread.currentThread().getName()) )
			.subscribe(e -> {});
		
		subject.onNext("One");
		subject.onNext("Three");
		subject.onNext("Four");
		subject.onComplete();
		
		Thread.sleep(50);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new PublishSubject_observeOn();

	}

}
