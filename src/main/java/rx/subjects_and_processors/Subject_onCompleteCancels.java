package rx.subjects_and_processors;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

//-The first Observable to call onComplete() on Subject is going to cease other Observables from pushing their emissions,
// and downstream cancellation requests are ignored. This means that you will most likely use Subjects for infinite, event-driven
// (that is, user action-driven) Observables.
public class Subject_onCompleteCancels {

	public Subject_onCompleteCancels() throws InterruptedException {
		
		Observable<Long> source1 = Observable.timer(1, TimeUnit.SECONDS);
		Observable<Long> source2 = Observable.interval(300, TimeUnit.MILLISECONDS);
		
		Subject<Long> subject = PublishSubject.create();
		
		source1.subscribe(subject);
		source2.subscribe(subject);
		
		subject.subscribe(System.out::println, System.out::println, ()->System.out.println("Done"));
		
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Subject_onCompleteCancels();

	}

}
