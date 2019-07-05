package rx.multicasting;

import io.reactivex.Observable;

//-autoConnect() will return an Observable<T> that will automatically call connect() after a specified number of Observers are subscribed.
//-This saves us the trouble of having to save ConnectableObservable and call its connect() later.
// Obviously, this does not work well when you have an unknown number of Observers and you want all of them to receive all emissions.
//-If no argument for numberOfSubscribers, it will default to 1. This can be helpful if you want it to start firing on the first subscription
// and do not care about any subsequent Observers missing previous emissions.
//-If you pass 0 to autoConnect() for the numberOfSubscribers argument, it will start firing immediately and not wait for any Observers.
// This can be handy to start firing emissions immediately without waiting for any Observers.

//-Returns an Observable that auto connects (at most once) to this ConnectableObservablewhen the specified number of Observers subscribe to it. 
public class AutoConnect {

	public AutoConnect() {
		
		Observable<Integer> threeRandoms = Observable.range(1,3)
		    .publish()
		    //.autoConnect();		// Default is 1
		    .autoConnect(2);
		
		threeRandoms.subscribe(i -> System.out.println("Observer 1: " + i));	//Observer 1 - print each random integer
		threeRandoms.reduce(0, (total,next) -> total + next)					//Observer 2 - sum the random ints, then print
		    .subscribe(i -> System.out.println("Observer 2: " + i));
		threeRandoms.subscribe(i -> System.out.println("Observer 1: " + i));	//Observer 3 - not included 
		System.out.println();
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new AutoConnect();

	}

}
