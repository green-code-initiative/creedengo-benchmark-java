package org.greencodeinitiative.creedngo;

import java.util.regex.Pattern;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class ExceptionsVsIf {
    @State(Scope.Thread)
    public static class StringIndex {
        @Param({ "1", "175" })
        private int strIndex;

        private static final String STR = "test";

        @Benchmark
        public char indexTryCatch() {
            try {
                return STR.charAt(strIndex);
            } catch (IndexOutOfBoundsException ex) {
                return '?';
            }
        }

        @Benchmark
        public char indexCheckBefore() {
            if (strIndex < STR.length()) {
                return STR.charAt(strIndex);
            } else {
                return '?';
            }
        }
    }

    @State(Scope.Thread)
    public static class IntParsing {
        @Param({ "123456", "12345&", "+77777", "badbad" })
        private String intToParse;

        private static final Pattern INTEGER_REGEX = Pattern.compile("[+-]?\\d+");

        @Benchmark
        public int parsingTryCatch() {
            try {
                return Integer.parseInt(intToParse);
            } catch (NumberFormatException ex) {
                return -1;
            }
        }

        @Benchmark
        public int parsingCheckBefore() {
            // NOTE: this is not a good practice, but if you have lots of erroneous inputs
            // and don't want to return early, the results show that it's worth to use
            // a strategy that does not rely on exceptions.
            if (INTEGER_REGEX.matcher(intToParse).matches()) {
                return Integer.parseInt(intToParse);
            } else {
                return -1;
            }
        }
    }
}
