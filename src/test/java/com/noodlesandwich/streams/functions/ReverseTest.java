package com.noodlesandwich.streams.functions;

import com.noodlesandwich.streams.Streams;
import org.junit.Test;

import com.noodlesandwich.streams.Stream;
import com.noodlesandwich.streams.testutils.ThrowingIterator;

import static com.noodlesandwich.streams.matchers.NilMatcher.nil;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public final class ReverseTest {
    @Test public void
    is_lazy() {
        Stream<Object> stream = Streams.wrap(new ThrowingIterator());
        stream.reverse();
    }

    @Test public void
    the_reverse_of_nil_is_nil() {
        assertThat(Streams.nil().reverse(), is(nil()));
    }

    @Test public void
    reverses_a_stream() {
        Stream<Integer> stream = Streams.of(1, 2, 3);
        assertThat(stream.reverse(), contains(3, 2, 1));
    }

    @Test public void
    is_repeatable() {
        Stream<Integer> reversedStream = Streams.of(1, 2, 3).reverse();
        assertThat(reversedStream, contains(3, 2, 1));
        assertThat(reversedStream, contains(3, 2, 1));
    }
}
