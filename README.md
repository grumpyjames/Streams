Streams for Java
================

Functional programming in Java shouldn't be so hard. Streams is an attempt to make it a little easier on us all by
providing Haskell-style functionality in an object-oriented format. A `Stream` is a simple object that wraps an
iterator (or an iterable) and allows you to (mis)treat it as you please. Everything is lazily evaluated, so nothing
happens until you ask for the results.

Personally, I find this sort of thing is much better explained with an example:

    Iterator<Integer> numbers = theIteratorOfAMassivelyLongList();
    Stream<Integer> numberStream = Stream.wrap(numbers);
    Stream<String> htmlStream = numberStream.map(add(3))
                                            .filter(isEven())
                                            .concat(defaults())
                                            .zipWith(definitions(), formatAsHtml());

    for (String item : htmlStream.take(5)) {
        // This will loop through only the first five values of that horrendously complicated endeavour.
        // In fact, it will only evaluate the first five values.
        // No wasted effort.
    }

All you need to do is define those functions. Streams backs onto Google's Guava when it can to allow you to reuse code
wherever possible.

    private Function<Integer, Integer> add(final int n) {
        return new Function<Integer, Integer>() {
            public Integer apply(Integer input) {
                return input + n;
            }
        };
    }

Using Streams
-------------

All you have to do is [download the JAR][Streams-0.1.jar], grab [Guava][] Release 09 and add them to your classpath.

  [Streams-0.1.jar]: https://github.com/downloads/SamirTalwar/Streams/Streams-0.1.jar
  [Guava]: http://code.google.com/p/guava-libraries/

Building Streams
----------------

Streams is built through [Gradle][]. I recommend grabbing the latest version, as the one in the Ubuntu repositories is
a little out of date. Then just fire up a command line and type:

    gradle build

This will run all the tests and create a JAR in the `build/libs` directory. Please run this command and ensure it
succeeds before pushing up to your public repository as it will make sure anyone downloading it can build it.

To create an Eclipse project and classpath file, type:

    gradle eclipse

This will generate everything you need to jump in and play with the code in your IDE.

  [Gradle]: http://gradle.org/
