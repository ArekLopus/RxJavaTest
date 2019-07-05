package rx.concurrency;

//-By default, Observables execute work on the immediate thread, which is the thread that declared the Observer and subscribed it.
//-In many of examples, this was the main thread that kicked off our main() method.

//-But not all Observables will fire on the immediate thread.
//-Fe Observable.interval() fires on a thread other than the main one. The main thread kicks-off Observable.interval(),
// but not wait for it to complete because it is operating on its own separate thread now.

//-Observables are agnostic to whatever thread they work on, making concurrency easy to implement, configure, and evolve at any time.
//-Being able to split and combine Observables happening on different threads is powerful and eliminates the pain points of callbacks.

//-When you are working with a high volume of emissions (more than 10,000) and leveraging concurrency, you will likely want to use
// Flowables instead of Observables, which we will cover in Chapter 8, Flowables and Backpressure.


//		subscribeOn()
//-The subscribeOn() operator instructs the source Observable which Scheduler to emit emissions on.
//-If that source is not already tied to a particular Scheduler, it will use the Scheduler you specify.
//-If subscribeOn() is the only concurrent operation in an Observable chain, the thread from that Scheduler
// will work the entire Observable chain, pushing emissions from the source all the way to the final Observer. 
//-if you have multiple subscribeOn() calls on a given Observable chain, the top-most one, or the one closest to the source, will win
// and cause any subsequent ones to have no practical effect (other than unnecessary resource usage).
//-If I call subscribeOn() with Schedulers.computation() and then call subscribeOn() for Schedulers.io(),
// Schedulers.computation() is the one that will be used.

//		observeOn()
//-Use observeOn() to intercept each emission and push them forward on a different Scheduler (subscribeOn() you can set only once).
//-The observeOn() operator intercepts emissions at the point in the Observable chain and switch them to a different Scheduler going forward.
//-Unlike subscribeOn(), the placement of observeOn() matters.
// It will leave all operations upstream on the default or subscribeOn()-defined Scheduler, but will switch to a different Scheduler downstream.


//		Schedulers
//-Thread pools are a collection of threads. Depending on the policy of that thread pool, threads may be persisted and maintained
// so they can be reused. A queue of tasks is then executed by that thread pool.
//-Some thread pools hold a fixed number of threads (such as the computation() ), while others dynamically create and destroy threads as needed.
// Typically in Java, you use an ExecutorService as your thread pool. However, RxJava implements its own concurrency abstraction called Scheduler.
// It define methods and rules that an actual concurrency provider such as an ExecutorService or actor system must obey.
// The construct flexibly makes RxJava non-opinionated on the source of concurrency.
//-Many of the default Scheduler implementations can be found in the Schedulers static factory class. For a given Observer,
// a Scheduler will provide a thread from its pool that will push the emissions. When onComplete() is called, the operation
// will be disposed of and the thread will be given back to the pool, where it may be persisted and reused by another Observer.
//-Here are a few Scheduler types in RxJava. There are also some common third-party ones available in other libraries
// such as RxAndroid (covered in Chapter 11, RxJava for Android) and RxJavaFX.

//	Starting and shutting down Schedulers
//-Each default Scheduler is lazily instantiated when you first invoke its usage.
//-You can dispose the computation(), io(), newThread(), single(), and trampoline() Schedulers at any time by calling their
// shutdown() or all of them by calling Schedulers.shutdown().
// This will stop all their threads and forbid new tasks from coming in and will throw an error if you try otherwise.
//-You can also call their start() method, or Schedulersers.start(), to reinitialize the Schedulers so they can accept tasks again.

//-In desktop and mobile app environments, you should not run into many cases where you have to start and stop the Schedulers.
//-On the server side, however, J2EE-based apps (fe, Servlets) may get unloaded and reloaded and use a different classloader, causing
// the old Schedulers instances to leak. To prevent this from occurring, the Servlet should shut down the Schedulers manually in its destroy().

//-Only manage the life cycle of your Schedulers if you absolutely have to. It is better to let the Schedulers dynamically
// manage their usage of resources and keep them initialized and available so tasks can quickly be executed at a moment's notice.
//-Note carefully that it is better to ensure that all outstanding tasks are completed or disposed of before you shut down the Schedulers,
// or else you may leave the sequences in an inconsistent state.

public class AnInfo {}
