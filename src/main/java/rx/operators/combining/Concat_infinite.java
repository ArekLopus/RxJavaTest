package rx.operators.combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Observable.concat() with infinite Observables will forever emit from the first one it encounters and prevent
// any following Observables from firing.
//-If we ever want to put an infinite Observable anywhere in a concatenation operation, it would likely be specified last.
// This ensures that it does not hold up any Observables following it because there are none.
//-We can also use take() operators to make infinite Observables finite.
public class Concat_infinite {

	public Concat_infinite() throws InterruptedException {
		
		Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)    		//emit every sec, but only take 2 emissions
		    .take(2)
		    .map(l -> l + 1) 															// emit elapsed seconds
		    .map(l -> "Source1: " + l + " seconds");
		
		Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)	//emit every 300 ms
		    .map(l -> (l + 1) * 300) 													// emit elapsed milliseconds
		    .map(l -> "Source2: " + l + " milliseconds");
		
		Observable.concat(source1, source2)
		    .subscribe(i -> System.out.println("Received: " + i));
		
		Thread.sleep(5000);																//keep app alive for 5 secs
		

		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Concat_infinite();

	}

}
