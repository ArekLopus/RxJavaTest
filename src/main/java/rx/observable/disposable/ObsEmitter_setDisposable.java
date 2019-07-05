package rx.observable.disposable;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//-If your Observable.create() is wrapped around some resource, you should also handle the disposal of that resource to prevent leaks. 
//-ObservableEmitter has the setCancellable() and setDisposable() methods for that.
//-interface Cancellable -> void cancel() throws Exception
public class ObsEmitter_setDisposable {

	public ObsEmitter_setDisposable() throws InterruptedException {
		
		Observable<String> source = Observable.<String>create(em -> {
			
			try {
		        for (int i = 0; i < 100; i++) {
		        	
		        	if (!em.isDisposed()) {
		        		em.onNext("Value: " + i + ", disposed: " + em.isDisposed() + ", thread: " + Thread.currentThread().getName());
		        		RxUtils.hardWork(1000);
		        	} else {
		        		em.setDisposable(new Disposable() {
							
							@Override
							public boolean isDisposed() {
								return true;
							}
							
							@Override
							public void dispose() {
								System.out.println("DISPOSED");
							}
						});
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
		new ObsEmitter_setDisposable();

	}

}
