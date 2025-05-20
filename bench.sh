#!/usr/bin/env bash
set -eu
cd "$(dirname "$0")"

# Compile the benchmarks.
./gradlew jmhJar

# Run JMH with user options.
PROJECT=microbenchs

REPORT_DIR=$(pwd)/$PROJECT/build/reports/jmh
mkdir -p "$REPORT_DIR"
MACHINE_REPORT_FILE=$REPORT_DIR/result.json
# HUMAN_REPORT_FILE=$REPORT_DIR/result.txt

MANDATORY_ARGS=(-rf json -rff "$MACHINE_REPORT_FILE")
    DEFAULT_ARGS=()
if [[ "$*" != *"-bm"* ]]; then
    # default modes: avg and thrpt
    DEFAULT_ARGS=(-bm "avg,thrpt")
fi
if [[ "$*" != *"-tu"* ]]; then
    # default unit: µs
    DEFAULT_ARGS+=(-tu us)
fi
java -jar ./$PROJECT/build/libs/$PROJECT-jmh.jar "${MANDATORY_ARGS[@]}" "${DEFAULT_ARGS[@]}" "$@"
