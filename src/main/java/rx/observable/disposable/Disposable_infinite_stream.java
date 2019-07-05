package rx.observable.disposable;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//-If your Observable is a long-running or infinite Observable, you should ideally check the isDisposed() of ObservableEmitter regularly,
// to see whether you should keep sending emissions. This prevents unnecessary work from being done if the subscription is no longer active.
//-Observable MUST run on different thread otherwise it WONT work! (here: subscribeOn(Schedulers.computation()))
public class Disposable_infinite_stream {

	public Disposable_infinite_stream() throws InterruptedException {
		
		Observable<String> source = Observable.<String>create(em -> {
			
			try {
		        for (int i = 0; i < 100; i++) {
		        	
		        	if (!em.isDisposed()) {
		        		em.onNext("Value: " + i + ", disposed: " + em.isDisposed() + ", thread: " + Thread.currentThread().getName());
		        		RxUtils.hardWork(1000);
		        	} else {
		        		System.out.println("Value: " + i + ", disposed: " + em.isDisposed() + ", thread: " + Thread.currentThread().getName());
		        		return;
		        	}
		        	
		        }
		        em.onComplete();
		    } catch (Throwable e) {
		        em.onError(e);
		    }
		}).subscribeOn(Schedulers.computation());
		
		Disposable subscribe = source.subscribe(System.out::println, System.out::println, () -> System.out.println("Finished"));
		
		Thread.sleep(4000);
		subscribe.dispose();
		
		Thread.sleep(2000);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Disposable_infinite_stream();

	}

}
