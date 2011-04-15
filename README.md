Streams for Java
================

Functional programming in Java shouldn't be so hard. Streams is an attempt to make it a little easier on us all by
providing Haskell-style functionality in an object-oriented format. A `Stream` is a simple object that wraps an
iterator (or an iterable) and allows you to (mis)treat it as you please. Everything is lazily evaluated, so nothing
happens until you ask for the results.

Personally, I find this sort of thing is much better explained with an example:

    Iterator<Integer> numbers = theIteratorOfAMassivelyLongList();
    Stream<Integer> numberStream = Stream.wrap(numbers);
    Stream<String> htmlStream = numberStream.map(addOne())
                                            .filter(isEven())
                                            .concat(defaults())
                                            .zipWith(definitions(), formatAsHtml());

    for (String item : htmlStream.take(5)) {
        // This will loop through only the first five values of that horrendously complicated endeavour.
        // In fact, it will only calculate the first five values.
        // No wasted effort.
    }

All you need to do is define those functions. Streams backs onto Google's Guava when it can to allow you to reuse code
wherever possible.

    private Function<Integer, Integer> addOne() {
        return new Function<Integer, Integer>() {
            public Integer apply(Integer input) {
                return input + 1;
            }
        };
    }