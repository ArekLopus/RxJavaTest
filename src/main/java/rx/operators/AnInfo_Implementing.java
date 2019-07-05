package rx.operators;

//-Ideally, you will rarely get to a point where you need to build your own operator from scratch by implementing Operators.
//-ObservableTransformer and FlowableTransformer will hopefully satisfy most cases where you can use existing operators to compose new ones,
// and this is usually the safest route.
//-But on occasion, you may find yourself having to do something that the existing operators cannot do or not do easily.
//-After you exhaust all other options, you may have to create an operator that manipulates each 
// onNext(), onComplete(), and onError() event between the upstream and the downstream.
//-Implementing your own ObservableOperator (as well as FlowableeOperator) is more involved than creating an ObservableTransformer.
// Instead of composing a series of existing operators, you intercept the onNext(), onComplete(), onError(), and  onSubscribe() calls
// from the upstream by implementing your own Observer instead. This Observer will then logically pass the onNext(), onComplete(),
// and onError() events to the downstream Observer in a way that fulfills the desired operation.

//-To create your own ObservableOperator<Downstream,Upstream> (where Upstream is the upstream emission type
// and Downstream is the downstream emission type), you will need to implement its apply() method.
// This accepts an Observer<Downstream> observer argument and returns an Observer<Upstream>.
//-You create another Observer (you can use an abstract DisposableObserver / Publisher that handles disposal requests for us)
// to receive emissions and events from the upstream and relay them to the downstream Observer.
// You can manipulate the events to execute the desired logic as well as add any side-effects.
//-You can then use this ObservableOperator by calling it in the lift() operator in your Observable chain.

//	Custom Transformers and operators for Singles, Maybes, and Completables
//-There are Transformer and operator counterparts for Single, Maybe, and Completable.
//-When you want to create an Observable or Flowable operator that yields Single, you might find it easier to convert
// it back into an Observable/Flowable by calling its toObservable() or toFlowable() operators. This also applies to Maybe. 
//-If on some rare occasion you need to create a Transformer or operator specifically to takea Single and transform it into another Single,
// you will want to use SingleTransformer or SingleOperator.
//-Maybe, Completable have counterparts with MaybeTransformer/MaybeOperator and CompletableTransformer/CompletableOperator.
//-The implementation of apply() for all of these should largely be the same experience, and you will use SingleObserver, MaybeObserver,
// and CompletableObserver to proxy the upstream and downstream.
public class AnInfo_Implementing {}
