package rx.observable;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

//-More in multicasting.
//-A ConnectableObservable resembles an ordinary Observable, except that it does not begin emitting items when it is subscribed to,
// but only when its connect method is called.
//-In this way you can wait for all intended Observers to Observable.subscribe to the Observable before the Observable begins emitting items. 
//-Rather than Observer 1 processing all the emissions before Observer 2, each emission goes to each Observer simultaneously.
//-Using ConnectableObservable to force each emission to go to all Observers simultaneously is known as multicasting,
//-ConnectableObservable is hot, if new subscriptions occur after connect() is called, they will miss emissions that were fired previously.
public class Observables_connectable {

	public Observables_connectable() {
		
		ConnectableObservable<String> source = Observable.fromIterable(Arrays.asList("One", "Two", "Three", "Four")).publish();
		
		source.subscribe(s -> System.out.println("Observer 1: " + s));	//Set up observer 1
		source.map(String::length)			
		    .subscribe(i -> System.out.println("Observer 2: " + i));	//Set up observer 2
		source.connect();
		
		source.subscribe(s -> System.out.println("Observer 3: " + s));	//Set up observer 3
		source.connect();
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Observables_connectable();

	}

}
