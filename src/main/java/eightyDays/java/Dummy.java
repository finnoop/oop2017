package eightyDays.java;

import org.openjdk.jmh.annotations.Benchmark;

public class Dummy {
    @Benchmark
    public void test() throws InterruptedException {
        Thread.sleep(100);
    }
}
