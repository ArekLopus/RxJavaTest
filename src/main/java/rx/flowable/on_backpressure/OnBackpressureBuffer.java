package rx.flowable.on_backpressure;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

//-The onBackPressureBuffer() will take an existing Flowable that is assumed to not have backpressure implemented
// and then essentially apply BackpressureStrategy.BUFFER at that point to the downstream.
//-Since Flowable.interval() cannot be backpressured at the source, putting onBackPressureBuffer()
// after it will proxy a backpressured queue to the downstream.
//-There are a number of overload arguments that you can provide as well.
//-We will not get into all of them, and you can refer to the JavaDocs for more information, but we will highlight the common ones.
//-The capacity argument will create a maximum threshold for the buffer rather than allowing it to be unbounded.
//-An onOverflow Action lambda can be specified to fire an action when an overflow exceeds the capacity.
//-You can also specify a BackpressureOverflowStrategy enum to instruct how to handle an overflow that exceeds the capacity.
//-Here are the three BackpressureOverflowStrategy enum items that you can choose from:
//	BackpressureOverflowStrategy	Description
//	ERROR							Simply throws an error the moment capacity is exceeded
//	DROP_OLDEST						Drops the oldest value from the buffer to make way for a new one
//	DROP_LATEST						Drops the latest value from the buffer to prioritize older, unconsumed values
public class OnBackpressureBuffer {

	public OnBackpressureBuffer() throws InterruptedException {
		
		Flowable.interval(1, TimeUnit.MILLISECONDS)
	    	//.onBackpressureBuffer()
			.onBackpressureBuffer(100, ()-> System.out.println("--- Backpressure overflow"), BackpressureOverflowStrategy.DROP_OLDEST)
	    	.observeOn(Schedulers.io())
	    	.subscribe(i -> {
	    		Thread.sleep(5);
	    		System.out.println(i);
	    	});
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	public static void main(String[] args) throws Exception {
		new OnBackpressureBuffer();

	}

}
