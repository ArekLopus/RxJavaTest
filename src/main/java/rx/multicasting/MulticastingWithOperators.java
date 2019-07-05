package rx.multicasting;

import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

//-Observable.range() source will yield two separate emission generators, and each will coldly emit a separate stream for each Observer.
//-Each stream also has its own separate map() instance, hence each Observer gets different random integers.
public class MulticastingWithOperators {

	public MulticastingWithOperators() {
		
		Observable<Integer> threeRandoms = Observable.range(1,3)
		    .map(i -> ThreadLocalRandom.current().nextInt(100000));
		
		threeRandoms.subscribe(i -> System.out.println("Observer 1: " + i));
		threeRandoms.subscribe(i -> System.out.println("Observer 2: " + i));
		System.out.println();
		
		// It still wont work. Everything before publish() was consolidated into a single stream.
		// But after publish(), it will fork into separate streams for each Observer again,
		ConnectableObservable<Integer> threeInts2 = Observable.range(1,3).publish();
		Observable<Integer> threeRandoms2 = threeInts2.map(i -> ThreadLocalRandom.current().nextInt(100000));
		
		threeRandoms2.subscribe(i -> System.out.println("Observer 1:	" + i));
		threeRandoms2.subscribe(i -> System.out.println("Observer 2:	" + i));
		threeInts2.connect();
		System.out.println();
		
		// If we want to prevent the map() operator from yielding two separate streams for each Observer,
		// we need to call publish() after map() instead.
		ConnectableObservable<Integer> threeInts3 = Observable.range(1,3)
				.map(i -> ThreadLocalRandom.current().nextInt(100000))
				.publish();
		
		threeInts3.subscribe(i -> System.out.println("Observer 1:	" + i));
		threeInts3.subscribe(i -> System.out.println("Observer 2:	" + i));
		threeInts3.connect();
		
		//But after another connect we get a new emission.
		threeInts3.subscribe(i -> System.out.println("Observer 2:	" + i));
		threeInts3.connect();
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new MulticastingWithOperators();

	}

}
