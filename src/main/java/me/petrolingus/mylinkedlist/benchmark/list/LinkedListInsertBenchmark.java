package me.petrolingus.mylinkedlist.benchmark.list;

public class LinkedListInsertBenchmark extends AbstractLinkedListBenchmark {

    public LinkedListInsertBenchmark(int benchmarkCount, int warmupCount, int testCount, int objectsCount) {
        super(benchmarkCount, warmupCount, testCount, objectsCount);
    }

    @Override
    void linkedListBenchmark() {
        linkedList.add(0, 3141592);
    }

    @Override
    void myLinkedListBenchmark() {
        myLinkedList.add(0, 3141592);
    }

    @Override
    void saveResult() {
        saveToCsv("linked-list-insert-benchmark-" + objectsCount);
    }
}
