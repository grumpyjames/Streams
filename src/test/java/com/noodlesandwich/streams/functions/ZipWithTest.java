package com.noodlesandwich.streams.functions;

import java.util.Arrays;

import org.junit.Test;

import com.noodlesandwich.streams.Stream;
import com.noodlesandwich.streams.matchers.NilMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class ZipWithTest {
    @Test public void
    zipping_anything_with_nil_returns_nil() {
        Stream<Integer> streamOne = Stream.wrap(Arrays.asList(1, 2, 3, 4, 5));
        Stream<Integer> streamTwo = Stream.nil();
        assertThat(streamOne.zipWith(streamTwo, add()), is(NilMatcher.<Integer>nil()));
    }

    @Test public void
    zipping_nil_with_anything_returns_nil() {
        Stream<Integer> streamOne = Stream.nil();
        Stream<Integer> streamTwo = Stream.wrap(Arrays.asList(7, 6, 5, 4, 3));
        assertThat(streamOne.zipWith(streamTwo, add()), is(NilMatcher.<Integer>nil()));
    }

    @Test public void
    zips_two_populated_streams_of_the_same_length_and_type() {
        Stream<Integer> streamOne = Stream.wrap(Arrays.asList(1, 2, 3, 4, 5));
        Stream<Integer> streamTwo = Stream.wrap(Arrays.asList(7, 4, 9, 1, 10));
        assertThat(streamOne.zipWith(streamTwo, add()), contains(8, 6, 12, 5, 15));
    }

    @Test public void
    truncates_a_pairing_of_a_long_stream_with_a_short_stream_using_the_short() {
        Stream<Integer> streamOne = Stream.wrap(Arrays.asList(1, 2, 3, 4, 5));
        Stream<Integer> streamTwo = Stream.wrap(Arrays.asList(7, 4, 9));
        assertThat(streamOne.zipWith(streamTwo, add()), contains(8, 6, 12));
    }

    @Test public void
    truncates_a_pairing_of_a_short_stream_with_a_long_stream_using_the_short() {
        Stream<Integer> streamOne = Stream.wrap(Arrays.asList(1, 2, 3));
        Stream<Integer> streamTwo = Stream.wrap(Arrays.asList(7, 4, 9, 1, 10));
        assertThat(streamOne.zipWith(streamTwo, add()), contains(8, 6, 12));
    }

    @Test public void
    zips_streams_of_different_types() {
        Stream<Integer> streamOne = Stream.wrap(Arrays.asList(1, 2, 3, 4, 5));
        Stream<String> streamTwo = Stream.wrap(Arrays.asList("a", "b", "c", "d", "e"));
        assertThat(streamOne.zipWith(streamTwo, concat()), contains("1a", "2b", "3c", "4d", "5e"));
    }

    private static ZipWithFunction<Integer, Integer, Integer> add() {
        return new ZipWithFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };
    }

    private static ZipWithFunction<Object, Object, String> concat() {
        return new ZipWithFunction<Object, Object, String>() {
            @Override
            public String apply(Object a, Object b) {
                return a.toString() + b.toString();
            }
        };
    }
}
