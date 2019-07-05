package rx.flowable.on_backpressure;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

//-A slight variant of onBackpressureBuffer() is onBackPressureLatest().
//-This will retain the latest value from the source while the downstream is busy, and once the downstream is free to process more,
// it will provide the latest value. Any previous values emitted during this busy period will be lost.

//-there is a jump between 127 and around 500. This is because all numbers in between were ultimately beaten by around 500 being
// the latest value, and at that time, the downstream was ready to process more emissions. It started by consuming 
// the cached around 500 and the others before it was dropped.
public class OnBackpressureLatest {

	public OnBackpressureLatest() throws InterruptedException {
		
		Flowable.interval(1, TimeUnit.MILLISECONDS)
	    	.onBackpressureLatest()
	    	.observeOn(Schedulers.io())
	    	.subscribe(i -> {
	    		Thread.sleep(5);
	    		System.out.println(i);
	    	});
		
		Thread.sleep(750);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	public static void main(String[] args) throws Exception {
		new OnBackpressureLatest();

	}

}
