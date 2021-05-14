package me.petrolingus.mylinkedlist.benchmark;

import java.util.StringJoiner;

public abstract class AbstractBenchmark implements Benchmark {

    protected final int benchmarkCount;

    protected final int warmupCount;

    protected final int testCount;

    protected final StringJoiner result = new StringJoiner("\n");

    public AbstractBenchmark(int benchmarkCount, int warmupCount, int testCount) {
        this.benchmarkCount = benchmarkCount;
        this.warmupCount = warmupCount;
        this.testCount = testCount;
    }

    protected abstract void saveToCsv(String name);
}
