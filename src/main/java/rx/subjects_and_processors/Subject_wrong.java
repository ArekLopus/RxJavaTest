package rx.subjects_and_processors;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

//-When Subjects go wrong
//-Since Subjects are hot, executing the onNext() calls before the Observers are set up would result in these emissions being missed
// with our Subject. If you move the onNext() calls like this, you will not get any output because the Observer will miss these emissions:
public class Subject_wrong {

	public Subject_wrong() throws InterruptedException {
		
		Subject<String> subject = PublishSubject.create();
		
		// executing the onNext() calls before the Observers are set up - these emissions are missed
		subject.onNext("One");
		subject.onNext("Three");
		subject.onNext("Four");
		
		subject
		    .subscribe(System.out::println);
		
		subject.onNext("Five");
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Subject_wrong();

	}

}
