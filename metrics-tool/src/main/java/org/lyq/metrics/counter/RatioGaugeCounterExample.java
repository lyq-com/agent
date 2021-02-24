package org.lyq.metrics.counter;

import com.codahale.metrics.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RatioGaugeCounterExample {

    private static final MetricRegistry metricRegistry = new MetricRegistry();
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS).build();
    private static final Counter totalCounter = new Counter();
    private static final Counter successCounter = new Counter();


    public static void main(String[] args) {

        reporter.start(10,TimeUnit.SECONDS);
        metricRegistry.gauge("success-rate",() -> new RatioGauge(){
            @Override
            protected Ratio getRatio() {
                return Ratio.of(successCounter.getCount(),totalCounter.getCount());
            }
        });

        for (;;){
            shortSleep();
            business();
        }
    }

    private static void business(){
        // total inc
       totalCounter.inc();
        try{
            int x= 1/ThreadLocalRandom.current().nextInt(6);
            // success inc
            successCounter.inc();
        }catch (Exception e){
            System.out.println("error...");
        }
    }

    private static void shortSleep(){
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
        } catch (InterruptedException e) {
        }
    }


}
