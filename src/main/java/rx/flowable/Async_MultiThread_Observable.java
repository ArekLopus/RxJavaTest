package rx.flowable;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-When you add concurrency operations to an Observable chain (particularly observeOn(), parallelization, and operators such as delay()),
// the operation become asynchronous.
//-This means hat multiple parts of the Observable chain can be processing emissions at a given time, and producers can outpace consumers
// as they are now operating on different threads. An emission is no longer strictly being handed downstream one at a time
// from the source all the way to the Observer before starting the next one.
//-This is because once an emission hits a different Scheduler through observeOn() (or other concurrent operators),
// the source is no longer in charge of pushing that emission to the Observer.
//-Therefore, the source will start pushing the next emission even though the previous emission may not have reached the Observer yet. 

//-The emissions are being pushed much faster than the Observer can process them, and because backlogged emissions get queued by observeOn()
// in an unbounded manner, this could lead to many problems, including OutOfMemoryError exceptions.
public class Async_MultiThread_Observable {

	public Async_MultiThread_Observable() throws InterruptedException {
		
		Observable.range(1, 999_999_999)
	    	.map(MyItem::new)
	    	.observeOn(Schedulers.io())
	    	.subscribe(myItem -> {
	    		Thread.sleep(50);
	    		System.out.println("Received MyItem " + myItem.id);
	    	});
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	public static void main(String[] args) throws Exception {
		new Async_MultiThread_Observable();

	}
	
	static final class MyItem {
	    final int id;
	    MyItem(int id) {
	        this.id = id;
	        if(id % 1000000 == 0)
	        	System.out.println("Constructing MyItem " + id);
	    }
	}
}
