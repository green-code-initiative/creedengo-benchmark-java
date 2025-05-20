# Java µBenchmarks

Microbenchmarks that (in)validate CreednGo rules.

This repository is a Gradle project configured with the [JMH benchmarking framework](https://github.com/openjdk/jmh).

## Caution required

Rules often suggest an alternative way of writing small pieces of code.
Measuring the effect of a small and isolated amount of code is hard, we need to be cautious.
See [How NOT to write a microbenchmark](https://fr.slideshare.net/slideshow/2002-microbenchmarks/28737615), [Avoiding Benchmarking Pitfalls on the JVM](https://www.oracle.com/technical-resources/articles/java/architect-benchmarking.html), the OpenJDK wiki page [So, you want to write a microbenchmark](https://wiki.openjdk.org/display/HotSpot/Micro-Benchmark), and many others.

## Required tools

To run or create benchmarks, you need a recent JDK.
You can use [sdkman](https://sdkman.io/) to setup your environment easily.

By default, this project uses Java 21 (see below to change that).

## Writing a new benchmark

- Create a new public class in `microbenchs/src/jmh/java/org/greencodeinitiative/creedngo`.
- Create multiple methods with the `@Benchmark` annotation to compare different scenarios (for example one where the code rule is applied, one where it is not). See [`Example.java`](microbenchs/src/jmh/java/org/greencodeinitiative/creedngo/Example.java).

## Running one/multiple benchmark(s)

Use the `bench.sh` script, which is a wrapper around the JMH jar file.
Run `./bench.sh -h` too see the available JMH options.

To run a single benchmark function:

```sh
./bench.sh BenchmarkClass.benchmarkMethod
```

To run all the benchmarks in a given class:

```sh
./bench.sh BenchmarkClass
```

The benchmark results are printed to stdout and stored in [`microbenchs/build/reports/jmh/result.json`](microbenchs/build/reports/jmh/result.json).

## Changing the version of Java

Edit `build.gradle.kts` and change `JavaLanguageVersion.of(XX)`.

It is recommended to use a recent version of Java (>= 17), as it will benefit from the new [compiler blackholes](https://shipilev.net/jvm/anatomy-quarks/27-compiler-blackholes/).
However, for testing purposes, you may want to run your benchmarks with an older version.
