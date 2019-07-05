package rx.observable.backpr.switching;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class FxSwitch_noFx {
	
	public FxSwitch_noFx() {
		
		Observable<Boolean> source = Observable.create( em -> {
			
			try {
				while(true) {
					boolean next = ThreadLocalRandom.current().nextBoolean();
					Thread.sleep(1000);
					System.out.println("Next: " + next + ", thread: " + Thread.currentThread().getName());
					em.onNext(next);
				}
			} catch (Exception e) {
				em.onError(e);
			}
		});
		
		// If source produces true Observable.interval() starts, id false Observable.empty() is returned and it does nothing for next 1000ms.
		source.switchMap(selected -> {
			if (selected)
				return Observable.interval(400, TimeUnit.MILLISECONDS);
			else
				return Observable.empty();
			})
			.observeOn(Schedulers.computation())
			.subscribe(e -> System.out.println("Val: " + e + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) {
		new FxSwitch_noFx();
	}
}