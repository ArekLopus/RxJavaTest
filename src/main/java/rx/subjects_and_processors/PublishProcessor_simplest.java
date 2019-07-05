package rx.subjects_and_processors;

import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

//-Processor that multicasts all subsequently observed items to its current Subscribers. 
public class PublishProcessor_simplest {

	public PublishProcessor_simplest() {
		
		FlowableProcessor<String> subject = PublishProcessor.create();
		
		subject.map(String::length)
			.subscribe(System.out::println);
		
		subject.onNext("One");
		subject.onNext("Three");
		subject.onNext("Four");
		subject.onComplete();
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new PublishProcessor_simplest();

	}

}
