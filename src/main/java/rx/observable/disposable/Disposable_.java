package rx.observable.disposable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

//-A disposable (previously called Subscription in RxJava 1.0) is a tool that can be used to control the life cycle of an Observable. 
//-Disposable is a link between an Observable and an active Observer, and you can call its dispose() to stop emissions
// and dispose of all resources used for that Observer.

//-The finite Observables that call onComplete() will typically dispose of themselves safely when they are done.
//-But with infinite or long-running Observables, you likely will run into situations where you want to explicitly stop the emissions
// and dispose of everything associated with that subscription.
//-You cant trust the garbage collector to take care of active subscriptions that you no longer need,
// and explicit disposal is necessary in order to prevent memory leaks.

//-isDisposed() tells if it has been disposed of already.

//-If your Observable.create() is wrapped around some resource, you should also handle the disposal of that resource to prevent leaks. 
//-ObservableEmitter has the setCancellable() and setDisposable() methods for that.
//-interface Cancellable -> void cancel() throws Exception;
public class Disposable_ {

	public Disposable_() throws InterruptedException {
		
		Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
		Disposable disposable = source.subscribe(e -> System.out.println("Received: " + e + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(5000);
		
		disposable.dispose();		//dispose and stop emissions
		System.out.println("After disposable.dispose(), waiting 5 secs to check if more emissions.");
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Disposable_();

	}

}
