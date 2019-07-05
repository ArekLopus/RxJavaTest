package rx.concurrency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-If you want to read one or more data sources and wait for the response to come back, you will want to do that part on Schedulers.io()
// and will likely leverage subscribeOn() since that is the initial operation.
//-But once you have that data, you may want to do intensive computations with it, and Scheduler.io() may no longer be appropriate.
//-You will want to constrain these operations to a few threads that will fully utilize the CPU.
// Therefore, you use observeOn() to redirect data to Schedulers.computation().
//-You can actually use multiple observeOn() operators to switch Schedulers more than once.
public class ObserveOn_multiSwith {

	public ObserveOn_multiSwith() throws InterruptedException {
		
		//Happens on IO Scheduler
		Observable.just("WHISKEY/27653/TANGO", "6555/BRAVO", "232352/5675675/FOXTROT")
		    .subscribeOn(Schedulers.io())
		    .flatMap(s -> Observable.fromArray(s.split("/")))
		    .doOnNext(s -> System.out.println("Split out " + s + " on thread " + Thread.currentThread().getName()))
		     //Happens on Computation Scheduler
		    .observeOn(Schedulers.computation())
		    .filter(s -> s.matches("[0-9]+"))
		    .map(Integer::valueOf)
		    .reduce((total, next) -> total + next)
		    .doOnSuccess(i -> System.out.println("Calculated sum " + i + " on thread " + Thread.currentThread().getName()))
		    //Switch back to IO Scheduler
		    .observeOn(Schedulers.io())
		    .map(i -> i.toString())
		    .doOnSuccess(s -> System.out.println("Writing " + s + " to file on thread " + Thread.currentThread().getName()))
		    .subscribe(s -> write(s,"d:/ccc/output.txt")); 
		
		Thread.sleep(1000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void write(String text, String path) {
	    BufferedWriter writer = null;
	    try {
	        //create a temporary file
	        File file = new File(path);
	        writer = new BufferedWriter(new FileWriter(file));
	        writer.append(text);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            writer.close();
	        } catch (Exception e) {}
	    }
	}
	
	
	public static void main(String[] args) throws Exception {
		new ObserveOn_multiSwith();

	}

}



