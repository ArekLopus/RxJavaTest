package rx.concurrency.schedulers;

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
