package com.noodlesandwich.streams.functions;

import java.util.Arrays;

import org.junit.Test;

import com.noodlesandwich.streams.Stream;
import com.noodlesandwich.streams.matchers.NilMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public final class SymmetricDifferenceTest {
    @Test public void
    a_symmetric_difference_of_anything_with_nil_results_in_the_first_stream() {
        Stream<Integer> stream = Stream.wrap(Arrays.asList(1, 2, 3));
        assertThat(stream.symmetricDifference(Stream.<Integer>nil()), contains(1, 2, 3));
    }

    @Test public void
    a_symmetric_difference_of_nil_with_anything_results_in_the_second_stream() {
        Stream<Integer> stream = Stream.wrap(Arrays.asList(1, 2, 3));
        assertThat(Stream.<Integer>nil().symmetricDifference(stream), contains(1, 2, 3));
    }

    @Test public void
    the_symmetric_difference_of_two_disjunct_streams_results_in_a_concatenation() {
        Stream<Integer> streamOne = Stream.wrap(Arrays.asList(1, 2, 3));
        Stream<Integer> streamTwo = Stream.wrap(Arrays.asList(6, 5, 4));
        assertThat(streamOne.symmetricDifference(streamTwo), contains(1, 2, 3, 6, 5, 4));
    }

    @Test public void
    removes_items_in_both_streams() {
        Stream<Integer> streamOne = Stream.wrap(Arrays.asList(1, 2, 3));
        Stream<Integer> streamTwo = Stream.wrap(Arrays.asList(3, 4));
        assertThat(streamOne.symmetricDifference(streamTwo), contains(1, 2, 4));
    }

    @Test public void
    removes_duplicates() {
        Stream<Integer> streamOne = Stream.wrap(Arrays.asList(1, 2, 1, 3));
        Stream<Integer> streamTwo = Stream.wrap(Arrays.asList(3, 4, 4));
        assertThat(streamOne.symmetricDifference(streamTwo), contains(1, 2, 4));
    }

    @Test public void
    the_symmetric_difference_of_two_identical_streams_is_nil() {
        Stream<Integer> streamOne = Stream.wrap(Arrays.asList(1, 2, 3));
        Stream<Integer> streamTwo = Stream.wrap(Arrays.asList(1, 2, 3));
        assertThat(streamOne.symmetricDifference(streamTwo), is(NilMatcher.<Integer>nil()));
    }

    @Test public void
    the_symmetric_difference_of_two_streams_containing_the_same_elements_is_nil() {
        Stream<Integer> streamOne = Stream.wrap(Arrays.asList(1, 2, 3));
        Stream<Integer> streamTwo = Stream.wrap(Arrays.asList(2, 3, 1));
        assertThat(streamOne.symmetricDifference(streamTwo), is(NilMatcher.<Integer>nil()));
    }
}