package rx.observable;

import io.reactivex.Observable;

//-When the data is produced by the Observable itself, we call it a cold Observable.
//-When the data is produced outside the Observable, we call it a hot Observable,
// because the data is being created regardless of if there is a subscriber or not. 
public class Observables_hot {
	
	private Computator comp = new Computator();
	
	public Observables_hot() throws InterruptedException {
		
		
		System.out.println(comp.getComputation());
		
		Thread.sleep(1000);
		
		System.out.println(comp.getComputation());
		
		Thread.sleep(1000);
		
		Observable.just(comp.getComputation())
			.subscribe(System.out::println);
		
		comp.setContin(false);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Observables_hot();

	}
	
	
	private class Computator {
		
		private long computation = 1;
		private boolean contin = true;
		
		public Computator() {
			
			Thread t = new Thread(()->{
				try {
					System.out.println("Thread: " + Thread.currentThread().getName());
					while(contin) {
						computation++;
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			
			t.start();
		}
		
		
		public long getComputation() {
			return computation;
		}
		
		public void setContin(boolean contin) {
			this.contin = contin;
		}
		
	}
}
