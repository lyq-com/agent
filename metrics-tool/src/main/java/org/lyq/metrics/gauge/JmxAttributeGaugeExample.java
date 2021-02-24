package org.lyq.metrics.gauge;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxAttributeGauge;
import com.codahale.metrics.MetricRegistry;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.util.concurrent.TimeUnit;

public class JmxAttributeGaugeExample {

    // 创建一个注册表
    private static final MetricRegistry metricRegistry = new MetricRegistry();

    // 创建一个 ConsoleReporter
    private static final ConsoleReporter consoleReporter = ConsoleReporter
            .forRegistry(metricRegistry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS).build();

    public static void main(String[] args) throws MalformedObjectNameException, InterruptedException {
        consoleReporter.start(10,TimeUnit.SECONDS);

        // 向注册表中注册一个 JmxAttributeGauge（堆内存）
        metricRegistry.register(MetricRegistry.name(JmxAttributeGaugeExample.class,"HeapMemoryUsage"),new JmxAttributeGauge(
           new ObjectName("java.lang:type=Memory"),"HeapMemoryUsage"
        ));

        // 向注册表中注册一个 JmxAttributeGauge(非堆内存)
        metricRegistry.register(MetricRegistry.name(JmxAttributeGaugeExample.class,"NonHeapMemoryUsage"),new JmxAttributeGauge(
                new ObjectName("java.lang:type=Memory"),"NonHeapMemoryUsage"
        ));

        metricRegistry.register(MetricRegistry.name(JmxAttributeGaugeExample.class,"LoadedClassCount"),new JmxAttributeGauge(
                new ObjectName("java.lang:type=ClassLoading"),"LoadedClassCount"
        ));

        //
        metricRegistry.register(MetricRegistry.name(JmxAttributeGaugeExample.class,"TotalLoadedClassCount"),new JmxAttributeGauge(
                new ObjectName("java.lang:type=ClassLoading"),"TotalLoadedClassCount"
        ));

        metricRegistry.register(MetricRegistry.name(JmxAttributeGaugeExample.class,"UnloadedClassCount"),new JmxAttributeGauge(
                new ObjectName("java.lang:type=ClassLoading"),"UnloadedClassCount"
        ));

        Thread.currentThread().join();;
    }

}
