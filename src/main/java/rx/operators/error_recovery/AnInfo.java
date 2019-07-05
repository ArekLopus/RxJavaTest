package rx.operators.error_recovery;

//-Exceptions can occur in your Observable chain across many operators depending on what you are doing.
//-We already know about the onError() event that is communicated down the Observable chain to the Observer.
// After that, the subscription terminates and no more emissions will occur. But sometimes, we want to intercept exceptions before they get
// to the Observer and attempt some form of recovery. We cannot necessarily pretend that the error never happened and expect emissions
// to resume, but we can attempt resubscribing or switch to an alternate source Observable.
//-We can still do the former, just not with RxJava operators, which we will see shortly. If you find that the error recovery operators
// do not meet your needs, chances are you can compose them creatively.
public class AnInfo {}