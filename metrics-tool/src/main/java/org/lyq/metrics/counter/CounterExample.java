package org.lyq.metrics.counter;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CounterExample {

    private static final MetricRegistry metricRegistry = new MetricRegistry();

    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS).build();

    private static final BlockingDeque<Long> queue = new LinkedBlockingDeque<>(1_000);


    public static void main(String[] args) {

        reporter.start(10,TimeUnit.SECONDS);
        Counter counter = metricRegistry.counter("quene-counter", Counter::new);

        new Thread(() -> {
            for(;;){
                randomSleep();
                queue.add(System.nanoTime());
                counter.inc();
            }
        }).start();

        new Thread(() -> {
            for(;;){
                randomSleep();
                if (queue.poll()!=null) {
                    counter.dec();
                }
            }
        }).start();


    }

    private static void randomSleep(){
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
        }
    }
}
