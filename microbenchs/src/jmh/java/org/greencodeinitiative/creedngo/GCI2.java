package org.greencodeinitiative.creedngo;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class GCI2 {

    private static final long SEED = 123;
    public int arg;
    public int argBig;

    @Setup(Level.Iteration)
    public void setup() {
        arg = new Random(SEED).nextInt(0, 4); // 0, 1, 2 or 3
        argBig = new Random(SEED).nextInt(0, 12);
        // NOTE: choosing the branch at random is important,
        // because simply doing "+1" makes it too easy for the
        // CPU branch predictor to understand what is going on,
        // and it will optimize the if-else chain, making it as
        // efficient as the switch.
    }

    @Benchmark
    public int compliant(Blackhole bh) {
        int nb = arg;
        switch (nb) {
            case 0 -> bh.consume(nb);
            case 1 -> bh.consume(nb);
            case 2 -> bh.consume(nb);
            default -> bh.consume(nb);
        }
        return nb;
    }

    @Benchmark
    public int compliantBig(Blackhole bh) {
        int nb = arg;
        switch (nb) {
            case 0 -> bh.consume(nb);
            case 1 -> bh.consume(nb);
            case 2 -> bh.consume(nb);
            case 3 -> bh.consume(nb);
            case 4 -> bh.consume(nb);
            case 5 -> bh.consume(nb);
            case 6 -> bh.consume(nb);
            case 7 -> bh.consume(nb);
            case 8 -> bh.consume(nb);
            case 9 -> bh.consume(nb);
            case 10 -> bh.consume(nb);
            default -> bh.consume(nb);
        }
        return nb;
    }

    @Benchmark
    public int nonCompliant(Blackhole bh) {
        int nb = arg;
        if (nb == 0) {
            bh.consume(nb);
        } else if (nb == 1) {
            bh.consume(nb);
        } else if (nb == 2) {
            bh.consume(nb);
        } else {
            bh.consume(nb);
        }
        return nb;
    }

    @Benchmark
    public int nonCompliantBig(Blackhole bh) {
        int nb = arg;
        if (nb == 0) {
            bh.consume(nb);
        } else if (nb == 1) {
            bh.consume(nb);
        } else if (nb == 2) {
            bh.consume(nb);
        } else if (nb == 3) {
            bh.consume(nb);
        } else if (nb == 4) {
            bh.consume(nb);
        } else if (nb == 5) {
            bh.consume(nb);
        } else if (nb == 6) {
            bh.consume(nb);
        } else if (nb == 7) {
            bh.consume(nb);
        } else if (nb == 8) {
            bh.consume(nb);
        } else if (nb == 9) {
            bh.consume(nb);
        } else if (nb == 10) {
            bh.consume(nb);
        } else {
            bh.consume(nb);
        }
        return nb;
    }
}
