package org.greencodeinitiative.creedngo;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class GCI67 {
    private int i;

    @Benchmark
    public int prefix() {
        return ++i;
    }

    @Benchmark
    public int postfix() {
        return i++;
    }
}
