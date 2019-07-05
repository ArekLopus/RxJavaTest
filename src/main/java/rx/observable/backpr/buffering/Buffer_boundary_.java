package rx.observable.backpr.buffering;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-The most powerful variance of buffer() is accepting another Observable as a boundary argument.
//-It does not matter what type this other Observable emits. All that matters is every time it emits something,
// it will use the timing of that emission as the buffer cut-off.
//-In other words, the arbitrary occurrence of emissions of another Observable will determine when to "slice" each buffer.

//-This is probably the most flexible way to buffer items based on highly variable events.
//-The boundary can be any Observable representing any kind of event happening at any time.
//-This idea of an Observable serving as a cut-off for another Observable is a very powerful pattern. 
public class Buffer_boundary_ {

	public Buffer_boundary_() throws InterruptedException {
		
		Observable<Long> cutOffs = Observable.interval(1, TimeUnit.SECONDS);	// Make a buffer every 1 sec. 
		
		Observable.interval(300, TimeUnit.MILLISECONDS)
		    .map(i -> (i + 1) * 300)
		    .buffer(cutOffs)
		    .subscribe(System.out::println);
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Buffer_boundary_();

	}

}



