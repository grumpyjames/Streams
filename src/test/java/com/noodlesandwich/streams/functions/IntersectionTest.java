package com.noodlesandwich.streams.functions;

import com.noodlesandwich.streams.Streams;
import org.junit.Test;

import com.noodlesandwich.streams.Stream;
import com.noodlesandwich.streams.matchers.NilMatcher;
import com.noodlesandwich.streams.testutils.ThrowingIterator;

import static com.noodlesandwich.streams.matchers.NilMatcher.nil;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public final class IntersectionTest {
    @Test public void
    is_lazy() {
        Stream<Object> stream = Streams.wrap(new ThrowingIterator());
        stream.intersect(null);
    }

    @Test public void
    intersecting_anything_with_nil_returns_nil() {
        Stream<Object> stream = Streams.of(new Object(), new Object(), new Object());
        assertThat(stream.intersect(Streams.nil()), is(nil()));
    }

    @Test public void
    intersecting_nil_with_anything_returns_nil() {
        Stream<Object> stream = Streams.of(new Object(), new Object(), new Object());
        assertThat(Streams.nil().intersect(stream), is(nil()));
    }

    @Test public void
    the_intersection_of_two_disjunct_streams_is_nil() {
        Stream<Integer> streamOne = Streams.of(1, 2, 3);
        Stream<Integer> streamTwo = Streams.of(6, 5, 4);
        assertThat(streamOne.intersect(streamTwo), is(NilMatcher.<Integer>nil()));
    }

    @Test public void
    intersects_two_streams() {
        Stream<Integer> streamOne = Streams.of(1, 2, 3);
        Stream<Integer> streamTwo = Streams.of(4, 3, 2);
        assertThat(streamOne.intersect(streamTwo), contains(2, 3));
    }

    @Test public void
    removes_duplicates() {
        Stream<Integer> streamOne = Streams.of(1, 2, 3, 2);
        Stream<Integer> streamTwo = Streams.of(2, 3, 4, 4);
        assertThat(streamOne.intersect(streamTwo), contains(2, 3));
    }

    @Test public void
    is_repeatable() {
        Stream<Integer> intersectedStream = Streams.of(1, 2, 3).intersect(Streams.of(4, 3, 2));
        assertThat(intersectedStream, contains(2, 3));
        assertThat(intersectedStream, contains(2, 3));
    }
}
