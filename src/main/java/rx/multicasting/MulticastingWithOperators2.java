package rx.multicasting;

import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

//-It is not necessary to multicast when there is only a single Observer (and multicasting can cause unnecessary overhead).
// But if there are multiple Observers, you need to find the proxy point where you can multicast and consolidate the upstream operations.
// This point is typically the boundary where Observers have common operations upstream and diverge into different operations downstream.
//-For instance, you may have one Observer that prints the random integers but another one that finds the sum with reduce().
// At this point, that single stream should, in fact, fork into two separate streams because they are no longer redundant
// and doing different work, as shown in the following code snippet:
public class MulticastingWithOperators2 {

	public MulticastingWithOperators2() {
		
		ConnectableObservable<Integer> threeRandoms = Observable.range(1,3)
			.map(i -> ThreadLocalRandom.current()
			.nextInt(100000))
			.publish();
		
		threeRandoms.subscribe(i -> System.out.println("Observer 1: " + i));	//Observer 1 - print each random integer
		threeRandoms.reduce(0, (total,next) -> total + next)					//Observer 2 - sum the random ints, then print
			.subscribe(i -> System.out.println("Observer 2: " + i));
		
		threeRandoms.connect();
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new MulticastingWithOperators2();

	}

}
