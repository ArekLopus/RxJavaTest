package rx.operators.suppressing;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-Returns an Observable that drops items emitted by the source ObservableSource during a specified time window before the source completes. 
//-skipLast does not operate on any particular scheduler but uses the current time from the computation Scheduler.
public class SkipLast_time {

	public SkipLast_time() throws InterruptedException {
		
		Observable<String> source = Observable.<String>create(em -> {
			
			try {
		        for (int i = 1; i < 11; i++) {
		        	
		        	if (!em.isDisposed()) {
		        		em.onNext("Value: " + i + ", disposed: " + em.isDisposed() + ", thread: " + Thread.currentThread().getName());
		        		RxUtils.hardWork(400);
		        	} else {
		        		em.setCancellable(() -> System.out.println("- CANCELED -"));
		        		return;
		        	}
		        	
		        }
		        em.onComplete();
		    } catch (Throwable e) {
		        em.onError(e);
		    }
		});
		
		source
			.skipLast(2, TimeUnit.SECONDS)
			.subscribe(System.out::println, System.out::println, () -> System.out.println("Finished"));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new SkipLast_time();

	}

}
