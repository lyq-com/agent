package org.lyq.metrics.histograms;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class HistgramExample {

    private static final MetricRegistry registry = new MetricRegistry();
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    private static final Histogram histogram = registry.histogram("searc-result");


    public static void main(String[] args) {
        reporter.start(10,TimeUnit.SECONDS);

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
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
        }
    }
}
