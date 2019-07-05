package rx.operators.blocking;

//	Blocking operators
//-In RxJava, there is a set of operators called blocking operators.
//-These operators serve as an immediate proxy between the reactive world and the stateful one,
// blocking and waiting for results to be emitted, but returned in a non-reactive way.
//-Even if the reactive operations are working on different threads, blocking operators will stop the declaring thread
// and make it wait for the results in a synchronized manner, much like blockingSubscribe().
//-Blocking operators are especially helpful in making the results of an Observable or Flowable easily available for evaluation.
//-Avoid using them in production because they encourage anti-patterns and undermine the benefits of reactive programming.
//-For testing, you will still want to prefer TestObserver and TestSubscriber.
//-But here are the blocking operators if you ever have a need for them.

public class AnInfo {}
