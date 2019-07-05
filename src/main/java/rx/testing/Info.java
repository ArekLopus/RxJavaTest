package rx.testing;

//-Tests are in dir: src/test/java	-> Blocking, TestObserver, TestSubscriber, TestScheduler. 

//-To prevent main thread to finish we used Thread.sleep(), especially when used Observable.interval(), subscribeOn(), or observeOn().
//-When it comes to unit testing, the unit test usually has to complete before it starts the next one.
//-This can become quite messy when we have an Observable or Flowable operation that happens on a different thread.
// When a test method declares an asynchronous Observable or Flowable chain operation, we need to block and wait for that operation to complete.

//-Thankfully, we do not have to get creative using synchronizers and other native Java concurrency tools.
//-Instead, we can use blockingSubscribe(), which will block the declaring main thread until onComplete() (or onError()) is called.
//-There are better ways to test other than blockingSubscribe(). But blockingSubscribe() is a quick and effective way to stop the declaring
// thread and wait for the Observable or Flowable to finish before proceeding, even if it is on a different thread.
// Just make sure that the source terminates at some point, or the test will never finish.

//-Be judicious in how you use blockingSubscribe() outside the context of testing and using it in production. There are times it is a good
// solution to interface with a non-reactive API. Fe, it can be valid to use it in production to keep an application alive indefinitely
// and is an effective alternative to using Thread.sleep(). Just be careful to ensure the asynchronous benefits of RxJava are not undermined.

//Blocking operators
//-In RxJava, there is a set of operators we have not covered yet called blocking operators.
//-These operators serve as an immediate proxy between the reactive world and the stateful one,
// blocking and waiting for results to be emitted, but returned in a non-reactive way.
//-Even if the reactive operations are working on different threads, blocking operators will stop the declaring thread
// and make it wait for the results in a synchronized manner, much like blockingSubscribe().
//-Blocking operators are especially helpful in making the results of an Observable or Flowable easily available for evaluation.
//-Avoid using them in production because they encourage anti-patterns and undermine the benefits of reactive programming.
//-For testing, you will still want to prefer TestObserver and TestSubscriber, which we will cover later.
//-But here are the blocking operators if you ever have a need for them.


//	Using TestObserver and TestSubscriber
//-Prefer TestObserver and TestSubscriber over blocking operators as much as possible. 
//-TestObserver is used for Observable, Single, Maybe, and Completable sources, while TestSubscriber is used for Flowable sources.
//-While you can use these blocking operators to do simple assertions, there is a much more comprehensive way to test reactive code than
// simply blocking for one or more values. After all, we should do more than test onNext() calls. We also have onComplete() and onError()
// events to account for! It also would be great to streamline testing other RxJava events, such as subscription, disposal, and cancellation.
//-TestObserver and TestSubscriber are your two best friends in testing your RxJava applications.
//-TestObserver and TestSubcsriber are a treasure trove of convenient methods to aid testing, many of which assert that certain events
// have occurred or specific values were received. There are also blocking methods, such as awaitTerminalEvent(), which will stop
// the calling thread until the reactive operation terminates.
//-Assert methods
// assertComplete		assertEmpty				assertError			assertErrorMessage		assertFailure			assertFailureAndMessage
// assertNever			assertNoErrors			assertNotComplete	assertNoTimeout			assertNotSubscribed		assertNotTerminated
// assertNoValues		assertOf				assertResult		assertSubscribed		assertTerminated		assertTimeout		
// assertValue			assertValueAt			assertValueCount	assertValues			assertValueSequence		assertValueSequenceOnly
// assertValueSet		assertValueSetOnly		assertValuesOnly


//	Manipulating time with the TestScheduler
//-If we have a lot of unit tests that deal with time-driven sources, it can take a long time for testing to complete.
//-The TestScheduler does exactly this. It is a Scheduler implementation that allows us to fast-forward by a specific amount of elapsed time,
// and we can do any assertions after each fast-forward to see what events have occurred.
//-Carefully note that advanceTimeBy() will fast-forward the specified time interval relative to the current time,
// whereas advanceTimeTo() will jump to the exact time elapsed since the subscription has occurred.
public class Info {}
