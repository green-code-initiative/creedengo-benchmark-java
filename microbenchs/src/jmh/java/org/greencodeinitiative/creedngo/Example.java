package org.greencodeinitiative.creedngo;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

/**
 * Example of how to use JMH for microbenchmarks.
 * See more samples at https://github.com/openjdk/jmh/tree/master/jmh-samples/src/main/java/org/openjdk/jmh/samples.
 */
@State(Scope.Benchmark)
public class Example {

    private int[] numbers;

    /**
     * Use the @Setup annotation on a method to run it before the benchmarks.
     * Choose the level for more details.
     */
    @Setup(Level.Trial)
    public void setup() {
        // fill the array
        numbers = new int[1000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        // to share code across benchmarks, put it in the main sources directory (`main/java`).
        // For instance, here you can call:
        ExampleShared.f();
    }

    @Benchmark
    public int sumWithLoop() {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        // important! return the result so that the compiler does not remove it
        return sum;
    }

    @Benchmark
    public int sumWithStream() {
        // important! return the result so that the compiler does not remove it
        return java.util.Arrays.stream(numbers).sum();
    }

    int compute(int x) {
        for (int i = 0; i < 10; i++) {
            x = 1 + x * x / 2;
        }
        return x;
    }

    @Benchmark
    public void compute(final Blackhole bh) {
        // BAD (eliminated by the compiler):
        // compute(0);
        // compute(1);

        // GOOD:
        // important! use a BlackHole on the result so that the compiler does not remove it
        // Blackholes are useful in situations where you don't want to return the result, or when
        // you have multiple results to "consume", like here.
        bh.consume(compute(0));
        bh.consume(compute(1));
    }
}
