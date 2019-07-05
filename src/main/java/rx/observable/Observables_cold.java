package rx.observable;

import java.util.Arrays;

import io.reactivex.Observable;

//-When the data is produced by the Observable itself, we call it a cold Observable.
//-When the data is produced outside the Observable, we call it a hot Observable,
// because the data is being created regardless of if there is a subscriber or not. 
//-Cold Observables will replay the emissions to each Observer, ensuring that all Observers get all the data.
//-Observable sources that emit finite datasets are usually cold.
public class Observables_cold {

	public Observables_cold() {
		
		Observable<String> source = Observable.fromIterable(Arrays.asList("One", "Two", "Three", "Four"));
		
		source.subscribe(s -> System.out.println("Observer 1 Received: " + s));		//first observer
		source.subscribe(s -> System.out.println("Observer 2 Received: " + s));		//second observer
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) {
		new Observables_cold();

	}

}
