package me.petrolingus.mylinkedlist.benchmark.list;

public class LinkedListFindBenchmark extends AbstractLinkedListBenchmark {

    public LinkedListFindBenchmark(int benchmarkCount, int warmupCount, int testCount, int objectsCount) {
        super(benchmarkCount, warmupCount, testCount, objectsCount);
    }

    @Override
    protected void prepareBenchmark() {
        for (int i = 0; i < objectsCount; i++) {
            linkedList.add(i);
            myLinkedList.add(i);
        }
        linkedList.set(objectsCount - 1, 3141592);
        myLinkedList.set(objectsCount - 1, 3141592);
    }

    @Override
    void linkedListBenchmark() {
        eat(linkedList.indexOf(3141592));
    }

    @Override
    void myLinkedListBenchmark() {
        eat(myLinkedList.indexOf(3141592));
    }

    @Override
    void saveResult() {
        saveToCsv("linked-list-search-benchmark-" + objectsCount);
    }
}
