package rx.subjects_and_processors;

//-As with the Observable/Flowable split, the backpressure-aware, Reactive-Streams compliant implementations are based on
// the FlowableProcessor<T> class (which extends Flowable to give a rich set of instance operators).

//	FlowableProcessor
//-Represents a Subscriber and a Flowable (Publisher) at the same time,
// allowing multicasting events from a single source to multiple child Subscribers. 

//-All methods except the onSubscribe, onNext, onError and onComplete are thread-safe.
// Use toSerialized() to make these methods thread-safe as well.


//-The io.reactivex.processors.AsyncProcessor, io.reactivex.processors.BehaviorProcessor, io.reactivex.processors.PublishProcessor,
// io.reactivex.processors.ReplayProcessor and io.reactivex.processors.UnicastProcessor are backpressure-aware.
public class AnInfo_processors {}
