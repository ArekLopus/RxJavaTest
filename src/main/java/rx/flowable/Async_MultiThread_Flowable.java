package rx.flowable;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

//-The Flowable is a backpressured variant of the Observable that tells the source to emit at a pace specified by the downstream operations.
//-The source started by pushing 128 emissions, and after that, a steady flow of 96 emissions at a time was processed by the Flowable chain.
//-Entire Flowable chain strives to have no more than 96 emissions in its pipeline at any given time.
//-This is what we call backpressure, and it effectively introduces a pull dynamic to the push-based operation
// to limit how frequently the source emits.
//-Why Flowable.range() starts with 128 emissions, and why observeOn() only sends 96 downstream before requesting another 96, leaving 32
// unprocessed emissions? The initial batch of emissions is a bit larger so some extra work is queued if there is any idle time.
// If (in theory) our Flowable operation started by requesting 96 emissions and continued to emit steadily at 96 emissions at a time,
// there would be moments where operations might wait idly for the next 96. Therefore, an extra rolling cache of 32 emissions is maintained
// to provide work during these idle moments, which can provide greater throughput.
//-This is much like a warehouse holding a little extra inventory to supply orders while it waits for more from the factory.
public class Async_MultiThread_Flowable {

	public Async_MultiThread_Flowable() throws InterruptedException {
		
		Flowable.range(1, 999_999_999)
	    	.map(MyItem::new)
	    	.observeOn(Schedulers.io())
	    	.subscribe(myItem -> {
	    		Thread.sleep(500);
	    		System.out.println("Received MyItem " + myItem.id);
	    	});
		
		Thread.sleep(5000);
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	public static void main(String[] args) throws Exception {
		new Async_MultiThread_Flowable();

	}
	
	static final class MyItem {
	    final int id;
	    MyItem(int id) {
	        this.id = id;
	        if(id % 10 == 0)
	        	System.out.println("Constructing MyItem " + id);
	    }
	}
}
