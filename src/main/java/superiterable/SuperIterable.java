package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  // this is equivalent to "forEach"
//  public void withEvery(SuperIterable<E> this, Consumer<E> someObject) {
// This is "forEach"
//  public void withEvery(Consumer<E> someObject) {
//    for (E s : this.self) {
//      someObject.accept(s);
//    }
//  }

  public SuperIterable<E> filter(SuperIterable<E>this, Predicate<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : this.self) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return new SuperIterable<>(res);
  }


  // This type of wrapper (if it conforms to some math rules)
  // this is called (in functional programming) a "functor"
  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> res = new ArrayList<>();
    for (E s : this.self) {
      F f = op.apply(s);
      res.add(f);
    }
    return new SuperIterable<>(res);
  }

  public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
    List<F> res = new ArrayList<>();
    for (E s : this.self) {
      SuperIterable<F> manyf = op.apply(s);
      for (F f : manyf) {
        res.add(f);
      }
    }
    return new SuperIterable<>(res);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
