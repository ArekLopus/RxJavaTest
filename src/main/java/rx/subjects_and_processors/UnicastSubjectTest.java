package rx.subjects_and_processors;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.Subject;
import io.reactivex.subjects.UnicastSubject;

//-A UnicastSubject will buffer all the emissions it receives until an Observer subscribes to it,
// and then it will release all these emissions to the Observer and clear its cache.
//-It will only work with one Observer and will throw an error for any subsequent ones. Logically, this makes sense because it is designed
// to release buffered emissions from its internal queue once it gets an Observer.
// But when these cached emissions are released, they cannot be released again to a second Observer since they are already gone.
//-If you want a second Observer to receive missed emissions, you might as well use ReplaySubject.
// The benefit of UnicastSubject is that it clears its buffer, and consequently frees the memory used for that buffer, once it gets an Observer.

//-A Subject that queues up events until a single Observer subscribes to it, replays those events to it until the Observer catches up
// and then switches to relaying events live tothis single Observer until this UnicastSubject terminates or the Observer unsubscribes. 
//-UnicastSubject does not operate by default on a particular Scheduler
public class UnicastSubjectTest {

	public UnicastSubjectTest() throws InterruptedException {
		
		Subject<String> subject = UnicastSubject.create();
		
		Observable.interval(300, TimeUnit.MILLISECONDS)
		    .map(l -> ((l + 1) * 300) + " milliseconds")
		    .subscribe(subject);
		
		Thread.sleep(2000);
		
		subject.subscribe(s -> System.out.println("Observer 1: " + s));
		
		Thread.sleep(2000);
		
		// java.lang.IllegalStateException: Only a single observer allowed.
		//subject.subscribe(s -> System.out.println("Observer 1: " + s));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new UnicastSubjectTest();

	}

}
