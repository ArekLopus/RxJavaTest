package rx.operators.reducing;

//-You will likely have moments where you want to take a series of emissions and consolidate them into a single emission
// (usually emitted through a Single). We will cover a few operators that accomplish this.
//-Note that nearly all of these operators only work on a finite Observable that calls onComplete() because typically, we can consolidate
// only finite datasets. We will explore this behavior as we cover these operators.

public class AInfo {}
