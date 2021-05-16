package me.petrolingus.mylinkedlist.benchmark.map;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class MapBenchmark {

    private final int testsCount;

    private final int callsCount;

    private final Map<String, Integer> hashMap = new HashMap<>();
    private final Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
    private final Map<String, Integer> treeMap = new TreeMap<>();

    StringJoiner putJoiner = new StringJoiner("\n");
    StringJoiner searchJoiner = new StringJoiner("\n");
    StringJoiner removeJoiner = new StringJoiner("\n");

    String[] keys;
    Integer[] values;

    public MapBenchmark(int testsCount, int callsCount) {
        this.testsCount = testsCount;
        this.callsCount = callsCount;
        this.keys = new String[callsCount];
        this.values = new Integer[callsCount];
    }

    private void init() {
        putJoiner.add("HashMap, LinkedMap, TreeMap");
        for (int i = 0; i < callsCount; i++) {
            keys[i] = String.valueOf(i);
            values[i] = i;
        }
    }

    public void run() {
        init();
        runBenchmark();
    }

    private void runBenchmark() {
        System.out.println("Put Benchmark running...");
        putBenchmark();
        System.out.println("Search Benchmark running...");
        searchBenchmark();
        System.out.println("Remove Benchmark running...");
        removeBenchmark();
    }


    private void putBenchmark() {
        for (int i = 0; i < testsCount; i++) {
            long hashMapResult = putTestHashMap();
            long linkedHashMapResult = putTestLinkedHashMap();
            long treeMapMapResult = putTestTreeMapMap();
            putJoiner.add(hashMapResult + "," + linkedHashMapResult + "," + treeMapMapResult);
            clear();
        }
        saveResults(putJoiner, "map-put.csv");
    }

    private long putTestHashMap() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            hashMap.put(keys[j], values[j]);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long putTestLinkedHashMap() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            linkedHashMap.put(keys[j], values[j]);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long putTestTreeMapMap() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            treeMap.put(keys[j], values[j]);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }


    private void searchBenchmark() {
        clear();
        for (int j = 0; j < callsCount; j++) {
            hashMap.put(keys[j], values[j]);
            linkedHashMap.put(keys[j], values[j]);
            treeMap.put(keys[j], values[j]);
        }
        for (int i = 0; i < testsCount; i++) {
            long hashMapResult = searchTestHashMap();
            long linkedHashMapResult = searchTestLinkedHashMap();
            long treeMapMapResult = searchTestTreeMapMap();
            searchJoiner.add(hashMapResult + "," + linkedHashMapResult + "," + treeMapMapResult);
        }
        saveResults(searchJoiner, "map-search.csv");
    }

    private long searchTestHashMap() {
        String key = keys[callsCount / 2];
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            hashMap.containsKey(key);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long searchTestLinkedHashMap() {
        String key = keys[callsCount / 2];
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            linkedHashMap.containsKey(key);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long searchTestTreeMapMap() {
        String key = keys[callsCount / 2];
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            treeMap.containsKey(key);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }


    private void removeBenchmark() {
        clear();
        for (int i = 0; i < testsCount; i++) {
            for (int j = 0; j < callsCount; j++) {
                hashMap.put(keys[j], values[j]);
                linkedHashMap.put(keys[j], values[j]);
                treeMap.put(keys[j], values[j]);
            }
            long hashMapResult = removeTestHashMap();
            long linkedHashMapResult = removeTestLinkedHashMap();
            long treeMapMapResult = removeTestTreeMapMap();
            removeJoiner.add(hashMapResult + "," + linkedHashMapResult + "," + treeMapMapResult);
            clear();
        }
        saveResults(removeJoiner, "map-remove.csv");
    }

    private long removeTestHashMap() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            hashMap.remove(keys[j]);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long removeTestLinkedHashMap() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            linkedHashMap.remove(keys[j]);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }

    private long removeTestTreeMapMap() {
        Instant start = Instant.now();
        for (int j = 0; j < callsCount; j++) {
            treeMap.remove(keys[j]);
        }
        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos();
    }


    private void clear() {
        hashMap.clear();
        linkedHashMap.clear();
        treeMap.clear();
    }

    private void saveResults(StringJoiner joiner, String name) {
        try(FileWriter fileWriter = new FileWriter(name)) {
            fileWriter.write(joiner.toString(), 0, joiner.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
