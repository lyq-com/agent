package org.lyq.metrics.gauge;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 演示：/ɡeɪdʒ/
 * 使用 gauge 测量器，来监控队列的情况。
 * 如果队列长度超过 900 我们任务生产能力大于消费能力。
 */
public class SimpleGaugeExample {

    // 定义一个注册表。
    private static final MetricRegistry metricRegistry = new MetricRegistry();

    // 定义一个 Reporter。
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS).build();

    //定义一个双向队列，长度为 1000。
    private static final BlockingDeque<Long> queue = new LinkedBlockingDeque<>(1_000);

    public static void main(String[] args) {

        //向注册表中注册一个 gauge。
        metricRegistry.register(MetricRegistry.name(SimpleGaugeExample.class, "quene-sie"), new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return queue.size();
            }
        });

        // 每秒向控制台 reporter 一次。
        reporter.start(1,TimeUnit.SECONDS);

        //创建一个线程，完队列中加数据
        new Thread(() -> {
            for(;;){
                randomSleep();
                queue.add(System.nanoTime());
            }
        }).start();

        // 创建一个线程，往队列外取数据。
        new Thread(() -> {
            for(;;){
                randomSleep();
                queue.poll();
            }
        }).start();

    }

    private static void randomSleep(){
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
        } catch (InterruptedException e) {
        }
    }

}
