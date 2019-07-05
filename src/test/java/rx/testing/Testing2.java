package rx.testing;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;


public class Testing2 {

	@Test
	public void test() {
	    
		TestObserver<String> testObserver = new TestObserver<>();
	    
		Observable<String> items = Observable.just("521934/2342/Foxtrot", "Bravo/12112/78886/Tango", "283242/4542/Whiskey/2348562");
	    
		items
			.concatMap(s -> Observable.fromArray(s.split("/")))
			.doOnNext(s -> System.out.println("Source pushed: " + s))
			.filter(s -> s.matches("[A-Za-z]+"))
	    	.doOnNext(s -> System.out.println("Source pushed: " + s))
	    	.subscribe(testObserver);
	    
		//System.out.println(testObserver.values());
	    testObserver.assertValues("Foxtrot","Bravo","Tango","Whiskey");
	}

}
