package me.petrolingus.mylinkedlist.benchmark.list;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@SuppressWarnings("ALL")
public class ListBenchmark {

    private final int testsCount;

    private final int callsCount;

    private final List<Integer> arrayList = new ArrayList<>();
    private final List<Integer> linkedList = new LinkedList<>();

    StringJoiner addJoiner = new StringJoiner("\n");
    StringJoiner searchJoiner = new StringJoiner("\n");
    StringJoiner removeJoiner = new StringJoiner("\n");

    Integer[] values;

    public ListBenchmark(int testsCount, int callsCount) {
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
            long arrayListResult = addTestHashSet();
            long linkedListResult = addTestLinkedHashSet();
            addJoiner.add(arrayListResult + "," + linkedListResult);
        }
        saveResults(addJoiner, "list-add.csv");
    }

    private long addTestHashSet() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            arrayList.add(j);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long addTestLinkedHashSet() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            arrayList.add(j);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }


    private void searchBenchmark() {
        clear();
        for (int j = 0; j < callsCount; j++) {
            arrayList.add(j);
            linkedList.add(j);
        }
        for (int i = 0; i < testsCount; i++) {
            long arrayListResult = searchTestHashMap();
            long linkedListResult = searchTestLinkedHashMap();
            searchJoiner.add(arrayListResult + "," + linkedListResult);
        }
        saveResults(searchJoiner, "list-search.csv");
    }

    private long searchTestHashMap() {
        int value = callsCount / 2;
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            arrayList.contains(value);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long searchTestLinkedHashMap() {
        int value = callsCount / 2;
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            linkedList.contains(value);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }


    private void removeBenchmark() {
        clear();
        for (int i = 0; i < testsCount; i++) {
            for (int j = 0; j < callsCount; j++) {
                arrayList.add(j);
                linkedList.add(j);
            }
            long arrayListResult = removeTestHashMap();
            long linkedListResult = removeTestLinkedHashMap();
            removeJoiner.add(arrayListResult + "," + linkedListResult);
            clear();
        }
        saveResults(removeJoiner, "list-remove.csv");
    }

    private long removeTestHashMap() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            arrayList.remove(0);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long removeTestLinkedHashMap() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            linkedList.remove(0);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }


    private void clear() {
        arrayList.clear();
        linkedList.clear();
    }

    private void saveResults(StringJoiner joiner, String name) {
        try(FileWriter fileWriter = new FileWriter(name)) {
            fileWriter.write(joiner.toString(), 0, joiner.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
