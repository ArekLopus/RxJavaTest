package rx.subjects_and_processors;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.Subject;
import io.reactivex.subjects.UnicastSubject;

//-If you want to support more than one Observer and just let subsequent Observers receive the live emissions without receiving
// the missed emissions, you can trick it by calling publish() to create a single Observer proxy that multicasts to more than one Observer.

public class UnicastSubjectTest2 {

	public UnicastSubjectTest2() throws InterruptedException {
		
		Subject<String> subject = UnicastSubject.create();
		
		Observable.interval(300, TimeUnit.MILLISECONDS)
		    .map(l -> ((l + 1) * 300) + " milliseconds")
		    .subscribe(subject);
		
		Thread.sleep(2000);
		
		Observable<String> multicast = subject.publish().autoConnect();	//multicast to support multiple Observers
		multicast.subscribe(s -> System.out.println("Observer 1: " + s));	//bring in first Observer
		
		Thread.sleep(2000);
		
		multicast.subscribe(s -> System.out.println("Observer 2: " + s));	//bring in second Observer
		
		Thread.sleep(1000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new UnicastSubjectTest2();

	}

}
