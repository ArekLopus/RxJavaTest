package rx.operators.suppressing;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-Returns an Observable that emits the items from the source ObservableSource that were emitted
// in a specified window of time before the ObservableSource completed.
//-This version of takeLast operates by default on the computation Scheduler.
public class TakeLast_time {

	public TakeLast_time() throws InterruptedException {
		
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
			.takeLast(2, TimeUnit.SECONDS)
			.subscribe(System.out::println, System.out::println, () -> System.out.println("Finished"));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new TakeLast_time();

	}

}
