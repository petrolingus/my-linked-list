package me.petrolingus.mylinkedlist.benchmark.set;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@SuppressWarnings("ALL")
public class SetBenchmark {

    private final int testsCount;

    private final int callsCount;

    private final Set<Integer> hashSet = new HashSet<>();
    private final Set<Integer> linkedHashSet = new LinkedHashSet<>();
    private final Set<Integer> treeSet = new TreeSet<>();

    StringJoiner addJoiner = new StringJoiner("\n");
    StringJoiner searchJoiner = new StringJoiner("\n");
    StringJoiner removeJoiner = new StringJoiner("\n");

    Integer[] values;

    public SetBenchmark(int testsCount, int callsCount) {
        this.testsCount = testsCount;
        this.callsCount = callsCount;
        this.values = new Integer[callsCount];
    }

    private void init() {
        for (int i = 0; i < callsCount; i++) {
            values[i] = i;
        }
    }

    public void run() {
        init();
        runBenchmark();
    }

    private void runBenchmark() {
        System.out.println("Add Benchmark running...");
        addBenchmark();
        System.out.println("Search Benchmark running...");
        searchBenchmark();
        System.out.println("Remove Benchmark running...");
        removeBenchmark();
    }


    private void addBenchmark() {
        for (int i = 0; i < testsCount; i++) {
            long hashSetResult = addTestHashSet();
            long linkedHashSetResult = addTestLinkedHashSet();
            long treeSetResult = addTestTreeMapSet();
            addJoiner.add(hashSetResult + "," + linkedHashSetResult + "," + treeSetResult);
        }
        saveResults(addJoiner, "set-add.csv");
    }

    private long addTestHashSet() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            hashSet.add(j);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long addTestLinkedHashSet() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            linkedHashSet.add(j);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long addTestTreeMapSet() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            treeSet.add(j);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }


    private void searchBenchmark() {
        clear();
        for (int j = 0; j < callsCount; j++) {
            hashSet.add(j);
            linkedHashSet.add(j);
            treeSet.add(j);
        }
        for (int i = 0; i < testsCount; i++) {
            long hashSetResult = searchTestHashMap();
            long linkedHashSetResult = searchTestLinkedHashMap();
            long treeSetResult = searchTestTreeMapMap();
            searchJoiner.add(hashSetResult + "," + linkedHashSetResult + "," + treeSetResult);
        }
        saveResults(searchJoiner, "set-search.csv");
    }

    private long searchTestHashMap() {
        int value = callsCount / 2;
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            hashSet.contains(value);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long searchTestLinkedHashMap() {
        int value = callsCount / 2;
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            linkedHashSet.contains(value);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long searchTestTreeMapMap() {
        int value = callsCount / 2;
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            treeSet.contains(value);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }


    private void removeBenchmark() {
        clear();
        for (int i = 0; i < testsCount; i++) {
            for (int j = 0; j < callsCount; j++) {
                hashSet.add(j);
                linkedHashSet.add(j);
                treeSet.add(j);
            }
            long hashMapResult = removeTestHashMap();
            long linkedHashMapResult = removeTestLinkedHashMap();
            long treeMapMapResult = removeTestTreeMapMap();
            removeJoiner.add(hashMapResult + "," + linkedHashMapResult + "," + treeMapMapResult);
            clear();
        }
        saveResults(removeJoiner, "set-remove.csv");
    }

    private long removeTestHashMap() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            hashSet.remove(j);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long removeTestLinkedHashMap() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            linkedHashSet.remove(j);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long removeTestTreeMapMap() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            treeSet.remove(j);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }


    private void clear() {
        hashSet.clear();
        linkedHashSet.clear();
        treeSet.clear();
    }

    private void saveResults(StringJoiner joiner, String name) {
        try(FileWriter fileWriter = new FileWriter(name)) {
            fileWriter.write(joiner.toString(), 0, joiner.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
