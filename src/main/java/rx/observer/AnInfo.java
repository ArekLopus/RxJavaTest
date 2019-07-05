package rx.observer;

//-Observables are the source which emits items to the Observers. For Observers to listen to the Observables, they need to subscribe first.
//-The instance created after subscribing in RxJava2 is called Disposable.
//-In order to stop listening to Observables, we can call unsubscribe by calling the method dispose() on the Disposable instance.

//-The onSubscribe(Disposable d), onNext(), onComplete(), and onError() methods actually define the Observer type, an abstract interface.

//-It is critical to note that most of the subscribe() overload variants return a Disposable. Disposables allow us to disconnect
// an Observable from an Observer so emissions are terminated early, which is critical for infinite or long-running Observables. 
//-The Disposable is a link between an Observable and an active Observer, and you can call its dispose() method to stop emissions
// and dispose of all resources used for that Observer.

//-Note that passing an Observer to the subscribe() method will be void and not return a Disposable since it is assumed that
// the Observer will handle it. 

//-Each operator, such as map() and filter(), also implements Observer internally.
public class AnInfo {}
