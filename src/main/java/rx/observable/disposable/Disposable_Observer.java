package rx.observable.disposable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//-Disposable is passed in the implementation of an Observer through the onSubscribe().
//-This method was added in RxJava 2.0, and it allows the Observer to have the ability to dispose of the subscription at any time.
//-The Disposable is sent from the source all the way up the chain to the Observer, so each step in the Observable chain has access to it.
//-Note that passing an Observer to the subscribe() will be void and not return a Disposable since it is assumed that the Observer will handle it.
//-You can extend ResourceObserver which uses a default Disposable handling. Pass this to subscribeWith() instead of subscribe(),
// and you will get the default Disposable returned:
public class Disposable_Observer {

	public Disposable_Observer() throws InterruptedException {
		
		Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
		
		source.subscribe(myObserver);				// returns void!
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	Observer<Long> myObserver = new Observer<Long>() {
	    private Disposable disposable;
	    @Override
	    public void onSubscribe(Disposable disposable) {
	        this.disposable = disposable;
	    }
	    @Override
	    public void onNext(Long value) {
	    	if(value < 3) {
	    		System.out.println("Received < 3: " + value + ", thread: " + Thread.currentThread().getName());
	    	} else {
	    		System.out.println("Received = 3, disposed: " + value);
	    		disposable.dispose();
	    	}
	    	
	    }
	    @Override
	    public void onError(Throwable e) {
	    	System.out.println("Error: " + e.getMessage());
	    }
	    @Override
	    public void onComplete() {
	    	System.out.println("Completed.");
	    }
	};
	
	public static void main(String[] args) throws Exception {
		new Disposable_Observer();

	}

}
