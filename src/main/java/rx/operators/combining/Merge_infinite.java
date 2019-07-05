package rx.operators.combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class Merge_infinite {

	public Merge_infinite() throws InterruptedException {
		
		Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)			//emit every second
		    .map(l -> l + 1) 															// emit elapsed seconds
		    .map(l -> "Source1: " + l + " seconds");
		Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)	//emit every 300 ms
		    .map(l -> (l + 1) * 300) 													// emit elapsed milliseconds
		    .map(l -> "Source2: " + l + " milliseconds");
		Observable.merge(source1, source2)												//merge and subscribe
		    .subscribe(System.out::println);
		
		Thread.sleep(3000);																//keep alive for 3 seconds
		

		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Merge_infinite();

	}

}
