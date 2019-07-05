package rx.flowable.on_backpressure;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

//-The onBackpressureDrop() will simply discard emissions if the downstream is too busy to process them.
//-This is helpful when emissions are considered redundant if the downstream is already occupied
// (such as a "RUN" request being sent repeatedly, although the resulting process is already running). 
//-You can optionally provide an onDrop lambda argument specifying what to do with each dropped item.
public class OnBackpressureDrop {

	public OnBackpressureDrop() throws InterruptedException {
		
		Flowable.interval(1, TimeUnit.MILLISECONDS)
	    .onBackpressureDrop(i -> System.out.println("Dropping " + i))
	    .observeOn(Schedulers.io())
	    .subscribe(i -> {
	        Thread.sleep(5);
	        System.out.println(i);
	    });
		
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	public static void main(String[] args) throws Exception {
		new OnBackpressureDrop();

	}

}
