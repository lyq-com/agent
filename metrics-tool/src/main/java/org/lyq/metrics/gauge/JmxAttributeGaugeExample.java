package org.lyq.metrics.gauge;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxAttributeGauge;
import com.codahale.metrics.MetricRegistry;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.util.concurrent.TimeUnit;

public class JmxAttributeGaugeExample {

    private static final MetricRegistry metricRegistry = new MetricRegistry();
    private static final ConsoleReporter consoleReporter = ConsoleReporter
            .forRegistry(metricRegistry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS).build();

    public static void main(String[] args) throws MalformedObjectNameException, InterruptedException {
        consoleReporter.start(10,TimeUnit.SECONDS);

        metricRegistry.register(MetricRegistry.name(JmxAttributeGaugeExample.class,"HeapMemoryUsage"),new JmxAttributeGauge(
           new ObjectName("java.lang:type=Memory"),"HeapMemoryUsage"
        ));

        metricRegistry.register(MetricRegistry.name(JmxAttributeGaugeExample.class,"NonHeapMemoryUsage"),new JmxAttributeGauge(
                new ObjectName("java.lang:type=Memory"),"NonHeapMemoryUsage"
        ));

        Thread.currentThread().join();;
    }

}
