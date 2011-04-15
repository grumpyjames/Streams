package com.noodlesandwich.streams.functions;

import org.junit.Test;

import com.noodlesandwich.streams.Stream;
import com.noodlesandwich.streams.matchers.NilMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public final class ExceptTest {
    @Test public void
    anything_except_nil_results_in_no_change() {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        assertThat(stream.except(Stream.<Integer>nil()), contains(1, 2, 3));
    }

    @Test public void
    a_stream_except_another_disjunct_stream_results_in_no_change() {
        Stream<Integer> streamOne = Stream.of(1, 2, 3);
        Stream<Integer> streamTwo = Stream.of(4, 5, 6);
        assertThat(streamOne.except(streamTwo), contains(1, 2, 3));
    }

    @Test public void
    removes_items_in_both_streams() {
        Stream<Integer> streamOne = Stream.of(1, 2, 3);
        Stream<Integer> streamTwo = Stream.of(3, 4);
        assertThat(streamOne.except(streamTwo), contains(1, 2));
    }

    @Test public void
    does_not_remove_duplicates() {
        Stream<Integer> streamOne = Stream.of(1, 2, 3, 2);
        Stream<Integer> streamTwo = Stream.of(3, 4);
        assertThat(streamOne.except(streamTwo), contains(1, 2, 2));
    }

    @Test public void
    excepting_a_superset_results_in_nil() {
        Stream<Integer> streamOne = Stream.of(1, 2, 3);
        Stream<Integer> streamTwo = Stream.of(4, 3, 2, 1);
        assertThat(streamOne.except(streamTwo), is(NilMatcher.<Integer>nil()));
    }
}
