package rx.subjects_and_processors;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

//-More likely, you will use Subjects to eagerly subscribe to an unknown number of multiple source Observables and consolidate their
// emissions as a single Observable. Since Subjects are an Observer, you can pass them to a subscribe() method easily. This can be helpful
// in modularized code bases where decoupling between Observables and Observers takes place and executing Observable.merge() is not that easy.
// Here, Subject is used to merge and multicast two Observable interval sources.
//-Of course, I could use Observable.merge() to accomplish this (and technically for this case, I should). But when you have modularized
// code managed through dependency injection or other decoupling mechanisms, you may not have your Observable sources prepared
// in advance to put in Observable.merge().
public class Subject_ {

	public Subject_() throws InterruptedException {
		
		Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
			.map(l -> (l + 1) + " seconds");
		
		Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
		    .map(l -> ((l + 1) * 300) + " milliseconds");
		
		Subject<String> subject = PublishSubject.create();
		
		source1.subscribe(subject);
		source2.subscribe(subject);
		subject.subscribe(System.out::println);
		
		
		Thread.sleep(3000);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Subject_();

	}

}
