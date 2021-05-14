package me.petrolingus.mylinkedlist.benchmark.list;

import me.petrolingus.mylinkedlist.MyLinkedList;
import me.petrolingus.mylinkedlist.benchmark.AbstractBenchmark;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.OptionalDouble;
import java.util.stream.LongStream;

public abstract class AbstractLinkedListBenchmark extends AbstractBenchmark {

    final int objectsCount;

    final LinkedList<Integer> linkedList = new LinkedList<>();
    final MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

    public AbstractLinkedListBenchmark(int benchmarkCount, int warmupCount, int testCount, int objectsCount) {
        super(benchmarkCount, warmupCount, testCount);
        this.objectsCount = objectsCount;
    }

    public void run() {

        result.add("linked_list, my_linked_list");

        for (int i = 0; i < benchmarkCount; i++) {
            warmup();
            prepareBenchmark();
            double result1 = linkedList(objectsCount, testCount);
            double result2 = myLinkedList(objectsCount, testCount);
            result.add(result1 + ", " + result2);
            System.out.println("Benchmark #" + i);
            System.out.println("Linked List:  " + result1 + " ns");
            System.out.println("MyLinkedList: " + result2 + " ns");
            System.out.println();
        }

        saveResult();
    }

    protected void prepareBenchmark() {

    }

    protected void prepareLinkedList() {

    }

    protected void prepareMyLinkedList() {

    }

    private double linkedList(int elementsCount, int measuresCount) {

        long[] measures = new long[measuresCount];

        for (int i = 0; i < measuresCount; i++) {
            prepareLinkedList();
            long start = System.nanoTime();
            for (int j = 0; j < elementsCount; j++) {
                linkedListBenchmark();
            }
            long stop = System.nanoTime();
            measures[i] = stop - start;
            linkedList.clear();
        }

        OptionalDouble average = LongStream.of(measures).average();
        return average.isPresent() ? average.getAsDouble() : -1;
    }

    abstract void linkedListBenchmark();

    private double myLinkedList(int elementsCount, int measuresCount) {

        long[] measures = new long[measuresCount];

        for (int i = 0; i < measuresCount; i++) {
            prepareMyLinkedList();
            long start = System.nanoTime();
            for (int j = 0; j < elementsCount; j++) {
                myLinkedListBenchmark();
            }
            long stop = System.nanoTime();
            measures[i] = stop - start;
            myLinkedList.clear();
        }

        OptionalDouble average = LongStream.of(measures).average();
        return average.isPresent() ? average.getAsDouble() : -1;
    }

    abstract void myLinkedListBenchmark();

    private void warmup() {
        for (int i = 0; i < warmupCount; i++) {
            for (int j = 0; j < objectsCount; j++) {
                linkedList.add(j);
                myLinkedList.add(j);
            }
            clear();
        }
    }

    private void clear() {
        linkedList.clear();
        myLinkedList.clear();
    }

    protected void eat(int i) {

    }

    abstract void saveResult();

    @Override
    final protected void saveToCsv(String name) {
        try(FileWriter fileWriter = new FileWriter(name + ".csv")) {
            fileWriter.write(result.toString(), 0, result.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
