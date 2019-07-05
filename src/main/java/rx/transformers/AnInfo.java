package rx.transformers;

//-In RxJava, there are ways to implement your own custom operators using the compose() and lift() methods,
// which exist on both Observable and Flowable.
//-Most of the time, you will likely want to compose existing RxJava operators to create a new operator.
//-But on occasion, you may find yourself needing an operator that must be built from scratch. The latter is a lot more work:


//-Transformers are a great way to reuse a series of operators that perform a common task.
//-Usually, you will get the most flexibility and speed by implementing them through static factory methods, but you
// can also extend ObservableTransformer onto your own class implementation

//-It is redundant to invoke twice, so you can compose these operators into a single operator by implementing ObservableTransformer<T,R>.
//-This type has an apply() method that accepts an Observable<T> upstream and returns an Observable<R> downstream.
//-In your implementation, you can return an Observable chain that adds on any operators to the upstream,
// and after those transformations, it returns an Observable<R>.

//-You can also create Transformers that target specific emission types and accept arguments.
//-Fe, you can create a joinToString() Transformer that accepts a separator argument and concatenates String emissions with that separator.
// Usage of this ObservableTransformer will only compile when invoked on an Observable<String>:

//-To invoke a Transformer into an Observable chain, you pass it to the compose() operator.
//-When called on an Observable<T>, the compose() operator accepts an ObservableTransformer<T,R> and returns the transformed Observable<R>.
// This allows you to reuse Rx logic and invoke it in multiple places.

//-When you implement your own ObservableTransformer, you might want to create a FlowableTransformer counterpart as well.
//-This way, you can use your operator on both Observables and Flowables.
//-The FlowableTransformer is not much different from Observable. Of course, it will support backpressure since it is composed with Flowables.
// Otherwise, it is pretty much the same in its usage except that you obviously pass it to compose() on a Flowable, not Observable.

//	Custom Transformers and operators for Singles, Maybes, and Completables
//-There are Transformer and operator counterparts for Single, Maybe, and Completable.
//-When you want to create an Observable or Flowable operator that yields Single, you might find it easier to convert
// it back into an Observable/Flowable by calling its toObservable() or toFlowable() operators. This also applies to Maybe. 
//-If on some rare occasion you need to create a Transformer or operator specifically to takea Single and transform it into another Single,
// you will want to use SingleTransformer or SingleOperator.
//-Maybe, Completable have counterparts with MaybeTransformer/MaybeOperator and CompletableTransformer/CompletableOperator.
//-The implementation of apply() for all of these should largely be the same experience, and you will use SingleObserver, MaybeObserver,
// and CompletableObserver to proxy the upstream and downstream.
public class AnInfo {}
