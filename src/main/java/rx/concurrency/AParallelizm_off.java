package rx.concurrency;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-No parallelizm
//-By default, Obsrvables are pushing items synchronously and one at a time from the source all the way to the Observer.
//-It is how Observable chains work by default without any concurrency.
public class AParallelizm_off {
	
	public AParallelizm_off() throws InterruptedException {
		
		long start = System.currentTimeMillis();
		
		Observable.range(1,10)
	    .map(i -> RxUtils.hardWork(500))
	    .subscribe(i -> System.out.println("Val: " + i + ", time (ms) " + (System.currentTimeMillis() - start)));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new AParallelizm_off();

	}

}



