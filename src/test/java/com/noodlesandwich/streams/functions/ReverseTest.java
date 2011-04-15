package com.noodlesandwich.streams.functions;

import java.util.Arrays;

import org.junit.Test;

import com.noodlesandwich.streams.Stream;

import static com.noodlesandwich.streams.matchers.NilMatcher.nil;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public final class ReverseTest {
    @Test public void
    the_reverse_of_nil_is_nil() {
        assertThat(Stream.nil().reverse(), is(nil()));
    }

    @Test public void
    reverses_a_stream() {
        Stream<Integer> stream = Stream.wrap(Arrays.asList(1, 2, 3));
        assertThat(stream.reverse(), contains(3, 2, 1));
    }
}