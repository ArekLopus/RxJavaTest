package rx.observable.disposable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

//-If you have several subscriptions that need to be managed and disposed of, it can be helpful to use CompositeDisposable.
//-It implements Disposable, but it internally holds a collection of disposables, which you can add to and then dispose all at once.
//-CompositeDisposable is a simple but helpful utility to maintain a collection of disposables that you can add to
// by calling add() or addAll(). When you no longer want these subscriptions, you can call dispose() to dispose of all of them at once.
public class Disposable_CompositeDisposable {
	
	public Disposable_CompositeDisposable() throws InterruptedException {
		
		CompositeDisposable disposables = new CompositeDisposable();
		Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
		
		Disposable disposable1 = seconds.subscribe(l -> System.out.println("Observer 1: " + l));	//subscribe and capture disp
		Disposable disposable2 = seconds.subscribe(l -> System.out.println("Observer 2: " + l));
		
		disposables.addAll(disposable1, disposable2);	//put both disposables into CompositeDisposable
		
		Thread.sleep(5000);
		
		disposables.dispose();			//dispose all disposables
		System.out.println("After disposables.dispose(), waiting 5 secs to check if more emissions.");
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Disposable_CompositeDisposable();

	}

}
