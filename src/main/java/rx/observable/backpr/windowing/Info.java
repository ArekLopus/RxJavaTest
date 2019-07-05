package rx.observable.backpr.windowing;

//-The window() operators are almost identical to buffer(), except that they buffer into other Observables rather than collections.
//-This results in an Observable<Observable<T>> that emits Observables.
// Each Observable emission will cache emissions for each scope and then flush them once subscribed (like the GroupedObservable from groupBy()).
//-This allows emissions to be worked with immediately as they become available
// rather than waiting for each list or collection to be finalized and emitted.
//-The window() operator is also convenient to work with if you want to use operators to transform each batch.
//-Just like buffer(), you can cut-off each batch using fixed sizing, a time interval, or a boundary from another Observable.
//-Of course, you can use these yielded Observables for other transformations besides String concatenations.
//-You can use all the operators we learned up to this point to perform different operations on each windowed Observable,
// and you will likely do that work in flatMap(), concatMap(), or switchMap().
public class Info {}
