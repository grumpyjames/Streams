package com.noodlesandwich.streams.functions;

import com.noodlesandwich.streams.Streams;
import org.junit.Test;

import com.noodlesandwich.streams.Stream;
import com.noodlesandwich.streams.testutils.ThrowingIterator;

import static com.noodlesandwich.streams.matchers.NilMatcher.nil;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public final class DropTest {
    @Test public void
    is_lazy() {
        Stream<Object> stream = Streams.wrap(new ThrowingIterator());
        stream.drop(1);
    }

    @Test public void
    dropping_zero_elements_does_nothing() {
        Stream<Integer> stream = Streams.of(1, 2, 3, 4, 5);
        assertThat(stream.drop(0), contains(1, 2, 3, 4, 5));
    }

    @Test public void
    drops_the_first_N_elements_of_a_stream() {
        Stream<Integer> stream = Streams.of(1, 2, 3, 4, 5);
        assertThat(stream.drop(3), contains(4, 5));
    }

    @Test public void
    dropping_more_than_the_size_of_the_stream_returns_nil() {
        Stream<Object> stream = Streams.of(new Object(), new Object(), new Object());
        assertThat(stream.drop(7), is(nil()));
    }

    @Test(expected=IllegalArgumentException.class) public void
    cannot_drop_a_negative_number_of_elements() {
        Stream<Integer> stream = Streams.of(1, 2, 3, 4, 5);
        stream.drop(-1);
    }

    @Test public void
    is_repeatable() {
        Stream<Integer> droppedStream = Streams.of(1, 2, 3, 4, 5).drop(3);
        assertThat(droppedStream, contains(4, 5));
        assertThat(droppedStream, contains(4, 5));
    }
}
