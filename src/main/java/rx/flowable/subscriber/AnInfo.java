package rx.flowable.subscriber;

//-Instead of an Observer, the Flowable uses a Subscriber to consume emissions and events at the end of a Flowable chain.
//-If you pass only lambda event arguments (and not an entire Subscriber object), subscribe() does not return a Disposable
// but rather a Subscription, which can be disposed of by calling cancel() instead of dispose().
//-The Subscription can also serve another purpose; it communicates upstream how many items are wanted using its request().
//-Subscription can also be leveraged in the onSubscribe() of Subscriber to request() elements the moment it is ready to receive emissions.
//-Just like an Observer, the quickest way to create a Subscriber is to pass lambda arguments to subscribe().

//-Of course, you can implement your own Subscriber as well, which, of course, has the onNext(), onError(), and onComplete() methods
// as well as onSubscribe().
//-This is not as straightforward as impl an Observer because you need to call request() on Subscription to request emissions at the right moments.
public class AnInfo {}
