package rx.multicasting;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

//-Multicasting is helpful in preventing redundant work being done by multiple Observers and instead makes all Observers subscribe
// to a single stream, at least to the point where they have operations in common. 
//-You may do this to increase performance, reducing memory and CPU usage, or simply because your
// business logic requires pushing the same emissions to all Observers.
//-Data-driven cold Observables should only be multicast when you are doing so for performance reasons and have multiple Observers receiving
// the same data simultaneously. Remember that multicasting creates hot ConnectableObservables,
// and you have to be careful and time the connect() call so data is not missed by Observers.
//-Typically in your API, keep your cold Observables cold and call publish() when you need to make them hot.

//-It is not necessary to multicast when there is only a single Observer (and multicasting can cause unnecessary overhead).
// But if there are multiple Observers, you need to find the proxy point where you can multicast and consolidate the upstream operations.
// This point is typically the boundary where Observers have common operations upstream and diverge into different operations downstream.

//-Here, Observer 1 received all three emissions and called onComplete(). After that, Observer 2 received the three emissions (regenerated again)
// and called onComplete(). These were two separate streams of data generated for two separate. subscriptions.

//-If we want to consolidate them into a single stream of data that pushes each emission to both Observers simultaneously,
// we can call publish() on Observable, which will return a ConnectableObservable. 
//-We can set up the Observers in advance and then call connect() to start firing the emissions so both 
//-Observers receive the same emissions simultaneously. This will be indicated by the printing of each Observer interleaving here:

public class ABasics {

	public ABasics() {
		
		Observable<Integer> threeIntegers = Observable.range(1,3);
		
		threeIntegers.subscribe(i -> System.out.println("Observer 1: " + i));
		threeIntegers.subscribe(i -> System.out.println("Observer 2: " + i));
		System.out.println();
		
		
		ConnectableObservable<Integer> threeIntegers2 = Observable.range(1, 3).publish();
		
		threeIntegers2.subscribe(i -> System.out.println("Observer One: " + i));
		threeIntegers2.subscribe(i -> System.out.println("Observer Two: " + i));
		threeIntegers2.connect();
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ABasics();

	}

}
