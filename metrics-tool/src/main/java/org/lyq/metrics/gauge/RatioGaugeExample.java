package org.lyq.metrics.gauge;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.RatioGauge;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 监控接口的成功率。
 */
public class RatioGaugeExample {

    // 定义一个注册表。
    private static final MetricRegistry metricRegistry = new MetricRegistry();

    // 定义一个控制台 Reporter
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS).build();
    private static final Meter totalMeter = new Meter();
    private static final Meter successMeter = new Meter();


    public static void main(String[] args) {
        // 每 10 秒向控制台报告一次。
        reporter.start(10,TimeUnit.SECONDS);

        // 向注册表中注册一个 ratioGauge。
        metricRegistry.gauge("success-rate",() -> new RatioGauge(){
            @Override
            protected Ratio getRatio() {
                return Ratio.of(successMeter.getCount(),totalMeter.getCount());
            }
        });

        for (;;){
            shortSleep();
            business();
        }
    }

    private static void business(){
        // total inc
        totalMeter.mark();
        try{
            int x= 1/ThreadLocalRandom.current().nextInt(6);
            // success inc
            successMeter.mark();
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
