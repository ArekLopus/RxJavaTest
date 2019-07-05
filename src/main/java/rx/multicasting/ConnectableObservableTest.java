package rx.multicasting;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

//-If we want to consolidate them into a single stream of data that pushes each emission to both Observers simultaneously,
// we can call publish() on Observable, which will return a ConnectableObservable. 
//-We can set up the Observers in advance and then call connect() to start firing the emissions so both 
//-Observers receive the same emissions simultaneously. This will be indicated by the printing of each Observer interleaving here:

public class ConnectableObservableTest {

	public ConnectableObservableTest() {
		
		ConnectableObservable<Integer> threeIntegers2 = Observable.range(1, 3).publish();
		
		threeIntegers2.subscribe(i -> System.out.println("Observer 1: " + i));
		threeIntegers2.subscribe(i -> System.out.println("Observer 2: " + i));
		
		threeIntegers2.connect();
		
		threeIntegers2.subscribe(i -> System.out.println("Observer 2: " + i));
		threeIntegers2.connect();
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ConnectableObservableTest();

	}

}
