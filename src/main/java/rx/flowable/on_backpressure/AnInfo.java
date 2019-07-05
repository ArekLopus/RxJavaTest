package rx.flowable.on_backpressure;

//-If you are provided a Flowable that has no backpressure implementation (including ones derived from Observable),
// you can apply BackpressureStrategy using onBackpressureXXX() operators.
//-These also provide a few additional configuration options.
//-This can be helpful if, fe, you have a Flowable.interval() that emits faster than consumers can keep up. Flowable.interval() cannot be
// slowed down at the source because it is time-driven, but we can use an onBackpressureXXX() operator to proxy between it and the downstream.
//-Sometimes, Flowable may simply be configured with BackpressureStrategy.MISSING
// so these onBackpressureXXX() operators can specify the strategy later.

public class AnInfo {}
