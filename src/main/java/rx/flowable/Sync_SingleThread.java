package rx.flowable;

import io.reactivex.Observable;

//-Pushing items synchronously and one at a time from the source all the way to the Observer
// is how Observable chains work by default without any concurrency.
//-Eeach emission is bring processed one at a time from the source all the way to the terminal Observer.
//-This is because one thread is doing all the work for this entire operation, making everything synchronous.
//-The consumers and producers are passing emissions in a serialized, consistent flow.
public class Sync_SingleThread {

	public Sync_SingleThread() {
		
		Observable.range(1, 999_999_999)
	    	.map(MyItem::new)
	    	.subscribe(myItem -> { 
	    		Thread.sleep(1000);
	    		System.out.println("Received MyItem " + myItem.id);
	    	});
	
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	public static void main(String[] args) {
		new Sync_SingleThread();

	}
	
	static final class MyItem {
	    final int id;
	    MyItem(int id) {
	        this.id = id;
	        System.out.println("Constructing MyItem " + id);
	    }
	}
}
