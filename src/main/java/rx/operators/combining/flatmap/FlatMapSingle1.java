package rx.operators.combining.flatmap;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class FlatMapSingle1 {

	public FlatMapSingle1() throws InterruptedException {
		
		Observable<Long> cutOffs = Observable.interval(1, TimeUnit.SECONDS);
		
		Observable.interval(300, TimeUnit.MILLISECONDS)
		    .map(i -> (i + 1) * 300)
		    .window(cutOffs)													// returns Observable<Observable<Long>>
		    .flatMapSingle(obs -> obs.reduce("", (total, next) -> total + (total.equals("") ? "" : "-") + next)) 
		    .subscribe(System.out::println);
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new FlatMapSingle1();

	}

}



