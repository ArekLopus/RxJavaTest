package rx.concurrency;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-By default, Obsrvables are pushing items synchronously and one at a time from the source all the way to the Observer.
//-It is how Observable chains work by default without any concurrency.
public class Concurrent_off {

	public Concurrent_off() throws InterruptedException {
		
		Observable.just("One", "Two", "Three")
	    	.map(s -> RxUtils.hardWork(s.length() * 100))
	    	.subscribe(System.out::println);
		System.out.println("First Observable finished.");
		
		Observable.range(1,6)
	    	.map(s -> RxUtils.hardWork(s * 100))
	    	.subscribe(System.out::println);
		System.out.println("Second Observable finished.");
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Concurrent_off();

	}

}



