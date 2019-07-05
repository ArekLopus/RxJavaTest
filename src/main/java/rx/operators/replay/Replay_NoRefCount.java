package rx.operators.replay;

import io.reactivex.Observable;

//-Note that if you always want to persist the cached values in your replay() even if there are no subscriptions,
// use it in conjunction with autoConnect(), not refCount().

//-The refCount() causes the cache (and the entire chain) to dispose of and reset the moment Observer 1 is done, as there are no more Observers.
//-When Observer 2 came in, it starts all over and emits everything just like it is the first Observer, and another cache is built.
//-This may not be desirable, so you may consider using autoConnect() to persist the state of replay() and not have it dispose of
// when no Observers are present.
public class Replay_NoRefCount {

	public Replay_NoRefCount() {
		
		Observable<String> source = Observable.just("One", "Two", "Three")
		    .replay(1)
		    .autoConnect();
		
		source.subscribe(l -> System.out.println("Observer 1: " + l));	//Observer 1
		source.subscribe(l -> System.out.println("Observer 2: " + l));	//Observer 2
		System.out.println();
		
		
		Observable<String> source2 = Observable.just("One", "Two", "Three")
			    .replay(1)
			    .refCount();
		source2.subscribe(l -> System.out.println("Observer 1: " + l));	//Observer 1
		source2.subscribe(l -> System.out.println("Observer 2: " + l));	//Observer 2
		System.out.println();
		
		
		System.out.println("--- Main Thread Finished ---");
			
	}

	public static void main(String[] args) {
		new Replay_NoRefCount();

	}

}
