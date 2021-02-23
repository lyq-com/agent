package org.lyq.metrics.gauge;

import com.codahale.metrics.CachedGauge;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CachedGaugeExample {

    private static final MetricRegistry metricRegistry = new MetricRegistry();
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) throws InterruptedException {
        reporter.start(10,TimeUnit.SECONDS);

        metricRegistry.gauge("cached-db-size",() -> new CachedGauge<Long>(10,TimeUnit.SECONDS){

            @Override
            protected Long loadValue() {
                return queryFromDB();
            }
        });

        System.out.println("================================== " + new Date());
        Thread.currentThread().join();

    }

    private static long queryFromDB(){
        System.out.println("======== queryFromDB ========");
        return System.currentTimeMillis();
    }
}
