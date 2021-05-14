package me.petrolingus.mylinkedlist.benchmark.list;

public class LinkedListAddBenchmark extends AbstractLinkedListBenchmark {

    public LinkedListAddBenchmark(int benchmarkCount, int warmupCount, int testCount, int objectsCount) {
        super(benchmarkCount, warmupCount, testCount, objectsCount);
    }

    @Override
    void linkedListBenchmark() {
        linkedList.add(3141592);
    }

    @Override
    void myLinkedListBenchmark() {
        myLinkedList.add(3141592);
    }

    @Override
    void saveResult() {
        saveToCsv("linked-list-add-benchmark-" + objectsCount);
    }
}
