package org.lyq.metrics.histograms;

import com.codahale.metrics.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SlidingWindowReservoirsHistogramExample {

    private static final MetricRegistry registry = new MetricRegistry();
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    private static final Histogram histogram = new Histogram(new SlidingWindowReservoir(1_000));


    public static void main(String[] args) {
        reporter.start(10, TimeUnit.SECONDS);
        registry.register("SlidingWindowReservoir-Histogram",histogram);
        while (true){
            doSearch();
            randomSleep();
        }
    }

    private static void doSearch(){
        histogram.update(ThreadLocalRandom.current().nextInt(10));
    }


    private static void randomSleep(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
}
