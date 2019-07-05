package rx.concurrency;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

public class Flowable_concurent {

	public Flowable_concurent() throws InterruptedException {
		
		Flowable.just("One", "Two", "Three", "Four")
			//.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.io())
		    .subscribe(RxUtils::consumeStringWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint, () -> System.out.println("Op Completed"));
		
		Thread.sleep(200);
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Flowable_concurent();

	}

}
