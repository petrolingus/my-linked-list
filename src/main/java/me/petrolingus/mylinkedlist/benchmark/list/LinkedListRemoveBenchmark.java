package me.petrolingus.mylinkedlist.benchmark.list;

public class LinkedListRemoveBenchmark extends AbstractLinkedListBenchmark {

    public LinkedListRemoveBenchmark(int benchmarkCount, int warmupCount, int testCount, int objectsCount) {
        super(benchmarkCount, warmupCount, testCount, objectsCount);
    }

    @Override
    protected void prepareLinkedList(){
        for (int i = 0; i < objectsCount; i++) {
            linkedList.add(3141592);
        }
    }

    @Override
    void linkedListBenchmark() {
        eat(linkedList.remove(0));
    }

    @Override
    protected void prepareMyLinkedList() {
        for (int i = 0; i < objectsCount; i++) {
            myLinkedList.add(3141592);
        }
    }

    @Override
    void myLinkedListBenchmark() {
        eat(myLinkedList.remove(0));
    }

    @Override
    void saveResult() {
        saveToCsv("linked-list-remove-benchmark-" + objectsCount);
    }
}
