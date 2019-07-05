package rx.operators;

//-Operators themselves are Observers to the Observable they are called on
//-If you call map() on an Observable, the returned Observable will subscribe to it. It will then transform each emission
// and in turn be a producer for Observers downstream, including other operators and the terminal Observer itself. 
//-You should strive to execute as much logic as possible using RxJava operators, and you should use an Observer
// to receive the end product emissions that are ready to be consumed.
//-When you keep algorithms and processes reactive, you can easily leverage the benefits of reactive programming
// such as lower memory usage, flexible concurrency, and disposability.

//-Just like Transformers, be mindful when creating custom operators to not share states between subscriptions unless you absolutely mean to.
//-Operators can be made enormously complex, this is especially the case when the operators deal with concurrency
// (fe, observeOn() and subscribeOn()) or share states between subscriptions (fe, replay()).
// The implementations of groupBy(), flatMap(), and window() are complicated and intricate as well.

//-There are a couple of rules in the Observable contract you must follow when calling the three events.
// • Never call onComplete() after onError() has occurred (or vice versa).
// • Do not call onNext() after onComplete() or onError() is called,
// • Do not call any events after disposal. 
//-Breaking these rules can have unintended consequences downstream.

//-Another thing that needs to be pointed out is that onNext(), onComplete(), and onError() calls can be manipulated and mixed as needed.
//-Fe, toList() does not pass an onNext() call downstream for every onNext() it receives from the upstream. It will keep collecting these
// emissions in an internal list. When onComplete() is called from the upstream, it will call onNext() on the downstream
// to pass that list before it calls onComplete().
public class AnInfo_ {}
