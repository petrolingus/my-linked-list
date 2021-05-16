package me.petrolingus.mylinkedlist;

import me.petrolingus.mylinkedlist.benchmark.list.LinkedListAddBenchmark;
import me.petrolingus.mylinkedlist.benchmark.list.LinkedListFindBenchmark;
import me.petrolingus.mylinkedlist.benchmark.list.LinkedListInsertBenchmark;
import me.petrolingus.mylinkedlist.benchmark.list.LinkedListRemoveBenchmark;
import me.petrolingus.mylinkedlist.benchmark.map.MapBenchmark;
import me.petrolingus.mylinkedlist.benchmark.set.SetBenchmark;

public class Main {

    public static void main(String[] args) {

//        new LinkedListAddBenchmark(32, 1000, 1000, 10_000).run();
//        new LinkedListAddBenchmark(32, 1000, 1000, 50_000).run();
//        new LinkedListAddBenchmark(32, 1000, 1000, 100_000).run();

//        new LinkedListInsertBenchmark(32, 1000, 1000, 10_000).run();
//        new LinkedListInsertBenchmark(32, 1000, 1000, 50_000).run();
//        new LinkedListInsertBenchmark(32, 1000, 1000, 100_000).run();

//        new LinkedListFindBenchmark(32, 1000, 1000, 10_000).run();
//        new LinkedListFindBenchmark(32, 1000, 1000, 20_000).run();
//        new LinkedListFindBenchmark(32, 1000, 1000, 30_000).run();

//        new LinkedListRemoveBenchmark(32, 1000, 1000, 10_000).run();
//        new LinkedListRemoveBenchmark(32, 1000, 1000, 50_000).run();
//        new LinkedListRemoveBenchmark(32, 1000, 1000, 100_000).run();

//        new MapBenchmark(1000, 200_000).run();
        new SetBenchmark(10000, 100_000).run();

    }
}
